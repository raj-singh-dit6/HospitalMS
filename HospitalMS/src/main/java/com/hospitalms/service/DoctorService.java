package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.DoctorDto;
import com.hospitalms.dto.UserDto;
import com.hospitalms.model.Doctor;
import com.hospitalms.model.User;
import com.hospitalms.repository.DoctorRepository;


@Service("doctorService")
@Transactional
public class DoctorService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(DoctorService.class);

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<DoctorDto> getDoctors() {
		List<Doctor> hospList=(List<Doctor>)doctorRepository.findAll();
		List<DoctorDto> hospDTOList = new ArrayList<DoctorDto>();
		for(Doctor doctor:hospList)
		{
			hospDTOList.add(mapper.map(doctor, DoctorDto.class));
		}
		return hospDTOList;
	}

	public DoctorDto getDoctor(Integer id) {
		return mapper.map(doctorRepository.findById(id).get(),DoctorDto.class);
	}
	
	
	public void addDoctor(UserDto userDto) {
		User user = mapper.map(userDto,User.class);
		Doctor doctor = new Doctor();
		doctor.setUser(user);
		doctorRepository.save(doctor);
	}
	
	public DoctorDto updateDoctor(DoctorDto doctorDto) {
		Doctor doctor= doctorRepository.findById(doctorDto.getId()).get();
		return doctorDto;
	}
	
	public void deleteDoctor(Integer id) {
		doctorRepository.deleteById(id);
	}

}
