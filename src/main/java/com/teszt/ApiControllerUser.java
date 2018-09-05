package com.teszt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teszt.entity.Reservation;
import com.teszt.entity.Sailboat;
import com.teszt.entity.Story;
import com.teszt.service.ReservationService;
import com.teszt.service.SailboatService;
import com.teszt.service.StoryService;
import com.teszt.service.UserService;
import com.teszt.service.UserServiceImpl;

@RestController
public class ApiControllerUser {

	private UserService userService;
	private StoryService storyService;
	private SailboatService sailboatService;
	private ReservationService reservationService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}

	@Autowired
	public void setSailboatService(SailboatService sailboatService) {
		this.sailboatService = sailboatService;
	}

	@Autowired
	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/getStories")
	public List<Story> stories() {
		return storyService.getStories();
	}

	@RequestMapping(value = "/getSailboats", method = RequestMethod.GET)
	public List<Sailboat> getSailboats() {
		return sailboatService.getSailboats();
	}

	@GetMapping(value = "/getReservations/{month}")
	public List<Reservation> getReservationsByMonth(@PathVariable("month") byte month) {
		return reservationService.getReservationsByMonth(month);
	}

	@PostMapping("/addReservation")
	public String addReservation(@RequestParam(value = "sailboatName", required = true) String sailboatName,
			@RequestParam(value = "startTime", required = true) String startTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "persons", required = true) String persons, HttpServletRequest request) {
		reservationService.addReservation(sailboatService.findSailboatByName(sailboatName),
				userService.findByEmail(request.getUserPrincipal().getName()),
				startTime, endTime, persons);
		return "ok";
	}

}
