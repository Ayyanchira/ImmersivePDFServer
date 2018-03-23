package com.ssdi.immersivepdf.controller;


import com.ssdi.immersivepdf.dao.RegisterUserDao;
import com.ssdi.immersivepdf.model.Register.RegistrationResponse;
import com.ssdi.immersivepdf.model.Register.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class UserRegistrationController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:3000")
    public RegistrationResponse registerUser(@RequestBody User request){
        System.out.println("Registering new user...");

        RegisterUserDao registerUserDao =  new RegisterUserDao();
        RegistrationResponse response = new RegistrationResponse();
        if (registerUserDao.register(request)){
             response.setStatusMessage("Success");
             response.setStatusCode(200);
        }
        else {
            response.setStatusMessage("Failed");
            response.setStatusCode(400);
        }
        return response;
           //return new User("Akshay1","Ayyanchira","ayyanch@gmail.com","Admin","password");
    }

}
