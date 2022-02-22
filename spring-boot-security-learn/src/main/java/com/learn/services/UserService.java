package com.learn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.models.User;

//Just data
@Service
public class UserService {
	List<User> list = new ArrayList<>();

	UserService(List<User> list) {
		super();
		this.list = list;
	}
	
	public UserService() {
		list.add(new User("abc","abc","ABC@GMAIL.com"));
		list.add(new User("xyz","xyz","XYZ@GMAIL.com"));
	}
	
	//get all users
	public List<User> getAllUSers(){
		return this.list;
	}
	
	//get single user
	public User getUser(String username) {
		User s = null;
		for(User user:list) {
			if(user.getUsername()== username) {
				s = user;
				break;
			}
		}
		return s;
	}
	
	//add user
	public User addUser(User user) {
		this.list.add(user);
		return user;
	}
	
	

}
