package com.teszt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.teszt.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	@Query(value = "select * from reservations where start >= :start and start <= :end", nativeQuery = true)
	List<Reservation> findByMonth(@Param("start") String start, @Param("end") String end);


}
