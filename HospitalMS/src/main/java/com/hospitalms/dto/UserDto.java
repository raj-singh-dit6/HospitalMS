package com.hospitalms.dto;

import java.util.Date;

import com.hospitalms.model.Doctor;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.Patient;
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
	private Doctor doctor;
	private UserSession userSession;
	
}
