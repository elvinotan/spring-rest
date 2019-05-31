package com.elvino.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.elvino.rest.repository.MenuRepository;
import com.elvino.rest.repository.RoleRepository;
import com.elvino.rest.repository.UserRepository;

public class DaoImpl {

	@Autowired
	protected UserRepository userRepo;
	
	@Autowired
	protected RoleRepository roleRepo;
	
	@Autowired
	protected MenuRepository menuRepo;
}
