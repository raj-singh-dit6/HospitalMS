package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.DoctorPatient;

public interface DoctorPatientRepository extends CrudRepository<DoctorPatient, Integer>{

}
