package com.teszt.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teszt.entity.Reservation;
import com.teszt.entity.Sailboat;
import com.teszt.entity.User;
import com.teszt.repository.ReservationRepository;

@Service
public class ReservationService {

	private ReservationRepository reservationRepo;

	@Autowired
	public void setReservationRepo(ReservationRepository reservationRepo) {
		this.reservationRepo = reservationRepo;
	}

	public List<Reservation> getReservationsByMonth(Integer month, String saibloatId) {
		int year = Year.now().getValue();
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();

		String start = Integer.toString(year) + "-" + Integer.toString(month) + "-01 00:00:00";
		String end = Integer.toString(year) + "-" + Integer.toString(month) + "-" + daysInMonth + " 23:59:59";
		
		return reservationRepo.findByMonth(start, end, saibloatId);
	}

	public String addReservation(Sailboat sailboat, User user, String startTime, String endTime, String persons) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTimeDate = null;
		Date endTimeDate = null;
		try {
			startTimeDate = df.parse(startTime);
			endTimeDate = df.parse(endTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Reservation reservation = new Reservation(sailboat, user, startTimeDate, endTimeDate, persons);
		
		reservationRepo.save(reservation);
		user.addReservation(reservation);
		return "Sikeres foglalás!";
	}

	public List<Reservation> getReservationsByUserByYear(User user) {
		
		String thisYear = Year.now().toString() + "-01-01 00:00:00";
		
		return reservationRepo.findByUserByYear(thisYear, user.getUserId());
	}
}
