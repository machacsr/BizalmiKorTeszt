package com.teszt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teszt.entity.User;
import com.teszt.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepo;

	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<User> getUsers(){
		return userRepo.findAll();
	}
}
