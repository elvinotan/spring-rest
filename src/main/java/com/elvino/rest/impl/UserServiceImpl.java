package com.elvino.rest.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elvino.rest.model.AuditTrail;
import com.elvino.rest.model.MenuBean;
import com.elvino.rest.model.RelativeBean;
import com.elvino.rest.model.RoleBean;
import com.elvino.rest.model.UserBean;
import com.elvino.rest.service.UserService;

@Service("userService")
public class UserServiceImpl extends DaoImpl implements UserService{

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public UserBean getUser(Long id) throws Exception {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserBean saveUser(UserBean bean) throws Exception {
		AuditTrail auditTrail = new AuditTrail();
		if (bean.getId() == null) {
			auditTrail.setCreatedBy("system");
			auditTrail.setCreatedDate(new Date());
		}else {
			auditTrail.setModifiedBy("system");
			auditTrail.setModifiedDate(new Date());
		}
		bean.setAuditTrail(auditTrail);
		
		for (RelativeBean relative : bean.getRelatives()) {
			relative.setAuditTrail(bean.getAuditTrail());
		}
		
		bean = userRepo.save(bean);
		for (RelativeBean relative : bean.getRelatives()) {
			relative.setUser(new UserBean(bean.getId()));
		}
		
		return bean;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(Long id) throws Exception {
		userRepo.deleteById(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UserBean> getUsers(String name, String email) throws Exception {
		return userRepo.findUserCriteria(name, email);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public MenuBean getMenu(Long id) throws Exception {
		return menuRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MenuBean saveMenu(MenuBean bean) throws Exception {
		return menuRepo.save(bean);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteMenu(Long id) throws Exception {
		menuRepo.deleteById(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public RoleBean getRole(Long id) throws Exception {
		return roleRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RoleBean saveRole(RoleBean bean) throws Exception {
		return roleRepo.save(bean);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRole(Long id) throws Exception {
		roleRepo.deleteById(id);
	}
}