package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Role;

public interface RoleRepository extends CrudRepository<Role,Integer>{
	
	Role findByType(String type);

}
