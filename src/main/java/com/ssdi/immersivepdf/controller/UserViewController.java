package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.ViewUserDao;
import com.ssdi.immersivepdf.model.View.ViewResponse;
import com.ssdi.immersivepdf.model.View.View;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserViewController {
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:3000")
    public ViewResponse viewUser(@RequestBody View request) {
        System.out.println("View PDFs");

        ViewUserDao viewUserDao = new ViewUserDao();
        ViewResponse response = new ViewResponse();
        if (viewUserDao.view(request)) {
            response.setStatusMessage("Success");
            response.setStatusCode(200);
        } else {
            response.setStatusMessage("Failed");
            response.setStatusCode(400);
        }
        return response;
    }
}
