package com.hospitalms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>{

	List<Patient> findAllByHospital(Hospital hospital);

}
