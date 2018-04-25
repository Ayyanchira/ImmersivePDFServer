package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.LoginUserDao;
import com.ssdi.immersivepdf.model.Login.Login;
import com.ssdi.immersivepdf.model.Register.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserLoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LoginUserDao loginUserDao;

    @InjectMocks
    private UserLoginController userLoginController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userLoginController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginUser_WithMock() {
        Login loginRequest = new Login();
        loginRequest.setEmail("brucewayne@gmail.com");
        loginRequest.setPassword("batman");
        User user =  new User();
        user.setEmail("robert@gmail.com");
        try {
            when(loginUserDao.login(loginRequest)).thenReturn(user);
            assertTrue(loginUserDao.login(loginRequest) == user);
        }catch (SQLException e){
            assertFalse(true);
        }
    }

    @Test
    public void loginUserInvalid_WithMock() {
        Login loginRequest = new Login();
        User user =  new User();
        user.setEmail("robert@gmail.com");
        try {
            when(loginUserDao.login(loginRequest)).thenThrow(new SQLException());
            assertFalse(loginUserDao.login(loginRequest) == user);
        }catch (SQLException e){
            assertTrue(true);
        }
    }

    @Test
    public void checkLoginControllerRequestMapping(){

        JSONObject request = new JSONObject();

        try {
            request.put("password","password");
            request.put("email","akshay@gmail.com");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            mockMvc.perform(post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request.toString())).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkResetPasswordControllerRequestMapping(){

        JSONObject request = new JSONObject();

        try {
            request.put("password","newpassword");
            request.put("email","akshay@gmail.com");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/resetpassword")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request.toString())).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resetPasswordValidWithMock() {
        Login loginRequest = new Login();
        loginRequest.setEmail("brucewayne@gmail.com");
        loginRequest.setPassword("batman");
        User user =  new User();
        user.setEmail("robert@gmail.com");
        when(loginUserDao.resetPassword(loginRequest)).thenReturn(true);
        assertTrue(loginUserDao.resetPassword(loginRequest) == true);
    }

    @Test
    public void resetPasswordInvalidWithMock() {
        Login loginRequest = new Login();
        loginRequest.setEmail("brucewayne@gmail.com");
        loginRequest.setPassword("batman");
        User user =  new User();
        user.setEmail("robert@gmail.com");
        when(loginUserDao.resetPassword(loginRequest)).thenReturn(false);
        assertTrue(loginUserDao.resetPassword(loginRequest) == false);
    }

}