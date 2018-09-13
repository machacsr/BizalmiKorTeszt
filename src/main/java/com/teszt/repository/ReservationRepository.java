package com.teszt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.teszt.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	@Query(value = "select * from (select * from reservations where sailboat_id = :sailboatId) where start >= :start and start <= :end", nativeQuery = true)
	List<Reservation> findByMonth(@Param("start") String start, @Param("end") String end, @Param("sailboatId") String sailboatId);
	
	@Query(value = "select * from (select * from reservations where user_user_id = :userId) where start >= :start", nativeQuery = true)
	List<Reservation> findByUserByYear(@Param("start") String start, @Param("userId") Integer integer);
	
	


}
