package com.teszt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.teszt.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findAll();
}
