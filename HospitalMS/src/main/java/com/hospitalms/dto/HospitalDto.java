package com.hospitalms.dto;

import java.util.Set;

import com.hospitalms.model.Room;
import com.hospitalms.model.Speciality;
import com.hospitalms.model.User;

import lombok.Data;

@Data
public class HospitalDto {
	
	private Integer id;
	private String name;
	private String address;
	private boolean active;
	private Speciality speciality;
	private Long contact;
	private Set<Room> rooms;
	private Set<User> users;	

}
