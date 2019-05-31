package com.elvino.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.elvino.rest.service.UserService;

public class ServiceController {

	@Autowired
	protected UserService userService; 
}
