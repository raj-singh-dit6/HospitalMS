package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Integer> {

}
