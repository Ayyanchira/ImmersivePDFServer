package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Login.Login;
import com.ssdi.immersivepdf.util.DBConnector;
import com.ssdi.immersivepdf.util.TestConnectionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertTrue;

public class LoginUserDaoTest {

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
    public void loginAnEmailId() {
        Login login = new Login();
        login.setEmail("robertdowney@gmail.com");
        login.setPassword("password");
        LoginUserDao loginUserDao = new LoginUserDao();
        boolean result = loginUserDao.login(login);
        assertTrue(result == true);
    }


}
