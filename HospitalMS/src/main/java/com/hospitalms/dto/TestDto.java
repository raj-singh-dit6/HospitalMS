package com.hospitalms.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TestDto {

	private Integer id;
	private String name;
	private Float charge;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
}