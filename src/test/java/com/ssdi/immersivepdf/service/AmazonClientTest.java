package com.ssdi.immersivepdf.service;

import com.ssdi.immersivepdf.model.fileUpload.FileEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static org.junit.Assert.*;

public class AmazonClientTest {

    @Autowired
    private AmazonClient amazonClient = new AmazonClient();

    String filepath = "";
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        amazonClient.deleteFileFromS3Bucket(filepath);
    }

    @Test
    public void uploadFile() {
        amazonClient.initializeAmazon();
        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
        FileEntity fileUploasStatus = amazonClient.uploadFile(file);
        assertTrue(200 ==  fileUploasStatus.getStatusCode());
        filepath = fileUploasStatus.getFilePath();
    }

    @Test
    public void deleteFileFromS3Bucket() {

    }
}