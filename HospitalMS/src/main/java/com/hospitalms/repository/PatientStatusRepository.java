package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.PatientStatus;

public interface PatientStatusRepository extends CrudRepository<PatientStatus, Integer>{

}
