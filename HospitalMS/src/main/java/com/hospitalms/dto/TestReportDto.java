package com.hospitalms.dto;

import java.time.LocalDateTime;

import com.hospitalms.model.Bill;
import com.hospitalms.model.Patient;
import com.hospitalms.model.Test;

import lombok.Data;

@Data
public class TestReportDto {

    private Integer id;
	private Test test;
	private String description;
	private Patient patient;
	private Bill bill;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
}
