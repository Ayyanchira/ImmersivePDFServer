package com.ssdi.immersivepdf.model.Login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    Login login;
    @Before
    public void setUp() throws Exception {
        login = new Login();
        login.setEmail("johncena@gmail.com");
        login.setPassword("uniquePassword");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getEmail() {
        assertTrue(login.getEmail()=="johncena@gmail.com");
    }

    @Test
    public void setEmail() {
        login.setEmail("newEmail@gmail.com");
        assertTrue(login.getEmail()=="newEmail@gmail.com");
    }

    @Test
    public void getPassword() {
        assertTrue(login.getPassword()=="uniquePassword");
    }

    @Test
    public void setPassword() {
        login.setPassword("ultimatePassword");
        assertTrue(login.getPassword()=="ultimatePassword");
    }
}