package com.ssdi.immersivepdf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdi.immersivepdf.dao.FavUserDao;
import com.ssdi.immersivepdf.model.View.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

public class UserFavControllerTest {


    @Mock
    private FavUserDao daoMock;
    private MockMvc mockMvc;
    private ObjectMapper obj_mapper;

    @InjectMocks
    private UserFavController userFavController;

    @Before
    public void setUp() throws Exception{
        obj_mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userFavController).build
                ();
    }

    @Test
    public void viewBook_UsingMock() {
        Book book = new Book();
        book.setBookid(4);
        book.setIsfavorite(true);

        when(daoMock.markfav(any(Book.class))).thenReturn(200).thenReturn(401);

        assertEquals(200,daoMock.markfav(book));
        assertEquals(401,daoMock.markfav(book));
    }

    @Test
    public void testFavMapping(){

        //User user = mock(User.class);
    }


}
