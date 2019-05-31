package com.elvino.rest.impl;

import com.elvino.rest.model.UserBean;
import com.elvino.rest.service.UserService;

public class UserServiceImpl extends DaoImpl implements UserService{

	@Override
	public UserBean getUser(Long id) throws Exception {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public UserBean saveUser(UserBean userBean) throws Exception {
		return userRepo.save(userBean);
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		userRepo.deleteById(id);
	}

}