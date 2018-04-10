package com.ssdi.immersivepdf.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserViewControllerTest {

    private MockMvc mockMvc;
    UserViewController userViewController = new UserViewController();


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userViewController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void viewUserBooks() {
        JSONObject request = new JSONObject();

        try {
            request.put("email","akshay@gmail.com");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            mockMvc.perform(post("/view")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request.toString())).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}