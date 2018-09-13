package com.teszt;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(value = "/getReservations/{month}/{sailboatName}")
	public List<Reservation> getReservationsByMonth(@PathVariable("month") Integer month,
			@PathVariable("sailboatName") String name) {
		return reservationService.getReservationsByMonth(month,
				sailboatService.findSailboatByName(name).getId().toString());
	}

	@GetMapping(value = "/getCurrentUserName")
	public Map<String, String> getCurrentUserName(HttpServletRequest request) {
		return Collections.singletonMap("user", userService.FindUserNameByEmail(request.getUserPrincipal().getName()));
	}

	@PostMapping("/addReservation")
	public Map<String, String> addReservation(
			@RequestParam(value = "sailboatName", required = true) String sailboatName,
			@RequestParam(value = "startTime", required = true) String startTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "persons", required = true) String persons, HttpServletRequest request) {
		return Collections.singletonMap("message",
				reservationService.addReservation(sailboatService.findSailboatByName(sailboatName),
						userService.findByEmail(request.getUserPrincipal().getName()), startTime, endTime, persons));
	}

	@GetMapping(value="/getReservations")
	public List<Reservation> getReservations(HttpServletRequest request){
		return reservationService.getReservationsByUserByYear(userService.findByEmail(request.getUserPrincipal().getName()));
	}

	@RequestMapping(value ="/deleteReservation/{reservationId}", method = RequestMethod.POST)
	public boolean deleteReservation(@PathVariable Integer reservationId, HttpServletRequest request){
		userService.findByEmail(request.getUserPrincipal().getName()).getReservations().forEach((res) -> res.toString());
		return false;
	}
}
