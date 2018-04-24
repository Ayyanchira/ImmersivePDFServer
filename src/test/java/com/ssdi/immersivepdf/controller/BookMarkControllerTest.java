package com.ssdi.immersivepdf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdi.immersivepdf.dao.BookMarkDao;
import com.ssdi.immersivepdf.model.View.Book;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

public class BookMarkControllerTest {
    @Mock
    private BookMarkDao daoMock;
    private MockMvc mockMvc;
    private ObjectMapper obj_mapper;

    @InjectMocks
    private BookMarkController bookMarkController;

    @Before
    public void setUp() throws Exception{
        obj_mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(bookMarkController).build
                ();
    }
    @Test
    public void bookMark_UsingMock() {
        Book book = new Book();
        book.setBookid(5);
        book.setBookmark(10);

        when(daoMock.bookMark(any(Book.class))).thenReturn(200).thenReturn(401);

        assertEquals(200, daoMock.bookMark(book));
        assertEquals(401, daoMock.bookMark(book));
    }

    @Test
    public void testBookMarkMapping(){

        JSONObject request = new JSONObject();

        try {
            request.put("bookid",1);
            request.put("bookmark",6);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{
            mockMvc.perform(post("/bookmark")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

