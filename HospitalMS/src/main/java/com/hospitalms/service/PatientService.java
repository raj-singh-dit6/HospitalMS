package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.PatientDto;
import com.hospitalms.dto.UserDto;
import com.hospitalms.model.Patient;
import com.hospitalms.model.User;
import com.hospitalms.repository.PatientRepository;


@Service("patientService")
public class PatientService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(PatientService.class);

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<PatientDto> getPatients() {
		List<Patient> hospList=(List<Patient>)patientRepository.findAll();
		List<PatientDto> hospDTOList = new ArrayList<PatientDto>();
		for(Patient patient:hospList)
		{
			hospDTOList.add(mapper.map(patient, PatientDto.class));
		}
		return hospDTOList;
	}

	public PatientDto getPatient(Integer id) {
		return mapper.map(patientRepository.findById(id),PatientDto.class);
	}
	
	
	public void addPatient(UserDto userDto) {
		User user = mapper.map(userDto,User.class);
		Patient patient = new Patient();
		patient.setUser(user);
		patientRepository.save(patient);
	}
	
	public PatientDto updatePatient(PatientDto patientDto) {
		Patient patient= patientRepository.findById(patientDto.getId()).get();
		return patientDto;
	}
	
	public void deletePatient(Integer id) {
		patientRepository.deleteById(id);
	}

}
