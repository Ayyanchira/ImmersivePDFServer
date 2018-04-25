package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.controller.UserLoginController;
import com.ssdi.immersivepdf.model.Login.Login;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.util.DBConnector;
import com.ssdi.immersivepdf.util.TestConnectionData;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginUserDaoTest {

    Connection connection;
    TestConnectionData connectionData;
    //UserLoginController loginController = new UserLoginController();
    @Before
    public void setUp() throws Exception {
        connectionData = new TestConnectionData();
        connection = DBConnector.getConnection(connectionData);
        try {
            this.connection = DBConnector.getConnection(this.connectionData);
            String sql = "DELETE FROM USER WHERE email = 'robertdowney@gmail.com'";
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            pstmt.executeUpdate();

            sql = "INSERT INTO USER(firstname,lastname,email,role,password) VALUES('Robert','Downey','robertdowney@gmail.com','User','password')";
            PreparedStatement pstmt2 = this.connection.prepareStatement(sql);
            pstmt2.executeUpdate();
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }
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

        try{
            User userResult = loginUserDao.login(login);
            System.out.println("Login performed successfully for " + userResult.getEmail());
            assertTrue(true);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            assertTrue(false);
        }


    }

    @Test
    public void loginInvalidCredentials() {
        Login login = new Login();
        login.setEmail("robertdowney@gmail.com");
        login.setPassword("123");
        LoginUserDao loginUserDao = new LoginUserDao();
        User resultUser = new User();
        try{
            resultUser = loginUserDao.login(login);
            assertTrue(false);
        }catch (SQLException e){
            assertTrue(true);
        }
    }

    @Test
    public void validateUserTestMethod() {
        Login login = new Login();
        login.setEmail("robertdowney@gmail.com");
        LoginUserDao loginUserDao = new LoginUserDao();
        User resultUser = new User();
        try{
            resultUser = loginUserDao.isValidUser(login);
            assertTrue(true);
        }catch (SQLException e){
            assertFalse(true);
        }
    }

    @Test
    public void invalidatedUserTestMethod() {
        Login login = new Login();
        login.setEmail("robertdowney@gmail.comasd");
        LoginUserDao loginUserDao = new LoginUserDao();
        User resultUser = new User();
        try{
            resultUser = loginUserDao.isValidUser(login);
            assertTrue(false);
        }catch (SQLException e){
            assertTrue(true);
        }
    }




}
