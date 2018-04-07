package com.ssdi.immersivepdf.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssdi.immersivepdf.service.AmazonClient;
import com.ssdi.immersivepdf.service.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/storage/")
public class BookFileController {

    private AmazonClient amazonClient;

    @Autowired
    BookFileController(AmazonClient client) {
        this.amazonClient = client;
    }

    @Autowired
    S3Services s3Services;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        String filename = file.getName();
        File fileToUpload = null;
        try {
            fileToUpload = convertMultiPartToFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        s3Services.uploadFile(filename,fileToUpload);
        System.out.println("File Upload Successful");
        return this.amazonClient.uploadFile(file);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }


}
