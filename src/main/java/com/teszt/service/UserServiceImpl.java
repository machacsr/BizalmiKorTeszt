package com.teszt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teszt.entity.Role;
import com.teszt.entity.User;
import com.teszt.repository.RoleRepository;
import com.teszt.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private final String USER_ROLE = "ROLE_USER";
	private final String ADMIN_ROLE = "ROLE_ADMIN";
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		return new UserDetailsImpl(user);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByUserEmail(userToRegister.getUserEmail());

		if (userCheck != null)
			return "alreadyExists";

		userToRegister.setUserPassword(passwordEncoder.encode(userToRegister.getUserPassword()));
		
		Role userRole = roleRepository.findByRole(USER_ROLE);
		if (userRole != null) {
			userToRegister.getRoles().add(userRole);
		} else {
			userToRegister.addRoles(USER_ROLE);
		}
		
		Role adminRole = roleRepository.findByRole(ADMIN_ROLE);
		if (adminRole != null) {
			userToRegister.getRoles().add(adminRole);
		} else {
			userToRegister.addRoles(ADMIN_ROLE);
		}
		
		userRepository.save(userToRegister);
		
		return "ok";
	}

	@Override
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
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

	@Override
	public User FindByUserId(Integer id) {
		return userRepository.findByUserId(id);
	}

	@Override
	public String FindUserNameByEmail(String email) {
		User user = userRepository.findByUserEmail(email);
		return user.getUserName();
	}



}
