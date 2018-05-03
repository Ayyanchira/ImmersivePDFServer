package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.View.Book;
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
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GetBooksDaoTest {

    Connection connection;
    TestConnectionData connectionData;
    int bookCount;
    ArrayList<Book> bookArray;
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
            ResultSet res = pstmt.executeQuery();
            bookArray = new ArrayList<>();
            while (res.next()){
                bookCount++;
                Book book = new Book();
                book.setBookid(res.getInt("bookid"));
                book.setBookname(res.getString("bookname"));
                book.setDescription(res.getString("description"));
                book.setUserid(res.getInt("userid"));
                book.setLocation(res.getString("location"));
                book.setIsfavorite(res.getBoolean("isfavorite"));
                book.setBookmark(res.getInt("bookmark"));
                bookArray.add(book);
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

            //Check if books parameter is same or not.
            assertTrue(resultbooks.getBookCollection().get(0).getBookname().equals(bookArray.get(0).getBookname()));
        }catch (SQLException e){
            assertTrue(false);
        }
    }
}