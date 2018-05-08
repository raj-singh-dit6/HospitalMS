package com.hospitalms.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hospitalms.model.Doctor;
import com.hospitalms.model.Patient;
import com.hospitalms.utility.CustomDateSerializer;

import lombok.Data;

@Data
public class AppointmentDto {

	private Integer id;
	private Doctor doctor;
	private Patient patient;
	
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date appointmentDateTime;
}
