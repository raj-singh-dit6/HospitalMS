package com.hospitalms.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Data
@JsonInclude(Include.NON_NULL)
public class Room implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String roomInfo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "occupancy_id")
	private Occupancy occupancy;
	
	@Column(nullable=false)
	private Integer totalBeds;
	
	@Column(nullable=false)
	private Integer remainingBeds;
	
	@Column(nullable=false)
	private boolean vacant=true;
	
	@Column(nullable=false)
	private Float perDayCharge;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="hosp_id", nullable=false)
	private Hospital hospital;
	
	@JsonIgnore
	@OneToMany(mappedBy="room",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Patient> patients;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
