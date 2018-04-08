package com.ssdi.immersivepdf.controller;


import com.ssdi.immersivepdf.dao.RegisterUserDao;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.generic.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRegistrationController {

    @Autowired
    private RegisterUserDao registerUserDao;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Response registerUser(@RequestBody User request){
        Response response = new Response();
        int result = registerUserDao.register(request);
        response.setStatusCode(result);
        if (result == 200){
             response.setStatusMessage("Success");
        }else if(result == 401){
            response.setStatusMessage("Email already registered.");
        }
        else {
            response.setStatusMessage("Failed");
        }
        return response;
    }

}
