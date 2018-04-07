package com.ssdi.immersivepdf.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssdi.immersivepdf.model.fileUpload.FileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AmazonClient {
    private AmazonS3 s3client;
    private Logger logger = LoggerFactory.getLogger(AmazonClient.class);

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    //Deprecated method will be replaced by S3Config constructor
    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public FileEntity uploadFile(MultipartFile multipartFile) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.filePath = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);

            /* uploadFileTos3bucket(fileName, file); */
            try{
                s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            }catch (AmazonServiceException ase) {
                logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
                logger.info("Error Message:    " + ase.getMessage());
                logger.info("HTTP Status Code: " + ase.getStatusCode());
                logger.info("AWS Error Code:   " + ase.getErrorCode());
                logger.info("Error Type:       " + ase.getErrorType());
                logger.info("Request ID:       " + ase.getRequestId());
                fileEntity.statusCode = 304;
                fileEntity.uploadStatus = "Service failure.\n"+ ase.getMessage() +" \nPlease try again later.";
            } catch (AmazonClientException ace) {
                logger.info("Caught an AmazonClientException: ");
                logger.info("Error Message: " + ace.getMessage());
                fileEntity.statusCode = 305;
                fileEntity.uploadStatus = "Client error.\n"+ ace.getMessage() +" \nPlease try again later.";
            }
            fileEntity.filePath = endpointUrl + "/" + bucketName + "/" + fileName;
            fileEntity.statusCode = 200;
            fileEntity.uploadStatus = "Upload Successfull";
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            fileEntity.statusCode = 402;
            fileEntity.uploadStatus = "Unknown Exception occurred. Seems to be problem with File Conversion. \nError Message: "+ e.getMessage() +" \nPlease contact the admin team to resolve the issue.";
        }
        return fileEntity;
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        return "Successfully deleted";
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

}


