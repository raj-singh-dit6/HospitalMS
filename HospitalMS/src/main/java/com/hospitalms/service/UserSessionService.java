package com.hospitalms.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.UserInfoDto;
import com.hospitalms.model.User;
import com.hospitalms.model.UserSession;
import com.hospitalms.model.exceptions.MissingRecordException;
import com.hospitalms.repository.UserRepository;
import com.hospitalms.repository.UserSessionRepository;

@Service("userSessionService")
@Transactional
public class UserSessionService {

private static final Logger LOG = LoggerFactory.getLogger(UserSessionService.class);
	
	@Autowired
	UserSessionRepository userSessionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public UserInfoDto getUserSession(String userName) throws MissingRecordException {
		UserInfoDto result = new UserInfoDto();
		String sessionKey = null;
		User user= userRepository.findByUserName(userName);
		UserSession userSess=userSessionRepository.findByUser(user) ;
		
		if(user==null)
		{
			throw new MissingRecordException();
			
		}else {
			
			result.setId(user.getId());
			result.setEmail(user.getEmail());
			result.setFirstName(user.getFirstName());
			result.setLastName(user.getLastName());
			result.setUserName(userName);
			result.setRoles(user.getUserRoles());
			result.setHospital(user.getHospital());
			
			if(userSess!=null)
			{
				userSess.setActive(true);
				result.setSessionKey(userSess.getSessionKey());
				
			}else {
				
				sessionKey = UUID.randomUUID().toString();
				UserSession newUserSession= new UserSession();
				newUserSession.setActive(true);
				newUserSession.setSessionKey(sessionKey);
				newUserSession.setUser(user);
				userSessionRepository.save(newUserSession);
				
				result.setSessionKey(sessionKey);
			}
		}
		return result;
	}
	
	
	public void deleteUserSession(String sessionKey) {
		userSessionRepository.deleteBySessionKey(sessionKey);
	}
}
