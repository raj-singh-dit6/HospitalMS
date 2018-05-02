package com.hospitalms.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Prescription {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")  
	private Doctor doctor;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")  
	private Patient patient;
	
	@JsonIgnore
	@OneToMany(mappedBy="prescription",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Document> document;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
}
