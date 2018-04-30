package com.hospitalms.dto;

import java.util.Set;

import com.hospitalms.model.TestReport;

import lombok.Data;

@Data
public class BillDto {
	
	private Integer id;
    private Set<TestReport> testReports ;
	private Float amount;
	
}
