package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>{

}
