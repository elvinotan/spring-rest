package com.elvino.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.elvino.rest.impl.UserServiceImpl;
import com.elvino.rest.service.UserService;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}
	
	
	@Bean
	public UserService getUserService() {
		return new UserServiceImpl();
	}

}
