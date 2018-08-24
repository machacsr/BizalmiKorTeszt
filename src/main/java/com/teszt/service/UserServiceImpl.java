package com.teszt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teszt.entity.User;
import com.teszt.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		return new UserDetailsImpl(user);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	@Override
	public void registerUser(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean saveUser(String name, String email) {
		if (!userRepository.existsByUserEmail(email)) {
			userRepository.save(new User(name, email));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteUser(Integer userId) {
		userRepository.deleteByUserId(userId);
		if (userRepository.existsById(userId)) {
			return false;
		}else {
			return true;
		}
	}

}
