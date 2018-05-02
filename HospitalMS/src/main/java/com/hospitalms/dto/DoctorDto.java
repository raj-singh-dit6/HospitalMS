package com.hospitalms.dto;

import com.hospitalms.model.Department;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.User;

import lombok.Data;

@Data
public class DoctorDto {

	private Integer id;
	private String description;
	private boolean active;
	private User user;
	private Department department;
	private Hospital hospital;

}
