package com.hospitalms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.PatientDto;
import com.hospitalms.model.Doctor;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Patient;
import com.hospitalms.model.PatientDoctor;
import com.hospitalms.model.PatientStatus;
import com.hospitalms.model.Role;
import com.hospitalms.model.User;
import com.hospitalms.repository.DoctorRepository;
import com.hospitalms.repository.HospitalRepository;
import com.hospitalms.repository.PatientDoctorRepository;
import com.hospitalms.repository.PatientRepository;
import com.hospitalms.repository.RoleRepository;
import com.hospitalms.repository.UserRepository;


@Service("patientService")
@Transactional
public class PatientService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(PatientService.class);

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	PatientDoctorRepository patientDoctorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<PatientDto> getPatients() {
		List<Patient> patientList=(List<Patient>)patientRepository.findAll();
		List<PatientDto> patientDTOList = new ArrayList<PatientDto>();
		for(Patient patient:patientList)
		{
			PatientDto patientDto = new PatientDto();
			patientDto.setId(patient.getId());
			patientDto.setPatientStatus(patient.getPatientStatus());
			patientDto.setUser(patient.getUser());
			patientDto.setHospital(patient.getHospital());
			patientDto.setRoom(patient.getRoom());
			patientDto.setAdmittedDate(patient.getAdmittedDate());
			patientDto.setAttendedDate(patientDto.getAttendedDate());
			patientDto.setDischargedDate(patient.getDischargedDate());
			
			patientDTOList.add(patientDto);
		}
		return patientDTOList;
	}
	

	public List<PatientDto> getPatientsByHospital(Integer hospitalId) {
		Hospital hospital = hospitalRepository.findById(hospitalId).get();
		List<Patient>	patientList=(List<Patient>)patientRepository.findAllByHospital(hospital);
		List<PatientDto> patientDTOList = new ArrayList<PatientDto>();
		for(Patient patient:patientList)
		{
			PatientDto patientDto = new PatientDto();
			patientDto.setId(patient.getId());
			patientDto.setPatientStatus(patient.getPatientStatus());
			patientDto.setUser(patient.getUser());
			patientDto.setHospital(patient.getHospital());
			patientDto.setRoom(patient.getRoom());
			patientDto.setAdmittedDate(patient.getAdmittedDate());
			patientDto.setAttendedDate(patientDto.getAttendedDate());
			patientDto.setDischargedDate(patient.getDischargedDate());
			
			patientDTOList.add(patientDto);
			
		}
		return patientDTOList;
	}

	public PatientDto getPatient(Integer id) {
		Patient patient = patientRepository.findById(id).get();
		PatientDto patientDto = new PatientDto();
		patientDto.setId(patient.getId());
		patientDto.setPatientStatus(patient.getPatientStatus());
		patientDto.setUser(patient.getUser());
		patientDto.setHospital(patient.getHospital());
		patientDto.setRoom(patient.getRoom());
		patientDto.setAdmittedDate(patient.getAdmittedDate());
		patientDto.setAttendedDate(patientDto.getAttendedDate());
		patientDto.setDischargedDate(patient.getDischargedDate());
		return patientDto;
	}
	
	
	public PatientDto addPatient(PatientDto patientDto) {
			Role role=roleRepository.findByType("PATIENT");
			User user = new User();
			user.setUserName(patientDto.getUser().getEmail());
			user.setFirstName(patientDto.getUser().getFirstName());
			user.setLastName(patientDto.getUser().getLastName());
			user.setAddress(patientDto.getUser().getAddress());
			user.setContact(patientDto.getUser().getContact());
			user.setDob(patientDto.getUser().getDob());
			user.setEmail(patientDto.getUser().getEmail());
			user.setPassword(new BCryptPasswordEncoder().encode(user.getUserName().substring(0, user.getUserName().indexOf("@"))));
			user.getUserRoles().add(role);
			
			Patient patient = mapper.map(patientDto, Patient.class);
			patient.setUser(user);
			setPatientStatusDate(patient,patient.getPatientStatus());
			
			patientRepository.save(patient);
			return patientDto;
	}
	
	public PatientDto updatePatient(PatientDto patientDto) {
		Patient patient= patientRepository.findById(patientDto.getId()).get();
		User user = patient.getUser();
		user.setFirstName(patientDto.getUser().getFirstName());
		user.setLastName(patientDto.getUser().getLastName());
		user.setAddress(patientDto.getUser().getAddress());
		user.setContact(patientDto.getUser().getContact());
		user.setDob(patientDto.getUser().getDob());
		user.setHospital(patientDto.getHospital());
		
		patient.setPatientStatus(patientDto.getPatientStatus());
		setPatientStatusDate(patient,patient.getPatientStatus());
		return patientDto;
	}
	
	public void deletePatient(Integer id) {
		patientRepository.deleteById(id);
	}
	
	/**
	 * To change  status date corresponding to different statuses according to status
	 * @param patient
	 * @param patientStatus
	 */
	public void setPatientStatusDate(Patient patient,PatientStatus patientStatus)
	{
		if(patientStatus.getName().equalsIgnoreCase("admitted")) {
			patient.setAdmittedDate(new Date());
		}else if(patientStatus.getName().equalsIgnoreCase("discharged")) {
			patient.setDischargedDate(new Date());
		}else {
			patient.setAttendedDate(new Date());
		}
	}
	
	public PatientDto assignDoctor(PatientDto patientDto) {
		Patient patient= patientRepository.findById(patientDto.getId()).get();
		Doctor doctor = doctorRepository.findById(patientDto.getDoctor().getId()).get();
		if(patientDto.getPatientStatus().getName().equalsIgnoreCase("admitted"));
			patient.setRoom(patientDto.getRoom());	
		
		patient.setPatientStatus(patientDto.getPatientStatus());
		setPatientStatusDate(patient,patient.getPatientStatus());
		
		PatientDoctor patientDoctor = new PatientDoctor();
		patientDoctor.setDoctor(doctor);
		patientDoctor.setPatient(patient);
		patientDoctorRepository.save(patientDoctor);
		
		return patientDto;
	}
	

}
