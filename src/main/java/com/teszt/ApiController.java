package com.teszt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.teszt.service.UserService;

@RestController
public class ApiController {

	private UserService userService;
	
	@Autowired
	public void setStoryService(UserService userService) {
		this.userService = userService;
	}
}
