package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.util.DBConnector;
import com.ssdi.immersivepdf.util.TestConnectionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class FavUserDaoTest {

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
            //Deleting the user and the user's collection of books from test database
            sql = "DELETE FROM USER WHERE email = 'test1@gmail.com'";
            pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();

            //Inserting the user again to USER table
            sql= "INSERT INTO USER(firstname,lastname,email,role,password) VALUES('rakshit','rao','test1@gmail.com','User','password')";
            PreparedStatement pstmt2 = connection.prepareStatement(sql);
            pstmt2.executeUpdate();

            //Fetch user id
            sql = "SELECT userid from USER WHERE email='test1@gmail.com'";
            PreparedStatement pstmt3 = connection.prepareStatement(sql);
            ResultSet res = pstmt3.executeQuery();
            int userid = 0;
            if (res.first()){
                userid = res.getInt("userid");
            }

            //Entering a book entry for new user
            sql = "INSERT INTO BOOKS(bookname,description,userid,location) VALUES('Rao','Rao description',?,'C:/rao')";
            PreparedStatement pstmt4 = connection.prepareStatement(sql);
            pstmt4.setInt(1, userid);
            pstmt4.executeUpdate();

            //Fetch book id
            sql = "SELECT bookid FROM BOOKS b JOIN USER u on b.userid = u.userid and email='test1@gmail.com'";
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
    public void markBookAsFavorite() {
        Book book = new Book();
        book.setBookid(bookid);
        book.setIsfavorite(true);

        FavUserDao favUserDao = new FavUserDao();
        int result = favUserDao.markfav(book);
        assertTrue(200 == result);

    }

    @Test
    public void unmarkBookFromFavorite() {
        Book book = new Book();
        book.setBookid(bookid);
        book.setIsfavorite(false);

        FavUserDao favUserDao = new FavUserDao();
        int result = favUserDao.markfav(book);
        assertTrue(200 == result);
    }
}

