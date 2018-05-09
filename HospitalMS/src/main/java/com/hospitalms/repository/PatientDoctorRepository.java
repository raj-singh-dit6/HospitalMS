package com.hospitalms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Doctor;
import com.hospitalms.model.Patient;
import com.hospitalms.model.PatientDoctor;

public interface PatientDoctorRepository extends CrudRepository<PatientDoctor, Integer> {

	List<PatientDoctor> findAllByDoctor(Doctor doctor);

	List<PatientDoctor> findAllByPatient(Patient patient);


}
