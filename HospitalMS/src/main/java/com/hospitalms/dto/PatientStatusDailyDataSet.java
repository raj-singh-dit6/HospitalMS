package com.hospitalms.dto;

import java.util.List;

import lombok.Data;

@Data
public class PatientStatusDailyDataSet {
	
	List<Integer> admittedList;
	List<Integer> dischargedList;

}
