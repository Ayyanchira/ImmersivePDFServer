package com.ssdi.immersivepdf.dao;

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

public class GetBooksDaoTest {

    Connection connection;
    TestConnectionData connectionData;
    int bookCount;


    @Before
    public void setUp() throws Exception {
        connectionData = new TestConnectionData();
        connection = DBConnector.getConnection(connectionData);

        String sql;
        PreparedStatement pstmt;
        bookCount = 0;
        try {
            connection = DBConnector.getConnection(connectionData);

            //Deleting the user and collection of books from database.
            sql = "SELECT * from BOOKS";
            pstmt = connection.prepareStatement(sql);
            ResultSet resultset = pstmt.executeQuery();
            while (resultset.next()){
                bookCount++;
            }

        }catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllBooks() {
        GetBooksDao getBooksDao = new GetBooksDao();
        try{
            Books resultbooks = getBooksDao.getAllBooks();
            assertTrue(resultbooks.getBookCollection().size() == bookCount);
        }catch (SQLException e){
            assertTrue(false);
        }
    }
}