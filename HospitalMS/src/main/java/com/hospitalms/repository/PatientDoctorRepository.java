package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.PatientDoctor;

public interface PatientDoctorRepository extends CrudRepository<PatientDoctor, Integer> {

}
