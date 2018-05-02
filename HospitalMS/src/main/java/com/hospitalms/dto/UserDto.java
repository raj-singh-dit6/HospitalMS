package com.hospitalms.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hospitalms.model.Hospital;
import com.hospitalms.model.Patient;
import com.hospitalms.model.Role;
import com.hospitalms.model.UserSession;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private Date dob;
    private String email;
    private Long contact;
	private Hospital hospital;
	private Patient patient;
	private UserSession userSession;
	
}
