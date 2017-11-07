package com.app.demo.service;

import com.app.demo.model.UpdateUser;
import com.app.demo.model.User;
import com.app.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by e067411 on 10/30/17.
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@CacheEvict(value="allusers", allEntries=true)
	@CachePut(value="sessionUser", key="#user.email")
	public User saveUser(User user) {
		log.info("Save user invoked");
		return userRepository.save(user);
	}
	
	@Cacheable(value = "allusers")
	public List<User> getAllUser() {
		log.info("getAllUser  invoked");
		Iterable<User> rows = userRepository.findAll();
		List<User> result = new ArrayList<User>();
		for (User row : rows) {
			result.add(row);
		}
		return result;
	}

	@Cacheable(value = "sessionUser", key ="#email")
	public User findUser(String email) {
		log.info("findUser  invoked == " + email);
		User user = userRepository.findOne(email);
		if (user != null)
			return user;
		else
			return null;
	}

	@CachePut(value="sessionUser" , key="#updateUser.email")
	public User updateUser(UpdateUser updateUser) {
		log.info("update  invoked == " + updateUser.getEmail());
		User user = userRepository.findOne(updateUser.getEmail());
		user.setFirst_name(updateUser.getFirstName());
		log.info("user found == " + user);
		
		return userRepository.save(user);
	}
}
