package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.ViewUserDao;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class UserViewController {

    @Autowired
    private ViewUserDao viewUserDao;

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:3000")
    public Response viewUserBooks(@RequestBody User request) {

        Response response = new Response();
        try {
            Books userBookCollection = viewUserDao.getAllBooksForUser(request);
            response.setData(userBookCollection);
            response.setStatusMessage("Successful");
            response.setStatusCode(200);
        }catch (SQLException e){
            response.setStatusCode(303);
            response.setStatusMessage("Error while fetching books");
        }
        return response;
    }
}
