package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.HeadDto;
import com.hospitalms.model.Head;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Role;
import com.hospitalms.model.User;
import com.hospitalms.repository.HeadRepository;
import com.hospitalms.repository.HospitalRepository;
import com.hospitalms.repository.RoleRepository;
import com.hospitalms.repository.UserRepository;


@Service("headService")
@Transactional
public class HeadService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(HeadService.class);

	@Autowired
	HeadRepository headRepository;

	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<HeadDto> getHeads() {
		List<Head> headList=(List<Head>)headRepository.findAll();
		List<HeadDto> headDTOList = new ArrayList<HeadDto>();
		for(Head head:headList)
		{
			HeadDto headDto = new HeadDto();
			headDto.setHospital(head.getHospital());
			headDto.setId(head.getId());
			headDto.setUser(head.getUser());
		}
		return headDTOList;
	}
	
	public HeadDto getHeadByHospital(Integer hospitalId) {
		Hospital hospital= hospitalRepository.findById(hospitalId).get();
		Head head=headRepository.findByHospital(hospital);
		HeadDto headDto = new HeadDto();
		headDto.setHospital(head.getHospital());
		headDto.setId(head.getId());
		headDto.setUser(head.getUser());
		
		return headDto;
	}

	public HeadDto getHead(Integer id) {
		Head head=headRepository.findById(id).get();
		HeadDto headDto = new HeadDto();
		headDto.setHospital(head.getHospital());
		headDto.setId(head.getId());
		headDto.setUser(head.getUser());
		return headDto;
	}
	
	
	public HeadDto addHead(HeadDto headDto) {
		Role role=roleRepository.findByType("HEAD");
		
		User user = new User();
		user.setUserName(headDto.getUser().getEmail());
		user.setFirstName(headDto.getUser().getFirstName());
		user.setLastName(headDto.getUser().getLastName());
		user.setAddress(headDto.getUser().getAddress());
		user.setContact(headDto.getUser().getContact());
		user.setHospital(headDto.getHospital());
		user.setEmail(headDto.getUser().getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getUserName().substring(0, user.getUserName().indexOf("@"))));
		user.getUserRoles().add(role);
		
		Head head = mapper.map(headDto, Head.class);
		head.setUser(user);
		
		headRepository.save(head);
		return headDto;
	}
	
	public HeadDto updateHead(HeadDto headDto) {
		Head head= headRepository.findById(headDto.getId()).get();
		User user = head.getUser();
		user.setUserName(headDto.getUser().getEmail());
		user.setFirstName(headDto.getUser().getFirstName());
		user.setLastName(headDto.getUser().getLastName());
		user.setAddress(headDto.getUser().getAddress());
		user.setContact(headDto.getUser().getContact());
		user.setDob(headDto.getUser().getDob());
		user.setEmail(headDto.getUser().getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getUserName().substring(0, user.getUserName().indexOf("@"))));
		System.err.println(user.getUserName().substring(0, user.getUserName().indexOf("@")));
		user.setHospital(headDto.getHospital());
		
		return headDto;
	}
	
	public void deleteHead(Integer id) {
		headRepository.deleteById(id);
	}

}
