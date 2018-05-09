package com.hospitalms.dto;

import com.hospitalms.model.Doctor;
import com.hospitalms.model.Patient;

import lombok.Data;

@Data
public class PatientDoctorDto {

	private Patient patient;
	private Doctor doctor;
}
