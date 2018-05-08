package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.DoctorDto;
import com.hospitalms.model.Doctor;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Role;
import com.hospitalms.model.User;
import com.hospitalms.repository.DoctorRepository;
import com.hospitalms.repository.HospitalRepository;
import com.hospitalms.repository.RoleRepository;
import com.hospitalms.repository.UserRepository;


@Service("doctorService")
@Transactional
public class DoctorService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(DoctorService.class);

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<DoctorDto> getDoctors() {
		List<Doctor>	doctorList=(List<Doctor>)doctorRepository.findAll();
		List<DoctorDto> doctorDTOList = new ArrayList<DoctorDto>();
		for(Doctor doctor:doctorList)
		{
			DoctorDto doctorDto= new DoctorDto();
			doctorDto.setId(doctor.getId());
			doctorDto.setDepartment(doctor.getDepartment());
			doctorDto.setUser(doctor.getUser());
			doctorDto.setHospital(doctor.getHospital());
			doctorDto.setActive(doctor.isActive());
			doctorDTOList.add(doctorDto);
		}
		return doctorDTOList;

	}
	
	public List<DoctorDto> getDoctorsByHospital(Integer hospitalId) {
		Hospital hospital= hospitalRepository.findById(hospitalId).get();
		List<Doctor>	doctorList=(List<Doctor>)doctorRepository.findAllByHospital(hospital);
		List<DoctorDto> doctorDTOList = new ArrayList<DoctorDto>();
		for(Doctor doctor:doctorList)
		{
			DoctorDto doctorDto= new DoctorDto();
			doctorDto.setId(doctor.getId());
			doctorDto.setDepartment(doctor.getDepartment());
			doctorDto.setUser(doctor.getUser());
			doctorDto.setHospital(doctor.getHospital());
			doctorDto.setActive(doctor.isActive());
			doctorDTOList.add(doctorDto);
		}
		return doctorDTOList;
	}

	public DoctorDto getDoctor(Integer id) {
		Doctor doctor=doctorRepository.findById(id).get();
		DoctorDto doctorDto= new DoctorDto();
		doctorDto.setId(doctor.getId());
		doctorDto.setDepartment(doctor.getDepartment());
		doctorDto.setUser(doctor.getUser());
		doctorDto.setHospital(doctor.getHospital());
		doctorDto.setActive(doctor.isActive());
		return doctorDto;
	}
	
	
	public DoctorDto addDoctor(DoctorDto doctorDto) {
		Role role=roleRepository.findByType("DOCTOR");
		User user = new User();
		user.setUserName(doctorDto.getUser().getEmail());
		user.setFirstName(doctorDto.getUser().getFirstName());
		user.setLastName(doctorDto.getUser().getLastName());
		user.setAddress(doctorDto.getUser().getAddress());
		user.setContact(doctorDto.getUser().getContact());
		user.setHospital(doctorDto.getHospital());
		user.setEmail(doctorDto.getUser().getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getUserName().substring(0, user.getUserName().indexOf("@"))));
		user.getUserRoles().add(role);
		
		Doctor doctor = mapper.map(doctorDto, Doctor.class);
		doctor.setUser(user);
		doctorRepository.save(doctor);
		return doctorDto;
	}
	
	public DoctorDto updateDoctor(DoctorDto doctorDto) {
		Doctor doctor= doctorRepository.findById(doctorDto.getId()).get();
		User user = doctor.getUser();
		user.setFirstName(doctorDto.getUser().getFirstName());
		user.setLastName(doctorDto.getUser().getLastName());
		user.setAddress(doctorDto.getUser().getAddress());
		user.setContact(doctorDto.getUser().getContact());
		user.setDob(doctorDto.getUser().getDob());
		user.setHospital(doctorDto.getHospital());
		
		doctor.setActive(doctorDto.isActive());
		doctor.setDepartment(doctorDto.getDepartment());
		return doctorDto;
	}
	
	public void deleteDoctor(Integer id) {
		doctorRepository.deleteById(id);
	}

}
