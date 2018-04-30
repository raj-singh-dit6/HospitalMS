package com.hospitalms.dto;

import java.util.Set;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.Occupancy;
import com.hospitalms.model.Patient;

import lombok.Data;

@Data
public class RoomDto {
	
	private Integer id;
	private Occupancy occupancy;
	private Integer totalBeds;
	private Integer remainingBeds;
	private boolean vacantStatus;
	private Float perDayCharge;
	private Hospital hospital;
	private Set<Patient> patients;
	
}
