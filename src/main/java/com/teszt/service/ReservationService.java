package com.teszt.service;

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

	public List<Reservation> getReservationsByMonth(byte month) {
		int year = Year.now().getValue();
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();

		String start = Integer.toString(year) + "-" + Byte.toString(month) + "-01 00:00:00";
		String end = Integer.toString(year) + "-" + Byte.toString(month) + "-" + daysInMonth + " 23:59:59";
		;

		return reservationRepo.findByMonth(start, end);
	}

	public void addReservation(Sailboat sailboat, User user,
			String startTime, String endTime, String persons) {
		
		reservationRepo.save(new Reservation(sailboat, user, new Date(startTime), new Date(endTime), persons));
	}
}
