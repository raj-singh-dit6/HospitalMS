package com.hospitalms.dto;

import java.time.LocalDateTime;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.User;

import lombok.Data;

@Data
public class HospitalUserDto {
	
	private Integer id;
	private User user;
    private Hospital hospital;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
}
