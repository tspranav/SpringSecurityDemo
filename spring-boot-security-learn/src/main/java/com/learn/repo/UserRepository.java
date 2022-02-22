package com.learn.repo;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.learn.models.User;

public interface UserRepository extends MongoRepository<User,String>{
	
	public User findByUsername(String username);
}
