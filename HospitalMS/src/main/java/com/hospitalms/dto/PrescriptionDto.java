package com.hospitalms.dto;

import java.util.Set;

import com.hospitalms.model.Doctor;
import com.hospitalms.model.Document;
import com.hospitalms.model.Patient;

import lombok.Data;

@Data
public class PrescriptionDto {

	private Integer id;
	private Doctor doctor;
	private Patient patient;
}
