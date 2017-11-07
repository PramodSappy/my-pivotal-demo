package com.app.demo.controller;

import com.app.demo.Application;
import com.app.demo.BaseMockMVCTest;
import com.app.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"test"})
public class GetAllUserTest extends BaseMockMVCTest {

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setup() throws IOException {
        super.setupRuntime();
    }

    @Test
    public void testGetAdmin() throws Exception{

        MvcResult mvcResult = mockMvc.perform(get("/getAllUsers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        System.out.println("########## "+response);
        List<User> users = Arrays.asList( mapper.readValue(response, User[].class) );

        Assert.assertEquals("first", users.get(0).getFirst_name());
        Assert.assertEquals("last", users.get(0).getLast_name());
    }
}