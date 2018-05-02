package com.hospitalms.dto;

import com.hospitalms.model.User;

import lombok.Data;

@Data
public class UserSessionDto {

	private Integer id;
	private User user;
	private String sessionKey;
	private Boolean active;
}
