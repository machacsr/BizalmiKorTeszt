package com.teszt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teszt.entity.User;
import com.teszt.service.UserService;

@RestController
public class ApiController {

	private UserService userService;
	
	@Autowired
	public void setStoryService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/addUser")
    public boolean addUser(@RequestParam(value="UserName", required = true) String name, 
    						@RequestParam(value="UserEmail", required = true) String email) {
		return userService.saveUser(name, email);
	}
	
	@RequestMapping("/deleteUser")
    public boolean deleteUser(@RequestParam(value="UserId", required = true) Integer userId) {
		return userService.deleteUser(userId);
	}
	
	@RequestMapping("/getUsers")
    public List<User> getUsers() {
		return userService.getUsers();
    }
	
	
}
