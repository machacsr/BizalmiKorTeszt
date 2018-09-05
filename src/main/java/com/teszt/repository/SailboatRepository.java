package com.teszt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teszt.entity.Sailboat;

public interface SailboatRepository extends CrudRepository<Sailboat, Long> {
	List<Sailboat> findAll();

	Sailboat findByName(String sailboatName);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE sailboats SET state = :state WHERE name= :name", nativeQuery = true)
	Integer updateSailboatState(@Param("name") String name, @Param("state") boolean state);
}
