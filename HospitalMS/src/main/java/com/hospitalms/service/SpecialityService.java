package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.SpecialityDto;
import com.hospitalms.model.Speciality;
import com.hospitalms.repository.SpecialityRepository;

@Service("specialityService")
@Transactional
public class SpecialityService {

	private static final Logger LOG = LoggerFactory.getLogger(SpecialityService.class);

	@Autowired
	SpecialityRepository specialityRepository;

	@Autowired
	ModelMapper mapper;

	public List<SpecialityDto> getSpecialities() {
		List<Speciality> hospList = (List<Speciality>) specialityRepository.findAll();
		List<SpecialityDto> hospDTOList = new ArrayList<SpecialityDto>();
		for (Speciality speciality : hospList) {
			hospDTOList.add(mapper.map(speciality, SpecialityDto.class));
		}
		return hospDTOList;
	}

	public SpecialityDto getSpeciality(Integer id) {
		return mapper.map(specialityRepository.findById(id).get(), SpecialityDto.class);
	}

	public SpecialityDto addSpeciality(SpecialityDto specialityDto) {
		specialityRepository.save(mapper.map(specialityDto, Speciality.class));
		return specialityDto;
	}

	public SpecialityDto updateSpeciality(SpecialityDto specialityDto) {
		Speciality speciality = specialityRepository.findById(specialityDto.getId()).get();
		return specialityDto;
	}

	public void deleteSpeciality(Integer id) {
		specialityRepository.deleteById(id);
	}

}
