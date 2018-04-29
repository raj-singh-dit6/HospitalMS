package com.hospitalms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Room implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "occupancy_id")
	private Occupancy occupancy;
	
	@Column(nullable=false)
	private Integer totalBeds;
	
	@Column(nullable=false)
	private Integer remainingBeds;
	
	@Column(nullable=false)
	private boolean vacantStatus;
	
	@Column(nullable=false)
	private Float perDayCharge;
	
	@ManyToOne
	@JoinColumn(name="hosp_id", nullable=false)
	private Hospital hospital;
	
	@OneToMany(mappedBy="room")
	private Set<Patient> patients;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
