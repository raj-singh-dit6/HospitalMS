package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.User;
import com.hospitalms.model.UserSession;

public interface UserSessionRepository extends CrudRepository<UserSession, Integer>{

	UserSession findByUser(User user);

	UserSession findByUserAndSessionKey(User user, String sessionKey);

	UserSession findByUserAndSessionKey(String userName, String sessionKey);

}
