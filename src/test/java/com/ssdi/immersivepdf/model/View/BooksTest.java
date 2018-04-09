package com.ssdi.immersivepdf.model.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BooksTest {

    Books books;

    @Before
    public void setUp() throws Exception {
        books = new Books();
        Book book1 = new Book();
        Book book2 = new Book();
        ArrayList<Book> booklist = new ArrayList<Book>();
        booklist.add(book1);
        booklist.add(book2);
        books.setBookCollection(booklist);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getBookCollection() {
        assertTrue(2 == books.getBookCollection().size());

    }

    @Test
    public void setBookCollection() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        ArrayList<Book> booklist = new ArrayList<Book>();
        booklist.add(book1);
        booklist.add(book2);
        booklist.add(book3);
        books.setBookCollection(booklist);

        assertTrue(3 == books.getBookCollection().size());
    }
}