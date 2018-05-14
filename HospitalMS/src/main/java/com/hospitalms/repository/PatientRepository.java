package com.hospitalms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.Patient;
import com.hospitalms.model.Room;

public interface PatientRepository extends CrudRepository<Patient, Integer>{

	/** Named parameter
	 * **/
	@Query("SELECT p FROM Patient p WHERE p.admittedDate = '' OR p.admittedDate!=null AND p.hospital=:hospital")
	List<Patient> findAllAdmittedPatientsByHospital(@Param("hospital")Hospital hospital);

	List<Patient> findAllByRoom(Room room);

	List<Patient> findAllByHospital(Hospital hospital);

}
