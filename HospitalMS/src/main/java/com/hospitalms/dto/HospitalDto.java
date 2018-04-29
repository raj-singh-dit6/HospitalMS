package com.hospitalms.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hospitalms.model.Room;
import com.hospitalms.model.User;

import lombok.Data;

@Data
public class HospitalDto {
	
	private Integer id;
	private String name;
	private String adrress;
	private boolean active;
	private Long contact;
	private Set<Room> rooms;
	private Set<User> users;	
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	

}
