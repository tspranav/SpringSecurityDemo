package com.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.learn.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true) -- for enabling preauthorization of methods -- alternative to antMatacher
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    // .csrf.disable()
		     .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		     .and()
		     .authorizeRequests() //authorize requests
		    // .antMatchers("/public/**").permitAll() - for making it accessible by all
		     .antMatchers("/public/**").hasRole("NORMAL") //Allows the links with /public/** to be accessed by ROLE_NORMAL users only
		     .antMatchers("/users/**").hasRole("ADMIN")   //Allows the links with /users/** to be accessed by ROLE_ADMIN users only
		     .anyRequest() //all request
		     .authenticated() //authenticate
		     .and()
		     .formLogin(); // basic authentication mechanism
	}
	
	//Custom defined login credentials(username & password)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("john").password("durgesh").roles("NORMAL"); -- NoOpPass encoder
		/*
		 * auth.inMemoryAuthentication().withUser("john").password(this.passwordEncoder(
		 * ).encode("durgesh")).roles("NORMAL"); // for Bcrypt pass encoder
		 * auth.inMemoryAuthentication().withUser("roshni").password(this.
		 * passwordEncoder().encode("abc")).roles("ADMIN");
		 */
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder()); //authenticating the users of CustomUserDetailsService interface
	}
    
	//Defining Encoder type to store the passwords
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	//	return NoOpPasswordEncoder.getInstance(); - store password just as plain text without any encoding
		return new BCryptPasswordEncoder(10); //10-- Strength of Bcrypt encoder
	}
	
	
	
	
}
