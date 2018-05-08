package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Head;
import com.hospitalms.model.Hospital;

public interface HeadRepository extends CrudRepository<Head, Integer> {

	Head findByHospital(Hospital hospital);

}
