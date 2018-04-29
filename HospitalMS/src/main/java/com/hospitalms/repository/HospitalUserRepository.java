package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Hospital;

public interface HospitalUserRepository extends CrudRepository<Hospital, Integer> {

}
