package com.ssdi.immersivepdf.controller;
import com.ssdi.immersivepdf.dao.LoginUserDao;
import com.ssdi.immersivepdf.model.Login.Login;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class UserLoginController {

    @Autowired
    private LoginUserDao loginUserDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response loginUser(@RequestBody Login request) {

        Response response = new Response();
        User userObject = new User();
        try{
            userObject = loginUserDao.login(request);
            response.setStatusCode(200);
            response.setStatusMessage("User Login Sucessful");
            response.setData(userObject);
        }catch (SQLException e){
            response.setStatusMessage("Failed to login");
            response.setStatusCode(400);
        }
//        if (loginUserDao.login(request)) {
//            response.setStatusMessage("User Login Sucessful");
//            response.setStatusCode(200);
//        } else {
//            response.setStatusMessage("Failed to login");
//            response.setStatusCode(400);
//        }
        return response;
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response validateUser(@RequestBody Login request) {

        Response response = new Response();
        User userObject = new User();
        try{
            userObject = loginUserDao.isValidUser(request);
            response.setStatusCode(200);
            response.setStatusMessage("User is valid");
            response.setData(userObject);
        }catch (SQLException e){
            response.setStatusMessage("No such entry found.");
            response.setStatusCode(400);
        }
        return response;
    }
}