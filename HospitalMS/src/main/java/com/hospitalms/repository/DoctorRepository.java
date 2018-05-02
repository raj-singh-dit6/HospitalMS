package com.hospitalms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Doctor;
import com.hospitalms.model.Hospital;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {


	List<Doctor> findAllByHospital(Hospital hospital);

}
