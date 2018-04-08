package com.ssdi.immersivepdf.controller;
import com.ssdi.immersivepdf.dao.LoginUserDao;
import com.ssdi.immersivepdf.model.Login.Login;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    @Autowired
    private LoginUserDao loginUserDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response loginUser(@RequestBody Login request) {

        Response response = new Response();
        if (loginUserDao.login(request)) {
            response.setStatusMessage("User Login Sucessful");
            response.setStatusCode(200);
        } else {
            response.setStatusMessage("Failed to login");
            response.setStatusCode(400);
        }
        return response;
    }
}