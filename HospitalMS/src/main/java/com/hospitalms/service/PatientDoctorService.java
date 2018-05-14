package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.DoctorDto;
import com.hospitalms.dto.PatientDoctorDto;
import com.hospitalms.model.Doctor;
import com.hospitalms.model.Patient;
import com.hospitalms.model.PatientDoctor;
import com.hospitalms.model.User;
import com.hospitalms.repository.DoctorRepository;
import com.hospitalms.repository.PatientDoctorRepository;
import com.hospitalms.repository.PatientRepository;


@Service("patientDoctorService")
@Transactional
public class PatientDoctorService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(PatientDoctorService.class);

	@Autowired
	PatientDoctorRepository patientDoctorRepository;

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<DoctorDto> getPatientDoctorsByPatient(Integer patientId) {
		Patient patient = patientRepository.findById(patientId).get();
		List<Doctor> doctorList = (List<Doctor>) doctorRepository.findAll();
		List<PatientDoctor>	patientDoctorList=(List<PatientDoctor>)patientDoctorRepository.findAllByPatient(patient);
		List<DoctorDto> doctorDtoList = new ArrayList<DoctorDto>();
		Map<Integer,User> doctorPatientMap = new TreeMap<Integer,User>();
		
		for(PatientDoctor patientDoctor:patientDoctorList)
		{
			doctorPatientMap.put(patientDoctor.getDoctor().getId(), patientDoctor.getDoctor().getUser());
		}	
		
		
		for(Doctor doctor:doctorList)
		{
			if(!doctorPatientMap.containsKey(doctor.getId()))
				doctorDtoList.add(mapper.map(doctor,DoctorDto.class));
		}
		
		return doctorDtoList;
	}
	
	
	public PatientDoctorDto addPatientDoctor(PatientDoctorDto patientDoctorDto) {
		return null;
	}
	
	public PatientDoctorDto updatePatientDoctor(PatientDoctorDto patientDoctorDto) {
		return null;
	}
	
	public void deletePatientDoctor(Integer id) {
		patientDoctorRepository.deleteById(id);
	}

}
