package com.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learn.models.User;
import com.learn.repo.UserRepository;

@SpringBootApplication
public class SpringBootSecurityLearnApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityLearnApplication.class, args);
	}
	// Loading the user credentials using CommandLineRunner interface as it executes automatically when the project is run.
	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUsername("john");
		user.setPassword(this.bCryptPasswordEncoder.encode("durgesh"));
		user.setEmail("john@gmail.com");
		user.setRole("ROLE_NORMAL");
		this.userRepository.save(user);
		
		User user1 = new User();
		user1.setUsername("roshni");
		user1.setPassword(this.bCryptPasswordEncoder.encode("durgesh"));
		user1.setEmail("roshni@gmail.com");
		user1.setRole("ROLE_ADMIN");
		this.userRepository.save(user1);
		
	}

}
 