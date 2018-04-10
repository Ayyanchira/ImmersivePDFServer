package com.ssdi.immersivepdf.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;

import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
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
    private String endpointUrl= "https://s3.us-east-2.amazonaws.com";
    @Value("${amazonProperties.bucketName}")
    private String bucketName = "ssd-immersivepdf-production-storage";
    @Value("${amazonProperties.accessKey}")
    private String accessKey = "AKIAJ37CXUT5TRJQ2XXQ";
    @Value("${amazonProperties.secretKey}")
    private String secretKey ="UwnfYko01CzyVH4FHO6Rqk2thIlDdziOFMWeOhqn";


    //Deprecated method will be replaced by S3Config constructor
    @PostConstruct
    public void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
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
        fileEntity.setFilePath("");
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
                fileEntity.setStatusCode(304);
                fileEntity.setUploadStatus( "Service failure.\n"+ ase.getMessage() +" \nPlease try again later.");
            } catch (AmazonClientException ace) {
                logger.info("Caught an AmazonClientException: ");
                logger.info("Error Message: " + ace.getMessage());
                fileEntity.setStatusCode(305);
                fileEntity.setUploadStatus("Client error.\n"+ ace.getMessage() +" \nPlease try again later.");
            }
            fileEntity.setFilePath(endpointUrl + "/" + bucketName + "/" + fileName);
            fileEntity.setStatusCode(200);
            fileEntity.setUploadStatus("Upload Successfull");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            fileEntity.setStatusCode(402);
            fileEntity.setUploadStatus("Unknown Exception occurred. Seems to be problem with File Conversion. \nError Message: "+ e.getMessage() +" \nPlease contact the admin team to resolve the issue.");
        }
        return fileEntity;
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        try {
            s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        }catch (AmazonServiceException e){
            System.out.println(e.getMessage());
        }catch (SdkClientException e){
            System.out.println(e.getLocalizedMessage());
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return "Successfully deleted";
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

}


