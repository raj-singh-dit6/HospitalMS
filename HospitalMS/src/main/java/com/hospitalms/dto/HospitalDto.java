package com.hospitalms.dto;

import com.hospitalms.model.Speciality;

import lombok.Data;

@Data
public class HospitalDto {
	
	private Integer id;
	private String name;
	private String address;
	private boolean active;
	private Long contact;
	private Speciality speciality;
}
