package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.View.Book;
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
        bookEntryDao.enterNewBook("TestingBookDelete","C:Path","TestDescription","robertdowney@gmail.com");
    }

    @Test
    public void deleteBookTestMethod(){
        enterNewBook();
        //Fetch user's id
        String sql;
        PreparedStatement pstmt;
        try {
            sql = "SELECT bookid from BOOKS WHERE bookname = 'TestingBookDelete'";
            pstmt = connection.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            int bookid = 0;

            if (res.first()) {
                bookid = res.getInt("bookid");
            }

            Book bookToDelete =  new Book();
            bookToDelete.setBookid(bookid);
            BookEntryDao bookEntryDao = new BookEntryDao();
            int result = bookEntryDao.deleteBook(bookToDelete);
            System.out.println("Result of delete book operation : " + result);
            assertTrue(result == 200);

        }catch (SQLException e){
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void deleteNonExistingBookTestMethod(){
        Book bookToDelete =  new Book();
        bookToDelete.setBookid(1000);
        BookEntryDao bookEntryDao = new BookEntryDao();
        int result = bookEntryDao.deleteBook(bookToDelete);
        System.out.println("Result of delete book operation : " + result);
        assertTrue(result == 400);
    }
}