package com.elvino.rest.service;

import java.util.List;

import com.elvino.rest.model.UserBean;

public interface UserService {

	// Module User	
	
	public UserBean getUser(Long id) throws Exception;
	
	public UserBean saveUser(UserBean userBean) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
	
	public List<UserBean> getUsers(String name, String email) throws Exception;
}
