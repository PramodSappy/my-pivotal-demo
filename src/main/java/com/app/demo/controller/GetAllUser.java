package com.app.demo.controller;

import com.app.demo.model.User;
import com.app.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by e067411 on 10/30/17.
 */
@RestController
public class GetAllUser {
	 private static final Logger log = LoggerFactory.getLogger(GetAllUser.class);
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/getAllUsers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
    		log.info("Get All Users...");
        return userService.getAllUser();
    }
}
