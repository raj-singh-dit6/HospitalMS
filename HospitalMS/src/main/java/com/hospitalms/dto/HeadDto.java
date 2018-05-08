package com.hospitalms.dto;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.User;

import lombok.Data;

@Data
public class HeadDto {

	private Integer id;
	private User user;
	private Hospital hospital;
}
