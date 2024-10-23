package com.example.demo;

import java.util.List;

public interface UserService {
	public Users saveUser(Users user);
	public List<Users> fetchAllUsers();
	public Users getUserByID(int id);
	public Users updateUserByID(int id, Users user);
	public String deleteUserByID(int id);
}
