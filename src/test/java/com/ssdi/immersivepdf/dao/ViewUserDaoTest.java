package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.util.DBConnector;
import com.ssdi.immersivepdf.util.TestConnectionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ViewUserDaoTest {

    Connection connection;
    TestConnectionData connectionData;

    @Before
    public void setUp() throws Exception {
        connectionData = new TestConnectionData();
        connection = DBConnector.getConnection(connectionData);

        String sql;
        PreparedStatement pstmt;
        try {
            connection = DBConnector.getConnection(connectionData);

            //Deleting the user and collection of books from database.
            sql = "DELETE FROM USER WHERE email = 'robertdowney@gmail.com'";
            pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();

            //Deleting the user and collection of books from database.
            sql = "DELETE FROM USER WHERE email = 'courtney@gmail.com'";
            pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();

            //Inserting new user
            sql= "INSERT INTO USER(firstname,lastname,email,role,password) VALUES('Robert','Downey','robertdowney@gmail.com','User','password')";
            PreparedStatement pstmt2 = connection.prepareStatement(sql);
            pstmt2.executeUpdate();

            sql= "INSERT INTO USER(firstname,lastname,email,role,password) VALUES('Courtney','Cox','courtney@gmail.com','User','password')";
            pstmt2 = connection.prepareStatement(sql);
            pstmt2.executeUpdate();

            //Fetch user's id
            sql = "SELECT userid from USER WHERE email='robertdowney@gmail.com'";
            pstmt = connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            int userid = 0;
            if (res.first()){
                userid = res.getInt("userid");
            }

            //Entering 2 book entry into the record for newly entered user
            sql = "INSERT INTO BOOKS(bookname,description,userid,location) VALUES('Book1','Test Description of book1',?,'C:/book1')";
            pstmt2 = connection.prepareStatement(sql);
            pstmt2.setInt(1,userid);
            pstmt2.executeUpdate();

            sql = "INSERT INTO BOOKS(bookname,description,userid,location) VALUES('Book2','Test Description of book2',?,'C:/book2')";
            pstmt2 = connection.prepareStatement(sql);
            pstmt2.setInt(1,userid);
            pstmt2.executeUpdate();

        }catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllBooksForUserWithTwoBooks() {
        User user = new User();
        user.setEmail("robertdowney@gmail.com");
        user.setFirstname("Robert");
        user.setLastname("Downey");
        user.setRole("User");
        user.setPassword("password");


        ViewUserDao viewUserDao = new ViewUserDao();
        try{
            Books userBooks = viewUserDao.getAllBooksForUser(user);
            assertTrue(userBooks.getBookCollection().size() == 2);
        }catch (SQLException e){
            assertTrue(false);
        }

    }

    @Test
    public void getAllBooksForUserWithNoBooks() {
        User user = new User();
        user.setEmail("courtney@gmail.com");
        user.setFirstname("Courntey");
        user.setLastname("Cox");
        user.setRole("User");
        user.setPassword("password");


        ViewUserDao viewUserDao = new ViewUserDao();
        try{
            Books userBooks = viewUserDao.getAllBooksForUser(user);
            assertTrue(userBooks.getBookCollection().size() == 0);
        }catch (SQLException e){
            assertTrue(false);
        }

    }

}