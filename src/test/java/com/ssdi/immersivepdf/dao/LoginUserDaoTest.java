package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Login.Login;
import com.ssdi.immersivepdf.util.DBConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertTrue;

public class LoginUserDaoTest {

    Connection connection;
    @Before
    public void setUp() throws Exception {
        connection = DBConnector.getConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginEmailId() {
        Login login = new Login();
        login.setEmail("robertdowney@gmail.com");
        login.setPassword("password");
        LoginUserDao loginUserDao = new LoginUserDao();
        boolean result = loginUserDao.login(login);
        assertTrue(result == true);
    }


}
