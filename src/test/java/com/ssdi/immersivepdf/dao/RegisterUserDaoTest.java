package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.util.DBConnector;
import com.ssdi.immersivepdf.util.TestConnectionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class RegisterUserDaoTest {

    Connection connection;
    TestConnectionData connectionData;
    @Before
    public void setUp() throws Exception {
        connectionData = new TestConnectionData();
        connection = DBConnector.getConnection(connectionData);
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            String sql = "DELETE FROM USER WHERE email = 'robertdowney@gmail.com'";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();

            User user = new User();
            user.setEmail("akshay@gmail.com");
            user.setFirstname("Akshay");
            user.setLastname("Ayyanchira");
            user.setRole("User");
            user.setPassword("password");

            sql= "INSERT INTO USER(firstname,lastname,email,role,password) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt2 = connection.prepareStatement(sql);
            pstmt2.setString(1, user.getFirstname());
            pstmt2.setString(2, user.getLastname());
            pstmt2.setString(3, user.getEmail());
            pstmt2.setString(4, "User");
            pstmt2.setString(5, user.getPassword());
            pstmt2.executeUpdate();
        }catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void registerFreshNewUser() {
        User user = new User();
        user.setEmail("robertdowney@gmail.com");
        user.setFirstname("Robert");
        user.setLastname("Downey");
        user.setRole("User");
        user.setPassword("password");
        RegisterUserDao registerUserDao = new RegisterUserDao();
        int result = registerUserDao.register(user);
        assertTrue(result == 200);
    }

    @Test
    public void registerWithExistingEmailAddress() {
        User user = new User();
        user.setEmail("akshay@gmail.com");
        user.setFirstname("Akshay");
        user.setLastname("Ayyanchira");
        user.setRole("User");
        user.setPassword("password");
        RegisterUserDao registerUserDao = new RegisterUserDao();
        int result = registerUserDao.register(user);
        assertTrue(result == 401);
    }


}