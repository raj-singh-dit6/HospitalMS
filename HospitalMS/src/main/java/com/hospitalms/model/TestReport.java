package com.hospitalms.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class TestReport {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "test_id")
	private Test test;
	
	@Column(nullable=false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="bill_id", nullable=false)
	private Bill bill;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
