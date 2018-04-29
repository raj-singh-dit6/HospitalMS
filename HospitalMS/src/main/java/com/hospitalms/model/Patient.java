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

import lombok.Data;

@Entity
@Data
public class Patient {

	@GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(nullable = false)
    private Integer id;
 
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	
	@ManyToOne
	@JoinColumn(name="room_id", nullable=false)
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_status_id")
	private PatientStatus patientStatus;
	
	@Column(nullable = true)
	private Date admittedDate;
	
	@Column(nullable = true)
	private Date discharedDate;
	
	@OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@OneToMany(mappedBy = "patient")
    private Set<TestReport> testReports = new HashSet<TestReport>();
	
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
