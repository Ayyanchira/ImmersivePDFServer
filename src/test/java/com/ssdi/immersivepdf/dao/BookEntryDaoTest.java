package com.ssdi.immersivepdf.dao;

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

public class BookEntryDaoTest {

    Connection connection;
    TestConnectionData connectionData;
    int userid = 0;

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

            sql = "SELECT userid from USER WHERE email='robertdowney@gmail.com'";
            pstmt = connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();

            if (res.first()){
                userid = res.getInt("userid");
            }

        }catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void enterNewBook() {
        BookEntryDao bookEntryDao = new BookEntryDao();
        bookEntryDao.enterNewBook("Hello","C:Path","TestDescription",userid);
    }
}