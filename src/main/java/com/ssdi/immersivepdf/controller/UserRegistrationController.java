package com.ssdi.immersivepdf.controller;


import com.ssdi.immersivepdf.model.RegistrationRequest;
import com.ssdi.immersivepdf.model.RegistrationResponse;
import com.ssdi.immersivepdf.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class UserRegistrationController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public User registerUser(){

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setStatusCode(200);
        registrationResponse.setStatusMessage("Success");
        return new User("Akshay","Ayyanchira","ayyanch@gmail.com","Admin","password");
    }

}
