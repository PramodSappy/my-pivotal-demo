package com.app.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.demo.model.LoginUser;
import com.app.demo.model.UpdateUser;
import com.app.demo.model.User;
import com.app.demo.service.UserService;

/**
 * Created by e067411 on 10/30/17.
 */
@RestController
public class Login {
	private static final Logger log = LoggerFactory.getLogger(GetAllUser.class);

    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public User login(@RequestBody @Valid LoginUser user) {
    		log.info("Login user-"+user.getEmail());
        return userService.findUser(user.getEmail());
    }
    
    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public User updateUserData(@RequestBody @Valid UpdateUser updateUserInfo) {
    		log.info("Update user-"+updateUserInfo.getEmail());
        return userService.updateUser(updateUserInfo);
    }
    

}
