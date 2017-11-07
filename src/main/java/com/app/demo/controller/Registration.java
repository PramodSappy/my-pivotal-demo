package com.app.demo.controller;

import com.app.demo.model.User;
import com.app.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * Created by e067411 on 10/30/17.
 */
@RestController
public class Registration {
    @Autowired
    private UserService userService;
    // Return registration form template
   
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User register(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }
}
	