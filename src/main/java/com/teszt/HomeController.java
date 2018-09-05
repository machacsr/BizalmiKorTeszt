package com.teszt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teszt.entity.User;
import com.teszt.service.SailboatService;
import com.teszt.service.UserService;

import javassist.NotFoundException;

@Controller
public class HomeController {
	
	private UserService userService;
	private SailboatService sailboatService;
		
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setSailboatService(SailboatService sailboatService) {
		this.sailboatService = sailboatService;
	}

	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping("/")
	public String stories() {
		return "stories";
	}
	
	@PostMapping("/reg") 
    public String reg(@ModelAttribute User user) {
		userService.registerUser(user);
        return "auth/login";
    }
	
	@RequestMapping("/sailboats")
	public String sailboats(Model model) {
		model.addAttribute("sailboats",sailboatService.getSailboats());
		return "sailboats";
	}
	
	@RequestMapping("/sailboats/reservation")
	public String reservation(HttpServletRequest request,
							  @RequestParam(value="SailboatName", required = true) String name,
							   Model model) throws NotFoundException {
		if (sailboatService.checkSailboatByName(name)){
			model.addAttribute("sailboat", sailboatService.findSailboatByName(name));
			return "reservation";
		}else {
			throw new NotFoundException("Nincs ilyen vitrolás az adatbázisunkban!");
		}

	}
}
