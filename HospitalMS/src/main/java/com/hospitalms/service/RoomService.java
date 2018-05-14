package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospitalms.dto.RoomDto;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Occupancy;
import com.hospitalms.model.Room;
import com.hospitalms.repository.HospitalRepository;
import com.hospitalms.repository.OccupancyRepository;
import com.hospitalms.repository.RoomRepository;

@Service("roomService")
@Transactional
public class RoomService {

	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	ModelMapper mapper;

	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	OccupancyRepository occupancyRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	public List<RoomDto> getRooms() {
		List<Room> roomList=(List<Room>)roomRepository.findAll();
		List<RoomDto> roomDTOList = new ArrayList<RoomDto>();
		for(Room room:roomList)
		{
			RoomDto roomDto=new RoomDto();
			roomDto.setHospital(room.getHospital());
			roomDto.setId(room.getId());
			roomDto.setOccupancy(room.getOccupancy());
			roomDto.setPerDayCharge(room.getPerDayCharge());
			roomDto.setRemainingBeds(room.getRemainingBeds());
			roomDto.setTotalBeds(room.getTotalBeds());
			roomDto.setRoomInfo(room.getRoomInfo());
			roomDto.setVacant(room.isVacant());
			roomDTOList.add(roomDto);
		}
		return roomDTOList;
	}
	

	public List<RoomDto> getRoomsByHospital(Integer hospitalId) {
		Hospital hospital= hospitalRepository.findById(hospitalId).get();
		List<Room> rooms= (List<Room>) roomRepository.findAllByHospital(hospital);
		List<RoomDto> roomDTOList = new ArrayList<RoomDto>();
		for(Room room:rooms)
		{
			RoomDto roomDto=new RoomDto();
			roomDto.setHospital(room.getHospital());
			roomDto.setId(room.getId());
			roomDto.setOccupancy(room.getOccupancy());
			roomDto.setPerDayCharge(room.getPerDayCharge());
			roomDto.setRemainingBeds(room.getRemainingBeds());
			roomDto.setTotalBeds(room.getTotalBeds());
			roomDto.setRoomInfo(room.getRoomInfo());
			roomDto.setVacant(room.isVacant());
			roomDTOList.add(roomDto);
		}
		return roomDTOList;
	}

	public RoomDto addRoom(RoomDto roomDto) {
		Hospital hospital= hospitalRepository.findById(roomDto.getHospital().getId()).get();
		Occupancy occupancy= occupancyRepository.findById(roomDto.getOccupancy().getId()).get();
		Room room= new Room();
		room.setHospital(hospital);
		room.setOccupancy(occupancy);
		setBedStatus(room,occupancy);
		room.setRoomInfo(roomDto.getRoomInfo());
		room.setPerDayCharge(roomDto.getPerDayCharge());
		roomRepository.save(room);
		return roomDto;
	}
	
	public RoomDto updateRoom(RoomDto roomDto) {
		Room room= roomRepository.findById(roomDto.getId()).get();
		Occupancy occupancy= occupancyRepository.findById(roomDto.getOccupancy().getId()).get();
		room.setOccupancy(occupancy);
		setBedStatus(room,occupancy);
		room.setRoomInfo(roomDto.getRoomInfo());
		room.setPerDayCharge(roomDto.getPerDayCharge());
		return roomDto;
	}
	
	public void deleteRoom(Integer id) {
		roomRepository.deleteById(id);
	}

	public RoomDto getRoom(Integer id) {
		Room room=roomRepository.findById(id).get();
		RoomDto roomDto=new RoomDto();
		roomDto.setHospital(room.getHospital());
		roomDto.setId(room.getId());
		roomDto.setOccupancy(room.getOccupancy());
		roomDto.setPerDayCharge(room.getPerDayCharge());
		roomDto.setRemainingBeds(room.getRemainingBeds());
		roomDto.setTotalBeds(room.getTotalBeds());
		roomDto.setRoomInfo(room.getRoomInfo());
		roomDto.setVacant(room.isVacant());
		return roomDto;
	}
	
	public void setBedStatus(Room room,Occupancy occupancy) {
		if(occupancy.getType().equalsIgnoreCase("Single"))
		{
			room.setRemainingBeds(1);
			room.setTotalBeds(1);
			
		}else if(occupancy.getType().equalsIgnoreCase("Double")) 
		{
			room.setRemainingBeds(2);
			room.setTotalBeds(2);
		}else if(occupancy.getType().equalsIgnoreCase("Quad")) 
		{
			room.setRemainingBeds(4);
			room.setTotalBeds(4);
		}
	}
}

