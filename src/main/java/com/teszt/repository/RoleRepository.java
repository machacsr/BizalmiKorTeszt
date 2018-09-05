package com.teszt.repository;

import org.springframework.data.repository.CrudRepository;

import com.teszt.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{

	Role findByRole(String userRole);
}
