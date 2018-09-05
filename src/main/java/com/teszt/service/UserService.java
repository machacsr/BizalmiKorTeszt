package com.teszt.service;

import java.util.List;

import com.teszt.entity.User;

public interface UserService {
	
	public User findByEmail(String email);

	public String registerUser(User user);
	
	public List<User> getUsers();

	public boolean saveUser(String name, String email);

	public boolean deleteUser(Integer userId);
	
	public User FindByUserId(Integer id);
}
