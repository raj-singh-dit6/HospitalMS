package com.hospitalms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Hospital implements Serializable{

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String adrress;
	
	@Column(nullable=false)
	private boolean active;
	
	@Column(nullable=false)
	private Long contact;
	
	@OneToMany(mappedBy="hospital")
	private Set<Room> rooms;
	
	@OneToMany(mappedBy="hospital")
	private Set<User> users;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	
	
}
