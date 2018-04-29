package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.HospitalDto;
import com.hospitalms.model.Hospital;
import com.hospitalms.repository.HospitalRepository;


@Service("hospitalService")
public class HospitalService {	
		
	private static final Logger LOG = LoggerFactory.getLogger(HospitalService.class);

	@Autowired
	HospitalRepository hospitalRepository;
	
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
		return mapper.map(hospitalRepository.findById(id),HospitalDto.class);
	}
	
	
	public HospitalDto addHospital(HospitalDto hospitalDto) {
		hospitalRepository.save(mapper.map(hospitalDto,Hospital.class));
		return hospitalDto;
	}
	
	public HospitalDto updateHospital(HospitalDto hospitalDto) {
		Hospital hospital= hospitalRepository.findById(hospitalDto.getId()).get();
		return hospitalDto;
	}
	
	public void deleteHospital(Integer id) {
		hospitalRepository.deleteById(id);
	}

}
