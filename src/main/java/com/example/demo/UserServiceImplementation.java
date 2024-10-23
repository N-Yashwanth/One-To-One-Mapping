package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Users saveUser(Users user) {
		return userRepository.save(user);
	}

	@Override
	public List<Users> fetchAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Users getUserByID(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public Users updateUserByID(int id, Users user) {
		Users users=userRepository.findById(id).orElse(null);
		if(user.getAddress()!=null) {
			users.setAddress(user.getAddress());
		}
		if(user.getName()!=null) {
			users.setName(user.getName());
		}
		return userRepository.save(users);
	}

	@Override
	public String deleteUserByID(int id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "User Deleted Successfully";
		}
		else {
			return "User not found";
		}
	}

}
