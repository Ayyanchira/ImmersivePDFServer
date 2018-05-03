package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.BookEntryDao;
import com.ssdi.immersivepdf.dao.GetBooksDao;
import com.ssdi.immersivepdf.dao.UserDataDao;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.model.admin.Users;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.apache.http.client.methods.RequestBuilder.post;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AdminViewControllerTest {

    @Mock
    private UserDataDao userDataDao;
    @Mock
    private GetBooksDao getBooksDao;
    @Mock
    private BookEntryDao bookEntryDao;

    private MockMvc mockMvc;

    @InjectMocks
    private AdminViewController adminViewController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminViewController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllUserMvcMappingTest(){

        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("bookid",1);
            request.put("isfavorite",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/getUsers")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllUserDaoCallusingController(){
        try {
            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                    User user1 = new User("Courtney","Downey","robert@gmail.com","password",true);
                    User user2 = new User("Robert","Downey","robert@gmail.com","password",true);
                    Users users = new Users();
                    ArrayList<User> userlist = new ArrayList<>();
                    userlist.add(user1); userlist.add(user2);
                    users.setUsers(userlist);
                    return users;
                }
            }).when(userDataDao).getAllUsers();
            User user = new User("Robert","Downey","robert@gmail.com","password",true);
            adminViewController.getAllUsers(user);
            verify(userDataDao,times(1)).getAllUsers();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


    @Test
    public void getAllUsersUsingMock() {
        Users users = new Users();
        User user = new User("Robert","Downey","robert@gmail.com","password",true);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        users.setUsers(userList);
        try{
            when(userDataDao.getAllUsers()).thenReturn(users);
            assertTrue(userDataDao.getAllUsers().getUsers().size() == 1);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    // Test methods for fetching all books.
    @Test
    public void getAllBooksMvcMappingTest(){
        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("userid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/getAllBooks")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAllBooksUsingMock() {

        Book book = new Book();
        ArrayList<Book> booksarray = new ArrayList<>();
        booksarray.add(book);
        Books booksCollection = new Books();
        booksCollection.setBookCollection(booksarray);

        try{
            when(getBooksDao.getAllBooks()).thenReturn(booksCollection);
            Books resultantBooks = getBooksDao.getAllBooks();
            assertTrue(resultantBooks.getBookCollection().size() == 1);
        }catch (Exception e){
            assertFalse(true);
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void getAllBooksDaoTestUsingMock() {

        try {
            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                    Book book1 = new Book();
                    Books bookList = new Books();
                    ArrayList<Book> bookArrayList =  new ArrayList<>();
                    bookArrayList.add(book1);
                    bookList.setBookCollection(bookArrayList);
                    return bookList;
                }
            }).when(getBooksDao).getAllBooks();
            User user = new User("Robert","Downey","robert@gmail.com","password",true);
            adminViewController.viewUserBooks(user);
            verify(getBooksDao,times(1)).getAllBooks();
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            assertFalse(true);
        }
    }


    @Test
    public void deleteUserMvcMappingTest(){

        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("userid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/deleteUser")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserUsingMock() {
        Users users = new Users();
        User user = new User("Robert","Downey","robert@gmail.com","password",true);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        users.setUsers(userList);
        try{
            when(userDataDao.deleteUser(user)).thenReturn(true).thenReturn(false);
            assertTrue(userDataDao.deleteUser(user) == true);
            assertTrue(userDataDao.deleteUser(user) == false);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void deleteUserDaoTestUsingMock() {
        User user1 = new User("Robert","Downey","robert@gmail.com","password",true);
        try {
            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                   return true;
                }
            }).when(userDataDao).deleteUser(user1);
//            User user = new User("Robert","Downey","robert@gmail.com","password",true);
            adminViewController.deleteUser(user1);
            verify(userDataDao,times(1)).deleteUser(user1);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            assertFalse(true);
        }
    }


    @Test
    public void grantPrivilegeMvcMappingTest(){
        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("userid",1);
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/grantPrivilege")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void grantPrivilegeUsingMock() {

        User user = new User("Robert","Downey","robert@gmail.com","password",true);

        try{
            when(userDataDao.allowPasswordReset(user)).thenReturn(true).thenReturn(false);
            assertTrue(userDataDao.allowPasswordReset(user) == true);
            assertTrue(userDataDao.allowPasswordReset(user) ==  false);
        }catch (Exception e){
            assertFalse(true);
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void grantPrivilegeTestDaoUsingMock() {
        User user = new User("Robert","Downey","robert@gmail.com","password",true);

        try {
            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                    return false;
                }
            }).when(userDataDao).allowPasswordReset(user);
            adminViewController.grantPrivilege(user);
            verify(userDataDao,times(1)).allowPasswordReset(user);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            assertFalse(true);
        }
    }


    @Test
    public void deleteBookMvcMappingTest(){
        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("userid",1);
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/deleteBook")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteBookUsingMock() {
        Book book = new Book();
        try{
            when(bookEntryDao.deleteBook (book)).thenReturn(200).thenReturn(400).thenReturn(401);
            assertTrue(bookEntryDao.deleteBook(book) == 200);
            assertTrue(bookEntryDao.deleteBook(book) == 400);
            assertTrue(bookEntryDao.deleteBook(book) == 401);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void deleteBookTestDaoUsingMock() {
        Book book = new Book();
        try {
            doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                    return 0;
                }
            }).when(bookEntryDao).deleteBook(book);
            adminViewController.deleteBook(book);
            verify(bookEntryDao,times(1)).deleteBook(book);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            assertFalse(true);
        }
    }

}