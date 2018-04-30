package com.hospitalms.dto;

import java.util.HashSet;
import java.util.Set;

import com.hospitalms.model.Appointment;
import com.hospitalms.model.Department;
import com.hospitalms.model.Patient;
import com.hospitalms.model.User;

import lombok.Data;

@Data
public class DoctorDto {

    private Integer id;
	private User user;
	private String description;
	private boolean active;
    private Set<Appointment> appointments = new HashSet<Appointment>();
	private Department department;
	private Set<Patient> patients;
	
}
