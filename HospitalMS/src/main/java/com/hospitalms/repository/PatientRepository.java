package com.hospitalms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.Patient;
import com.hospitalms.model.Room;

public interface PatientRepository extends CrudRepository<Patient, Integer>{

	List<Patient> findAllByHospital(Hospital hospital);

	List<Patient> findAllByRoom(Room room);

}
