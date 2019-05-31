package com.elvino.rest.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elvino.rest.model.MenuBean;
import com.elvino.rest.model.Result;
import com.elvino.rest.model.RoleBean;
import com.elvino.rest.model.UserBean;

@RestController
@RequestMapping("/usermanagement")
public class UserManagementController extends ServiceController{

	// User Module
	
	@GetMapping(name="/user/{id}")
	public Result getUser(@PathVariable("id") Long id) {
		try {
			return Result.success(userService.getUser(id));
		}catch(Exception e) { return Result.error("Gagal ambil data", e.getMessage()); }
	}
	
	@PostMapping(name="/user")
	public Result saveUser(@RequestBody UserBean bean) {
		try {
			return Result.success(userService.saveUser(bean), 200, "Simpan Data Berhasil", "Success to save data");
		}catch(Exception e) { return Result.error("Gagal simpan data", e.getMessage()); }
	}
	
	@PutMapping(name="/user/{id}")
	public Result updateUser(@PathVariable("id") Long id, @RequestBody UserBean bean) {
		try {
			return Result.success(userService.saveUser(bean), 200, "Ubah Data Berhasil", "Success to update data");
		}catch(Exception e) { return Result.error("Gagal ubah data", e.getMessage()); }
	}
	
	@DeleteMapping(name="/user/{id}")
	public Result deleteUser(@PathVariable("id") Long id) {
		try {
			userService.deleteUser(id);
			return Result.success("Hapus Data Berhasil", "Success to delete data");
		}catch(Exception e) { return Result.error("Gagal hapus data", e.getMessage()); }
	}
	
	@GetMapping("/users")
	public Result getUsers(@RequestParam("name") String name, @RequestParam("email") String email){
		try {
			return Result.success(userService.getUsers(name, email));
		}catch(Exception e) { return Result.error("Gagal ambil data", e.getMessage()); }
	}
	
	
	// Module Menu
	@GetMapping(name="/menu/{id}")
	public Result getMenu(@PathVariable("id") Long id) {
		try {
			return Result.success(userService.getMenu(id));
		}catch(Exception e) { return Result.error("Gagal ambil data", e.getMessage()); }
	}
	
	@PostMapping(name="/menu")
	public Result saveMenu(@RequestBody MenuBean bean) {
		try {
			return Result.success(userService.saveMenu(bean), 200, "Simpan Data Berhasil", "Success to save data");
		}catch(Exception e) { return Result.error("Gagal simpan data", e.getMessage()); }
	}
	
	@PutMapping(name="/menu/{id}")
	public Result updateMenu(@PathVariable("id") Long id, @RequestBody MenuBean bean) {
		try {
			return Result.success(userService.saveMenu(bean), 200, "Ubah Data Berhasil", "Success to update data");
		}catch(Exception e) { return Result.error("Gagal ubah data", e.getMessage()); }
	}
	
	@DeleteMapping(name="/menu/{id}")
	public Result deleteMenu(@PathVariable("id") Long id) {
		try {
			userService.deleteMenu(id);
			return Result.success("Hapus Data Berhasil", "Success to delete data");
		}catch(Exception e) { return Result.error("Gagal hapus data", e.getMessage()); }
	}
	
	
	// Module Role
	@GetMapping(name="/role/{id}")
	public Result getRole(@PathVariable("id") Long id) {
		try {
			return Result.success(userService.getRole(id));
		}catch(Exception e) { return Result.error("Gagal ambil data", e.getMessage()); }
	}
	
	@PostMapping(name="/role")
	public Result saveRole(@RequestBody RoleBean bean) {
		try {
			return Result.success(userService.saveRole(bean), 200, "Simpan Data Berhasil", "Success to save data");
		}catch(Exception e) { return Result.error("Gagal simpan data", e.getMessage()); }
	}
	
	@PutMapping(name="/role/{id}")
	public Result updateRole(@PathVariable("id") Long id, @RequestBody RoleBean bean) {
		try {
			return Result.success(userService.saveRole(bean), 200, "Ubah Data Berhasil", "Success to update data");
		}catch(Exception e) { return Result.error("Gagal ubah data", e.getMessage()); }
	}
	
	@DeleteMapping(name="/role/{id}")
	public Result deleteRole(@PathVariable("id") Long id) {
		try {
			userService.deleteRole(id);
			return Result.success("Hapus Data Berhasil", "Success to delete data");
		}catch(Exception e) { return Result.error("Gagal hapus data", e.getMessage()); }
	}
}
