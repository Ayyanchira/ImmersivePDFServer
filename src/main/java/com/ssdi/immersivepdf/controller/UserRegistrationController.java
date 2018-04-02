package com.ssdi.immersivepdf.controller;


import com.ssdi.immersivepdf.dao.RegisterUserDao;
import com.ssdi.immersivepdf.model.Register.RegistrationResponse;
import com.ssdi.immersivepdf.model.Register.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@Configuration
@EnableAutoConfiguration
public class UserRegistrationController {

    private RegisterUserDao registerUserDao;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public RegistrationResponse registerUser(@RequestBody User request){
        System.out.println("Registering new user...");
        registerUserDao =  new RegisterUserDao();
        RegistrationResponse response = new RegistrationResponse();
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
           //return new User("Akshay1","Ayyanchira","ayyanch@gmail.com","Admin","password");
    }

}
