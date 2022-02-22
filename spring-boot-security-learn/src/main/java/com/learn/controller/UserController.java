package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.models.User;
import com.learn.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	
	//all users
	@GetMapping("/")
	public List<User> getAllUsers(){
		return this.userService.getAllUSers();
	}
	
	//return single user
	//@PreAuthorize("hasRole('ADMIN')") - Access granting only to admin for this method
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	@PostMapping("/")
	public User add(@RequestBody User user) {
		return this.userService.addUser(user);
	}

}
