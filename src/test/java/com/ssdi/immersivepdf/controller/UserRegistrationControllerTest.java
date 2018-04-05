package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.RegisterUserDao;
import com.ssdi.immersivepdf.model.Register.User;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserRegistrationControllerTest {



    @Test
    public void registerUser() {
       // User user = new Us
        UserRegistrationController controller = new UserRegistrationController();
        User user = new User();
        user.setFirstname("Johny");
        user.setLastname("Depp");
        user.setEmail("johny@gmail.com");
        user.setPassword("password");
        user.setRole("User");

        RegisterUserDao registerDao = mock(RegisterUserDao.class);

        
    }
}