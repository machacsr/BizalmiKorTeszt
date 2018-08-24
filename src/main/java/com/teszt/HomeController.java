package com.teszt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teszt.entity.User;
import com.teszt.service.UserService;

@Controller
public class HomeController {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping("/reg") 
    public String reg(@ModelAttribute User user) {
		userService.registerUser(user);
        return "auth/login";
    }
	
	@RequestMapping("/")
	public String users() {;
		return "main";
	}
}
