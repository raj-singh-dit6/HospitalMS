package com.hospitalms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.PatientDto;
import com.hospitalms.dto.PatientStatusDailyDataSet;
import com.hospitalms.model.Doctor;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Patient;
import com.hospitalms.model.PatientDoctor;
import com.hospitalms.model.PatientStatus;
import com.hospitalms.model.Role;
import com.hospitalms.model.Room;
import com.hospitalms.model.User;
import com.hospitalms.repository.DoctorRepository;
import com.hospitalms.repository.HospitalRepository;
import com.hospitalms.repository.PatientDoctorRepository;
import com.hospitalms.repository.PatientRepository;
import com.hospitalms.repository.PatientStatusRepository;
import com.hospitalms.repository.RoleRepository;
import com.hospitalms.repository.RoomRepository;
import com.hospitalms.repository.UserRepository;


@Service("patientService")
@Transactional
public class PatientService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(PatientService.class);

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PatientStatusRepository patientStatusRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	
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
	
	public PatientStatusDailyDataSet getPatientsDailyStatus(Integer hospitalId) {
		Hospital hospital = hospitalRepository.findById(hospitalId).get();
		List<Patient>	patientList=(List<Patient>)patientRepository.findAllByHospital(hospital);
		Map<Integer,Integer> admittedMap = new TreeMap<Integer,Integer>();
		Map<Integer,Integer> dischargedMap = new TreeMap<Integer,Integer>();
		for(Date date:getDaysOfMonth())
		{
			Calendar dayCal = Calendar.getInstance();
			dayCal.setTime(date);
			for(Patient patient:patientList)
			{
				if(!admittedMap.containsKey(dayCal.get(Calendar.DATE)))
				{
					admittedMap.put(dayCal.get(Calendar.DATE), 0);
				}
				
				if( !dischargedMap.containsKey(dayCal.get(Calendar.DATE)))
				{
					dischargedMap.put(dayCal.get(Calendar.DATE), 0);
				}
				
				Calendar admittedCal = Calendar.getInstance();
				Calendar dischargedCal = Calendar.getInstance();
				
				if(patient.getAdmittedDate()!=null)
				{
					admittedCal.setTime(patient.getAdmittedDate());
					if(dayCal.get(Calendar.DATE)==admittedCal.get(Calendar.DATE))
					{
						admittedMap.put(dayCal.get(Calendar.DATE), admittedMap.get(dayCal.get(Calendar.DATE))+1);
					}
					
				} 
				
				if(patient.getDischargedDate()!=null) {
				
					dischargedCal.setTime(patient.getDischargedDate());
					if(dayCal.get(Calendar.DATE)==dischargedCal.get(Calendar.DATE))
					{
						dischargedMap.put(dayCal.get(Calendar.DATE), dischargedMap.get(dayCal.get(Calendar.DATE))+1);
					}
				}
				
			}
		}
		
		List<Integer> dischargedValues= new ArrayList<Integer>(dischargedMap.values());
		List<Integer> admittedValues = new ArrayList<Integer>(admittedMap.values());

		System.err.println(admittedValues);
		System.err.println(dischargedValues);
		
		
		PatientStatusDailyDataSet patientStatusDailyDataSet = new PatientStatusDailyDataSet();
		patientStatusDailyDataSet.setAdmittedList(admittedValues);
		patientStatusDailyDataSet.setDischargedList(dischargedValues);
		
		return patientStatusDailyDataSet;
	}
	
	
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
			patientDto.setReservationDate(patient.getReservationDate());
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
			patientDto.setReservationDate(patient.getReservationDate());
			patientDto.setDischargedDate(patient.getDischargedDate());
			
			patientDTOList.add(patientDto);
			
		}
		return patientDTOList;
	}

	
	public List<PatientDto> getPatientsByRoom(Integer roomId) {
		Room room = roomRepository.findById(roomId).get();
		List<Patient>	patientList=(List<Patient>)patientRepository.findAllByRoom(room);
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
			patientDto.setReservationDate(patient.getReservationDate());
			patientDto.setDischargedDate(patient.getDischargedDate());
			
			patientDTOList.add(patientDto);
			
		}
		return patientDTOList;
	}
	
	public List<PatientDto> getPatientsByDoctor(Integer doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId).get();
		List<PatientDoctor>	patientDoctorList=(List<PatientDoctor>)patientDoctorRepository.findAllByDoctor(doctor);
		List<PatientDto> patientDTOList = new ArrayList<PatientDto>();
		for(PatientDoctor patientDoctor:patientDoctorList)
		{
			Patient patient=patientDoctor.getPatient();
			PatientDto patientDto = new PatientDto();
			patientDto.setId(patient.getId());
			patientDto.setPatientStatus(patient.getPatientStatus());
			patientDto.setUser(patient.getUser());
			patientDto.setHospital(patient.getHospital());
			patientDto.setRoom(patient.getRoom());
			patientDto.setAdmittedDate(patient.getAdmittedDate());
			patientDto.setReservationDate(patient.getReservationDate());
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
		patientDto.setReservationDate(patient.getReservationDate());
		patientDto.setDischargedDate(patient.getDischargedDate());
		return patientDto;
	}
	
	
	public PatientDto addPatient(PatientDto patientDto) {
			Role role=roleRepository.findByType("PATIENT");
			PatientStatus patientStatus= patientStatusRepository.findByName("Attended");
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
		Room room=patient.getRoom();
		if(room!=null) {
			room=roomRepository.findById(patient.getRoom().getId()).get();
			if(patientStatus.getName().equalsIgnoreCase("admitted")) {
				if(room.getRemainingBeds()>0)
					room.setRemainingBeds(room.getTotalBeds()-1);
				patient.setAdmittedDate(new Date());
				room.setVacant(room.getTotalBeds()<room.getRemainingBeds());
				
			}else if(patientStatus.getName().equalsIgnoreCase("discharged")) {
				if(room.getTotalBeds()<=room.getRemainingBeds())
					room.setRemainingBeds(room.getRemainingBeds()+1);
				patient.setDischargedDate(new Date());
				room.setVacant(room.getTotalBeds()<room.getRemainingBeds());
			}else {
				patient.setReservationDate(new Date());
			}
	   }
	}
	
	public PatientDto assignDoctor(PatientDto patientDto) {
		Patient patient= patientRepository.findById(patientDto.getId()).get();
		Doctor doctor = doctorRepository.findById(patientDto.getDoctor().getId()).get();
		
		PatientDoctor patientDoctor = new PatientDoctor();
		patientDoctor.setDoctor(doctor);
		patientDoctor.setPatient(patient);
		patientDoctorRepository.save(patientDoctor);
		
		return patientDto;
	}
	
	public PatientDto assignRoom(PatientDto patientDto) {
		Patient patient= patientRepository.findById(patientDto.getId()).get();
		patient.setRoom(patientDto.getRoom());	
		patient.setPatientStatus(patientDto.getPatientStatus());
		setPatientStatusDate(patient,patient.getPatientStatus());
		
		return patientDto;
	}
	
	public List<Date> getDaysOfMonth() {
		List<Date> daysOfMonth= new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		int myMonth=cal.get(Calendar.MONTH);
	
		while (myMonth==cal.get(Calendar.MONTH)) {
		  System.err.print(cal.getTime());
		  daysOfMonth.add(cal.getTime());
		  cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return daysOfMonth;
	}

}
