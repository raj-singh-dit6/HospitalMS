package com.hospitalms.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Patient {

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(nullable = false)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;

	@JsonIgnore
	@ManyToOne(optional=true)
	@JoinColumn(name = "room_id")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "hosp_id",nullable = false) 
	private Hospital hospital;

	@JsonIgnore
	@ManyToOne(optional=true)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "patient_status_id",nullable = false)
	private PatientStatus patientStatus;

	@Column
	private LocalDateTime admittedDate;

	@Column
	private LocalDateTime dischargedDate;

	@Column
	private LocalDateTime attendedDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@JsonIgnore
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TestReport> testReports = new HashSet<TestReport>();

	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

}
