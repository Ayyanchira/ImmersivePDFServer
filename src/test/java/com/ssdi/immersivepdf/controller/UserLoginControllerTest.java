package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.LoginUserDao;
import com.ssdi.immersivepdf.model.Login.Login;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserLoginControllerTest {

    @Mock
    private LoginUserDao loginUserDao;

    @InjectMocks
    private UserLoginController userLoginController;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginUser_WithMock() {
        Login loginRequest = new Login();
        loginRequest.setEmail("brucewayne@gmail.com");
        loginRequest.setPassword("batman");
        when(loginUserDao.login(loginRequest)).thenReturn(true);
        assertTrue(loginUserDao.login(loginRequest));
    }

    @Test
    public void loginUserInvalid_WithMock() {
        Login loginRequest = new Login();
        when(loginUserDao.login(loginRequest)).thenReturn(false);
        assertTrue(loginUserDao.login(loginRequest) == false);
    }
}