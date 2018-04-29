package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	User findByUserName(String userName);

}