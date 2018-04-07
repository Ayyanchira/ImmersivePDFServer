package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.RegisterUserDao;
import com.ssdi.immersivepdf.model.Register.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRegistrationControllerTest {


    @Mock
    private RegisterUserDao daoMock;

    @InjectMocks
    private UserRegistrationController userRegistrationController;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }



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

        when(daoMock.register(any(User.class))).thenReturn(1);

        RegisterUserDao registerDao = mock(RegisterUserDao.class);
        //when(registerDao.register(User user)).then(2);


        
    }
}