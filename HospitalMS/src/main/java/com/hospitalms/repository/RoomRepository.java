package com.hospitalms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.Room;

public interface RoomRepository extends CrudRepository<Room, Integer>{

	List<Room> findAllByHospital(Hospital hospital);

}
