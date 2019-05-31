package com.elvino.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elvino.rest.model.UserBean;

@RestController
@RequestMapping("/usermanagement")
public class UserManagementController extends ServiceController{

	//User
	
	@GetMapping(name="/user/{id}")
	public UserBean getUser(@PathVariable("id") Long id) throws Exception{
		return userService.getUser(id);
	}
	
	@PostMapping(name="/user")
	public UserBean saveUser(@RequestBody UserBean bean) throws Exception{
		return userService.saveUser(bean);
	}
	
	@PutMapping(name="/user/{id}")
	public UserBean updateUser(@PathVariable("id") Long id, @RequestBody UserBean bean) throws Exception{
		return userService.saveUser(bean);
	}
	
	@DeleteMapping(name="/user/{id}")
	public void updateUser(@PathVariable("id") Long id) throws Exception{
		userService.deleteUser(id);
	}
	
	@GetMapping("/users")
	public List<UserBean> getUsers(@RequestParam("name") String name, @RequestParam("email") String email)  throws Exception{
		return userService.getUsers(name, email);
	}
}
