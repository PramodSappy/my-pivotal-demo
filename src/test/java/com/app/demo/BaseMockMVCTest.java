package com.app.demo;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import redis.embedded.RedisServer;

import java.io.IOException;

public class BaseMockMVCTest {

    private RedisServer redisServer;

    @Value("${spring.redis.port}")
    private int redisPort;

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public void setupRuntime() throws IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        redisServer = new RedisServer(redisPort);
        redisServer.start();;
    }

    @After
    public void tearDown() throws Exception {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
