package com.teszt;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teszt.entity.User;
import com.teszt.service.SailboatService;
import com.teszt.service.StoryService;
import com.teszt.service.UserService;

@Secured("ROLE_ADMIN")
@RestController
public class ApiControllerAdmin {

	private UserService userService;
	private StoryService storyService;
	private SailboatService sailboatService;
	
	@Autowired
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}
	
	@Autowired
	public void setStoryService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setSailboatService(SailboatService sailboatService) {
		this.sailboatService = sailboatService;
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
	
	@RequestMapping(value = "/addStory", method = RequestMethod.GET)
	public boolean addStory(@RequestParam(value="storyTitle", required = true) String storyTitle,
							 @RequestParam(value="storyContent", required = true) String storyContent,
							  HttpServletRequest request) {
		storyService.saveStory(storyTitle, storyContent, userService.findByEmail(request.getUserPrincipal().getName()));
		return true;
	}
	
	@RequestMapping(value = "/deleteStory", method = RequestMethod.POST)
	public void deleteStory(@RequestParam(value="storyId", required = true) Long storyId) {
		storyService.deleteStory(storyId);
	}
	
	@RequestMapping(value = "/addSailboat", method = RequestMethod.POST, produces = "application/json")
	public Map<String, String> addSailboat(@RequestParam(value="sailboatName", required = true) String sailboatName,
								@RequestParam(value="sailboatCapacity", required = true) byte sailboatCapacity,
								 @RequestParam(value="sailboatState", required = true) boolean sailboatState) {
		return Collections.singletonMap("message", sailboatService.saveSailboat(sailboatName, sailboatCapacity, sailboatState));
	}
	
	@RequestMapping(value = "/setSailboatState", method = RequestMethod.POST)
	public boolean setSailboatState(@RequestParam(value="sailboatName", required = true) String sailboatName,
								 @RequestParam(value="sailboatState", required = true) boolean sailboatState) {
		return sailboatService.setSailboatState(sailboatName, sailboatState);
	}
	
	@RequestMapping(value = "/deleteSailboat", method = RequestMethod.POST)
	public boolean deleteSailboat(@RequestParam(value="sailboatName", required = true) String sailboatName) {
		return sailboatService.deleteSailboat(sailboatName);
	}
	
	

}
