package com.hospitalms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	private Boolean active;
	
	@Column(nullable=false)
	private Long contact;
	
	@OneToOne(mappedBy="hospital",cascade = CascadeType.ALL)
	private Head head;
	
	@JsonIgnore
	@OneToMany(mappedBy="hospital",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Doctor> doctors= new HashSet<Doctor>();
	
	@JsonIgnore
	@OneToMany(mappedBy="hospital",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Patient> patients = new HashSet<Patient>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "speciality_id",nullable=false)
	private Speciality speciality;
	
	@JsonIgnore
	@OneToMany(mappedBy="hospital",fetch = FetchType.LAZY)
	private Set<Room> rooms = new HashSet<Room>();

	@JsonIgnore
	@OneToMany(mappedBy="hospital",fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<User>();
	
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
