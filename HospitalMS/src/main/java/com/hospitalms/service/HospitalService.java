package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.HospitalDto;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Speciality;
import com.hospitalms.repository.HospitalRepository;
import com.hospitalms.repository.SpecialityRepository;


@Service("hospitalService")
@Transactional
public class HospitalService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(HospitalService.class);

	@Autowired
	HospitalRepository hospitalRepository;
	
	
	@Autowired
	SpecialityRepository specialityRepository;
	
	@Autowired
	ModelMapper mapper;
	
	public List<HospitalDto> getHospitals() {
		List<Hospital> hospList=(List<Hospital>)hospitalRepository.findAll();
		List<HospitalDto> hospDTOList = new ArrayList<HospitalDto>();
		for(Hospital hospital:hospList)
		{
			hospDTOList.add(mapper.map(hospital, HospitalDto.class));
		}
		return hospDTOList;
	}

	public HospitalDto getHospital(Integer id) {
		return mapper.map(hospitalRepository.findById(id).get(),HospitalDto.class);
	}
	
	
	public HospitalDto addHospital(HospitalDto hospitalDto) {
		Speciality speciality = specialityRepository.findById(hospitalDto.getSpeciality().getId()).get();
		Hospital newHosp = mapper.map(hospitalDto,Hospital.class);
		newHosp.setSpeciality(speciality);
		newHosp.setActive(true);
		hospitalRepository.save(newHosp);
		return hospitalDto;
	}
	
	public HospitalDto updateHospital(HospitalDto hospitalDto) {
		Hospital updateHospital= hospitalRepository.findById(hospitalDto.getId()).get();
		updateHospital.setActive(hospitalDto.isActive());
		updateHospital.setAddress(hospitalDto.getAddress());
		updateHospital.setContact(hospitalDto.getContact());
		updateHospital.setName(hospitalDto.getName());
		updateHospital.setSpeciality(hospitalDto.getSpeciality());
		return hospitalDto;
	}
	
	public void deleteHospital(Integer id) {
		hospitalRepository.deleteById(id);
	}

}
