package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.User;
import com.hospitalms.model.UserSession;

public interface UserSessionRepository extends CrudRepository<UserSession, Integer>{

	UserSession findByUser(User user);
	UserSession findByIdAndSessionKey(Integer id, String sessionKey);
	void deleteBySessionKey(String sessionKey);

}
