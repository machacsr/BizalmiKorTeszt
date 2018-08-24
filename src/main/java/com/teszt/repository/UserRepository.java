package com.teszt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.teszt.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
//	static final String FIND_USERS_WITHOUT_PASSWORD = "Select user_id, user_name,  user_email from users";
//	@Query(value = FIND_USERS_WITHOUT_PASSWORD, nativeQuery = true)
//	public List<Object> findUsersWithoutPassword();
	
	void deleteByUserId(Integer userId); 
	
	boolean existsByUserEmail(String email);
	
	User findByUserEmail(String email);
	
	List<User> findAll();
}
