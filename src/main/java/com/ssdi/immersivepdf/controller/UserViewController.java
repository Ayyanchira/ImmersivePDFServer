package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.ViewUserDao;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.model.View.ViewResponse;
import com.ssdi.immersivepdf.model.View.View;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserViewController {
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:3000")
    public Books viewUser(@RequestBody View request) {
        System.out.println("View PDFs");

        ViewUserDao viewUserDao = new ViewUserDao();
        ViewResponse response = new ViewResponse();

        Books userBookCollection = viewUserDao.view(request);
        return userBookCollection;
//        if (viewUserDao.view(request)) {
//            response.setStatusMessage("Success");
//            response.setStatusCode(200);
//        } else {
//            response.setStatusMessage("Failed");
//            response.setStatusCode(400);
//        }
//        return response;
    }
}
