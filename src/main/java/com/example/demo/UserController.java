package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/user")
	public Users saveUser(@RequestBody Users user) {
		return userService.saveUser(user);
	}
	@GetMapping("/user")
	public List<Users> getAllUsers(){
		return userService.fetchAllUsers();
	}
	@PutMapping("/user/{id}")
	public Users updateUserByID(@PathVariable int id, @RequestBody Users user){
		return userService.updateUserByID(id, user);
	}
	@DeleteMapping("/user")
	public String deleteUserByID(@PathVariable int id) {
		return userService.deleteUserByID(id);
	}
}
