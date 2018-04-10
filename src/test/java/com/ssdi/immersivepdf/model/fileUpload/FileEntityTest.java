package com.ssdi.immersivepdf.model.fileUpload;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileEntityTest {

    FileEntity fileEntity;

    @Before
    public void setUp() throws Exception {
        fileEntity = new FileEntity();
        fileEntity.setFilePath("C:/PDF1.pdf");
        fileEntity.setUploadStatus("Successfully uploaded");
        fileEntity.setStatusCode(200);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getStatusCode() {
        assertTrue(200 == fileEntity.getStatusCode());
    }

    @Test
    public void setStatusCode() {
        fileEntity.setStatusCode(400);
        assertTrue(400 == fileEntity.getStatusCode());
    }

    @Test
    public void getUploadStatus() {
        assertTrue("Successfully uploaded" == fileEntity.getUploadStatus());
    }

    @Test
    public void setUploadStatus() {
        fileEntity.setUploadStatus("Failed");
        assertTrue("Failed" == fileEntity.getUploadStatus());
    }

    @Test
    public void getFilePath() {
        assertTrue("C:/PDF1.pdf" == fileEntity.getFilePath());
    }

    @Test
    public void setFilePath() {
        fileEntity.setFilePath("D:/FolderNew/pdf1.pdf");
        assertTrue("D:/FolderNew/pdf1.pdf" == fileEntity.getFilePath());
    }
}