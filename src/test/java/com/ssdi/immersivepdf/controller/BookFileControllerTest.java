package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.model.fileUpload.FileEntity;
import com.ssdi.immersivepdf.model.generic.Response;
import com.ssdi.immersivepdf.service.AmazonClient;
import org.apache.catalina.webresources.FileResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookFileControllerTest {
    private MockMvc mockMvc;

    private static final String BUCKET_NAME = "bucket_name";
    private static final String OBJECT_KEY = "object_key";

    private AmazonClient s3;
    private BookFileController bookFileController = new BookFileController(s3);

    @Before
    public void setUp() {
        s3 = mock(AmazonClient.class);
        //bucket = new S3Bucket(s3, BUCKET_NAME);
        mockMvc = MockMvcBuilders.standaloneSetup(bookFileController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void uploadFileWithMockAmazonClass() {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setStatusCode(200);
        fileEntity.setUploadStatus("Upload Successful");
        fileEntity.setFilePath("C://pdf1.pdf");


        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
        when(s3.uploadFile(file)).thenReturn(fileEntity);

        assertTrue(fileEntity == s3.uploadFile(file));

    }

    @Test
    public void testRequestMappingFunctionality(){
        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

        HashMap<String, String> contentTypeParams = new HashMap<String, String>();
        contentTypeParams.put("fileName", "Hello");
        contentTypeParams.put("description","description");
        contentTypeParams.put("userID","1");


        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);
        Response result =  new Response();
        result.setStatusCode(200);
        when(bookFileController.uploadFile(file,"Hello","description","robertdowney@gmail.com")).thenReturn(result);

        try {
            mockMvc.perform(post("/storage/uploadFile")
                    .contentType(mediaType)
                    .content(file.getBytes())
            )
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}