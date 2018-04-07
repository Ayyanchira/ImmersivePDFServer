package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.util.DBConnector;
import com.ssdi.immersivepdf.util.TestConnectionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class RegisterUserDaoTest {

    Connection connection;
    TestConnectionData connectionData;
    @Before
    public void setUp() throws Exception {
        connectionData = new TestConnectionData();
        connection = DBConnector.getConnection(connectionData);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void registerAnEmailId() {
        User user = new User();
        user.setEmail("robertdowney@gmail.com");
        user.setFirstname("Robert");
        user.setLastname("Downey");
        user.setRole("User");
        user.setPassword("password");
        RegisterUserDao registerUserDao = new RegisterUserDao();
        int result = registerUserDao.register(user);
        assertTrue(result == 200 || result == 401);
    }


}