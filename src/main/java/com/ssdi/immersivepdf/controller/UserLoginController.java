package com.ssdi.immersivepdf.controller;
import com.ssdi.immersivepdf.dao.LoginUserDao;
import com.ssdi.immersivepdf.model.Login.LoginResponse;
import com.ssdi.immersivepdf.model.Login.Login;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class UserLoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse loginUser(@RequestBody Login request) {
        System.out.println("User login");

        LoginUserDao loginUserDao = new LoginUserDao();
        LoginResponse response = new LoginResponse();
        if (loginUserDao.login(request)) {
            response.setStatusMessage("Success");
            response.setStatusCode(200);
        } else {
            response.setStatusMessage("Failed");
            response.setStatusCode(400);
        }
        return response;
    }
}