package com.ssdi.immersivepdf.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdi.immersivepdf.dao.RegisterUserDao;
import com.ssdi.immersivepdf.model.Register.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

public class UserRegistrationControllerTest {

    @Mock
    private RegisterUserDao daoMock;
    private MockMvc mockMvc;
    private ObjectMapper obj_mapper;

    @InjectMocks
    private UserRegistrationController userRegistrationController;

    @Before
    public void setUp() throws Exception{
        obj_mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build
                ();
    }

    @Test
    public void registerUser_UsingMock() {
        User user = new User();
        user.setFirstname("Johny");
        user.setLastname("Depp");
        user.setEmail("johny@gmail.com");
        user.setPassword("password");
        user.setRole("User");

        when(daoMock.register(any(User.class))).thenReturn(200).thenReturn(401);

        assertEquals(200,daoMock.register(user));
        assertEquals(401,daoMock.register(user));
    }

    @Test
    public void testResigtrationMapping(){

        User user = mock(User.class);

//        try{
//            mockMvc.perform(post("/register")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .andExpect(status().isOk());
//            )
//        }
    }
//    @Test
//    public void testRequestMapping(){
//        User user = mock(User.class);
//        session.setAttribute("sessionParm",user);
//        this.mockMvc.perform(get("/register").session(session)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(view().name("test"));
//    }

}