package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.model.View.View;
import com.ssdi.immersivepdf.util.DBConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ViewUserDaoTest {
    Connection connection;
    @Before
    public void setUp() throws Exception {
        connection = DBConnector.getConnection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void viewEmailId() {
        View view = new View();
        view.setEmail("akshay@gmail.com");
        ViewUserDao viewUserDao = new ViewUserDao();
        Books res = viewUserDao.view(view);
        int length = res.getBookCollection().toArray().length;
        assertTrue(length == 2);
    }
}
