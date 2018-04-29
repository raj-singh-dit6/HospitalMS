package com.hospitalms.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hospitalms.model.Doctor;
import com.hospitalms.model.Document;
import com.hospitalms.model.Patient;

import lombok.Data;

@Data
public class PrescriptionDto {

	private Integer id;
	private Doctor doctor;
	private Patient patient;
	private Set<Document> document;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
}
