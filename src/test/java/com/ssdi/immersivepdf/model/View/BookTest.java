package com.ssdi.immersivepdf.model.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book();
        book.setUserid(1);
        book.setIsfavorite(true);
        book.setLocation("https://pdf1.pdf");
        book.setBookname("Harry Potter");
        book.setDescription("Deathly Hollows. The last part of Harry potter series");
        book.setBookid(1);
        book.setBookmark(0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getIsfavorite() {
        assertTrue(true == book.isfavorite);
    }

    @Test
    public void setIsfavorite() {
        book.setIsfavorite(false);
        assertTrue(book.isfavorite == false);
    }

    @Test
    public void getBookid() {
        assertTrue(1 == book.getBookid());
    }

    @Test
    public void setBookid() {
        book.setBookid(100);
        assertTrue(100 == book.getBookid());
    }

    @Test
    public void getBookname() {
        assertTrue(book.getBookname() == "Harry Potter");
    }

    @Test
    public void setBookname() {
        book.setBookname("Da Vinci Code");
        assertTrue(book.getBookname() == "Da Vinci Code");
    }

    @Test
    public void getDescription() {
        assertTrue(book.getDescription() == "Deathly Hollows. The last part of Harry potter series");
    }

    @Test
    public void setDescription() {
        book.setDescription("A world famous book by Dan Brown");
        assertTrue(book.getDescription() == "A world famous book by Dan Brown");
    }

    @Test
    public void getUserid() {
        assertTrue(1 == book.getUserid());
    }

    @Test
    public void setUserid() {
        book.setUserid(1500);
        assertTrue(1500 == book.getUserid());
    }

    @Test
    public void getLocation() {
        assertTrue(book.getLocation() == "https://pdf1.pdf");
    }

    @Test
    public void setLocation() {
        book.setLocation("https://website1/user/pdf1.pdf");
        assertTrue(book.getLocation() == "https://website1/user/pdf1.pdf");
    }

    @Test
    public void getBookmark() {
        assertTrue(book.getBookmark() == 0);
    }

    @Test
    public void setBookmark() {
        book.setBookmark(50);
        assertTrue(50 == book.getBookmark());
    }
}