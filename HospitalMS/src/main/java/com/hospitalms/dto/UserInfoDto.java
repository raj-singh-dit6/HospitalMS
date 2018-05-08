package com.hospitalms.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Role;

import lombok.Data;

@Data
public class UserInfoDto {
	
	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String contact;
	private Set<Role> roles;
	private String sessionKey;
	private Hospital hospital;

}
