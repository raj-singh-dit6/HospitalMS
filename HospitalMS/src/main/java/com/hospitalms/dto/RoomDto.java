package com.hospitalms.dto;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.Occupancy;

import lombok.Data;

@Data
public class RoomDto {
	
	private Integer id;
	private String roomInfo;
	private Occupancy occupancy;
	private Integer totalBeds;
	private Integer remainingBeds;
	private boolean vacant;
	private Float perDayCharge;
	private Hospital hospital;
	
}
