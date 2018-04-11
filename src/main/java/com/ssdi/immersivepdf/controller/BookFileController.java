package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.BookEntryDao;
import com.ssdi.immersivepdf.model.fileUpload.FileEntity;
import com.ssdi.immersivepdf.model.generic.Response;
import com.ssdi.immersivepdf.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
public class BookFileController {

    private AmazonClient amazonClient;
    private BookEntryDao bookEntryDao;

    @Autowired
    BookFileController(AmazonClient client) {
        this.amazonClient = client;
    }
//    BookFileController(BookEntryDao bookEntryDao) {this.bookEntryDao = bookEntryDao;}

    @PostMapping("/uploadFile")
    @CrossOrigin(origins = "http://localhost:3000")
    public Response uploadFile(@RequestPart(value = "file") MultipartFile file, String fileName, String description, String email) {

        Response response = new Response();
        //Attempt File Uploading
        FileEntity fileUploadStatusObject = this.amazonClient.uploadFile(file);

        if (fileUploadStatusObject.getStatusCode() == 200){
            //store the filePath in books table
            BookEntryDao bookEntryDao = new BookEntryDao();
            int fileUpdateDaoStatus = bookEntryDao.enterNewBook(fileName,fileUploadStatusObject.getFilePath(),description, email);
            if (fileUpdateDaoStatus == 200){
                response.setStatusCode(200);
                response.setStatusMessage("File successfully uploaded");
            }else {
                response.setStatusCode(303);
                response.setStatusMessage("File upload Failed.");
                System.out.println("File uploaded to Amazon S3 bucket. But Failed to update database.");
                //TO DO : Delete file from S3 bucket
            }
        }else{
            response.setStatusCode(303);
            response.setStatusMessage("File upload failed");
            System.out.println("Upload to Amazon S3 failed");
        }
        return response;
    }



    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }


}
