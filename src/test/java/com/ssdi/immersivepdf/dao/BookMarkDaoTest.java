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

public class BookMarkDaoTest {

    Connection connection;
    TestConnectionData connectionData;
    int bookid;
    @Before
    public void setUp() throws Exception {
        connectionData = new TestConnectionData();
        connection = DBConnector.getConnection(connectionData);
        String sql;
        PreparedStatement pstmt;
        try {
            Connection connection = DBConnector.getConnection(connectionData);
            sql = "SELECT bookid FROM BOOKS b JOIN USER u on b.userid = u.userid and email='robertdowney@gmail.com'";
            PreparedStatement pstmt5 = connection.prepareStatement(sql);
            ResultSet res1 = pstmt5.executeQuery();
            bookid = 0;
            if (res1.first()){
                bookid = res1.getInt("bookid");
            }

        }catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void bookMarkPage() {
        Book book = new Book();
        book.setBookid(bookid);
        book.setBookmark(6);

        BookMarkDao bookMarkDao = new BookMarkDao();
        int result = bookMarkDao.bookMark(book);
        assertTrue(200 == result);
    }
}