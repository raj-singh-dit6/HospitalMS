package com.hospitalms.dto;

import java.time.LocalDateTime;
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
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
}
