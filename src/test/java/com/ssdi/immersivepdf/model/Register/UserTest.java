package com.ssdi.immersivepdf.model.Register;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User user;
    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setFirstname("Johny");
        user.setLastname("Depp");
        user.setEmail("johny@gmail.com");
        user.setPassword("password");
        user.setRole("User");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getFirstname() {
        assertTrue(user.getFirstname()=="Johny");
    }

    @Test
    public void setFirstname() {
        user.setFirstname("Hrithik");
        assertTrue(user.getFirstname() == "Hrithik");
    }

    @Test
    public void getLastname() {
        assertTrue(user.getLastname() == "Depp");
    }

    @Test
    public void setLastname() {
        user.setFirstname("Roshan");
        assertTrue(user.getFirstname() == "Roshan");
    }

    @Test
    public void getEmail() {
        assertTrue(user.getEmail() == "johny@gmail.com");
    }

    @Test
    public void setEmail() {
        user.setEmail("newjohny@gmail.com");
        assertTrue(user.getEmail() == "newjohny@gmail.com");
    }

    @Test
    public void getRole() {
        assertTrue(user.getRole() == "User");
    }


    @Test
    public void getPassword() {
        assertTrue(user.getPassword() == "password");
    }

    @Test
    public void setPassword() {
        user.setPassword("NewPassword");
        assertTrue(user.getPassword() == "NewPassword");
    }
}