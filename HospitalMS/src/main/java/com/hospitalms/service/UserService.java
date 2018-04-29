package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.UserDto;
import com.hospitalms.model.Role;
import com.hospitalms.model.User;
import com.hospitalms.model.UserSession;
import com.hospitalms.repository.UserRepository;
import com.hospitalms.repository.UserSessionRepository;

@Service("userService")
public class UserService{

	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DoctorService doctorService;
	

	@Autowired
	PatientService patientService;
	
	@Autowired
	UserSessionRepository userSessRepository;
	
	@Autowired
	ModelMapper mapper;

	public boolean authenticate(String userName, String sessionKey) {
		User user = userRepository.findByUserName(userName);
		UserSession  userSession = userSessRepository.findByUserAndSessionKey(user, sessionKey);
		return userSession!=null;
	}
	
	public List<UserDto> getUsers() {
		List<User> hospList=(List<User>)userRepository.findAll();
		List<UserDto> hospDTOList = new ArrayList<UserDto>();
		for(User user:hospList)
		{
			hospDTOList.add(mapper.map(user, UserDto.class));
		}
		return hospDTOList;
	}

	public UserDto getUser(Integer id) {
		return mapper.map(userRepository.findById(id),UserDto.class);
	}
	
	
	public UserDto addUser(UserDto userDto) {
		boolean normalUser=true;
		for (Role role : userDto.getUserRoles())
		{
			if(role.getType().equalsIgnoreCase("DOCTOR"))
			{
				doctorService.addDoctor(userDto);
				normalUser=false;
			}else if(role.getType().equalsIgnoreCase("PATIENT")) {
				patientService.addPatient(userDto);
				normalUser=false;
			}
		}
		
		if(normalUser)
			userRepository.save(mapper.map(userDto,User.class));
		
		return userDto;
	}
	
	public UserDto updateUser(UserDto userDto) {
		User user= userRepository.findById(userDto.getId()).get();
		return userDto;
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

}

