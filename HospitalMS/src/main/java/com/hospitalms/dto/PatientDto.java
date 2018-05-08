package com.hospitalms.dto;

import java.util.Date;

import com.hospitalms.model.Doctor;
import com.hospitalms.model.Hospital;
import com.hospitalms.model.PatientStatus;
import com.hospitalms.model.Room;
import com.hospitalms.model.User;

import lombok.Data;

@Data
public class PatientDto {

	private Integer id;
	private User user;
	private Room room;
	private Hospital hospital;
	private Doctor doctor;
	private PatientStatus patientStatus;
	private Date admittedDate;
	private Date dischargedDate;
	private Date attendedDate;
}
