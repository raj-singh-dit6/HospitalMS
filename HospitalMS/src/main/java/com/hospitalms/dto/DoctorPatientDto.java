package com.hospitalms.dto;

import com.hospitalms.model.Doctor;
import com.hospitalms.model.Patient;

import lombok.Data;

@Data
public class DoctorPatientDto{

	private Integer id;
	private Doctor doctor;
    private Patient patient;
}
