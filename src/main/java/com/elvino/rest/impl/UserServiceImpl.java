package com.elvino.rest.impl;

import java.util.List;

import com.elvino.rest.model.MenuBean;
import com.elvino.rest.model.RoleBean;
import com.elvino.rest.model.UserBean;
import com.elvino.rest.service.UserService;

public class UserServiceImpl extends DaoImpl implements UserService{

	@Override
	public UserBean getUser(Long id) throws Exception {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public UserBean saveUser(UserBean bean) throws Exception {
		return userRepo.save(bean);
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		userRepo.deleteById(id);
	}
	
	@Override
	public List<UserBean> getUsers(String name, String email) throws Exception {
		return userRepo.findUserCriteria(name, email);
	}

	@Override
	public MenuBean getMenu(Long id) throws Exception {
		return menuRepo.findById(id).orElse(null);
	}

	@Override
	public MenuBean saveMenu(MenuBean bean) throws Exception {
		return menuRepo.save(bean);
	}

	@Override
	public void deleteMenu(Long id) throws Exception {
		menuRepo.deleteById(id);
	}

	@Override
	public RoleBean getRole(Long id) throws Exception {
		return roleRepo.findById(id).orElse(null);
	}

	@Override
	public RoleBean saveRole(RoleBean bean) throws Exception {
		return roleRepo.save(bean);
	}

	@Override
	public void deleteRole(Long id) throws Exception {
		roleRepo.deleteById(id);
	}
}