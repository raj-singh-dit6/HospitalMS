package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.OccupancyDto;
import com.hospitalms.model.Occupancy;
import com.hospitalms.repository.OccupancyRepository;

@Service("occupancyService")
@Transactional
public class OccupancyService {
	
		private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

		@Autowired
		ModelMapper mapper;
		
		@Autowired
		OccupancyRepository occupancyRepository;
		
		public List<OccupancyDto> getOccupancies() {
			List<Occupancy> occupancyList=(List<Occupancy>)occupancyRepository.findAll();
			List<OccupancyDto> occupancyDTOList = new ArrayList<OccupancyDto>();
			for(Occupancy occupancy:occupancyList)
			{	occupancyDTOList.add(mapper.map(occupancy, OccupancyDto.class));
			}
			return occupancyDTOList;
		}
		
		
		public OccupancyDto addOccupancy(OccupancyDto occupancyDto) {
			Occupancy occupancy= mapper.map(occupancyDto, Occupancy.class);
			occupancyRepository.save(occupancy);
			return occupancyDto;
		}
		
		public OccupancyDto updateOccupancy(OccupancyDto occupancyDto) {
			Occupancy occupancy= occupancyRepository.findById(occupancyDto.getId()).get();
			occupancy.setType(occupancyDto.getType());

			return occupancyDto;
		}
		
		public void deleteOccupancy(Integer id) {
			occupancyRepository.deleteById(id);
		}

		public OccupancyDto getOccupancy(Integer id) {
			return mapper.map(occupancyRepository.findById(id).get(),OccupancyDto.class);
		}
			
}

