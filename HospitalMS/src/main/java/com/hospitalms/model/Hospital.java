package com.hospitalms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String address;
	
	@Column(nullable=false)
	private boolean active;
	
	@Column(nullable=false)
	private Long contact;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "speciality_id",nullable=false)
	private Speciality speciality;
	
	@JsonIgnore
	@OneToMany(mappedBy="hospital")
	private Set<Room> rooms;

	@JsonIgnore
	@OneToMany(mappedBy="hospital")
	private Set<User> users;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	
	
}
