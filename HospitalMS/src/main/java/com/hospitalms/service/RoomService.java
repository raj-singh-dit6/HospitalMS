package com.hospitalms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitalms.dto.RoomDto;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Room;
import com.hospitalms.repository.RoomRepository;

@Service("roomService")
public class RoomService {

	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	RoomRepository roomRepository;
	
	public List<RoomDto> getRooms() {
		List<Room> roomList=(List<Room>)roomRepository.findAll();
		List<RoomDto> roomDTOList = new ArrayList<RoomDto>();
		for(Room room:roomList)
		{
			RoomDto roomDto= mapper.map(room, RoomDto.class);
			roomDTOList.add(roomDto);
		}
		return roomDTOList;
	}
	
	public List<RoomDto> getRoomsByHospital(Hospital hospital) {
		List<Room> roomList=(List<Room>)roomRepository.findAllByHospital(hospital);
		List<RoomDto> roomDTOList = new ArrayList<RoomDto>();
		for(Room room:roomList)
		{
			RoomDto roomDto= mapper.map(room, RoomDto.class);
			roomDTOList.add(roomDto);
		}
		return roomDTOList;
	}
	
	public RoomDto addRoom(RoomDto roomDto) {
		Room room= mapper.map(roomDto, Room.class);
		roomRepository.save(room);
		return roomDto;
	}
	
	public RoomDto updateRoom(RoomDto roomDto) {
		Room room= roomRepository.findById(roomDto.getId()).get();
		return roomDto;
	}
	
	public void deleteRoom(Integer id) {
		roomRepository.deleteById(id);
	}

	public RoomDto getRoom(Integer id) {
		return mapper.map(roomRepository.findById(id),RoomDto.class);
	}
}

