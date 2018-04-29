package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

}
