package com.app.demo.controller;

import com.app.demo.BaseMockMVCTest;
import com.app.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationTest extends BaseMockMVCTest {

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setup() throws IOException {
        super.setupRuntime();
    }

    @Test
    public void testRegistration() throws Exception {
        User user = new User();
        user.setFirst_name("testFirst");
        user.setLast_name("Last");
        user.setAddress("testAddress");
        user.setPassword("pwd");
        user.setEmail("test@email.com");

        String json = mapper.writeValueAsString(user);
        MvcResult mvcResult = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        System.out.println("########## "+response);
        JSONObject jsonObject = mapper.readValue(response, JSONObject.class);

        Assert.assertEquals(user.getFirst_name(), jsonObject.get("first_name"));
    }

}