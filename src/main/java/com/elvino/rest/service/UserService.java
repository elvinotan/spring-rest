package com.elvino.rest.service;

import java.util.List;

import com.elvino.rest.model.MenuBean;
import com.elvino.rest.model.RoleBean;
import com.elvino.rest.model.UserBean;

public interface UserService {

	// Module User	
	
	public UserBean getUser(Long id) throws Exception;
	
	public UserBean saveUser(UserBean bean) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
	
	public List<UserBean> getUsers(String name, String email) throws Exception;
	
	
	// Module Menu
	public MenuBean getMenu(Long id) throws Exception;
	
	public MenuBean saveMenu(MenuBean bean) throws Exception;
	
	public void deleteMenu(Long id) throws Exception;
	
	
	// Module Role
	public RoleBean getRole(Long id) throws Exception;
	
	public RoleBean saveRole(RoleBean bean) throws Exception;
	
	public void deleteRole(Long id) throws Exception;
}
