package com.hospitalms.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hospitalms.model.Appointment;
import com.hospitalms.model.Doctor;
import com.hospitalms.model.PatientStatus;
import com.hospitalms.model.Room;
import com.hospitalms.model.TestReport;
import com.hospitalms.model.User;

import lombok.Data;

@Data
public class PatientDto {

    private Integer id;
	private User user;
	private Room room;
	private Doctor doctor;
	private PatientStatus patientStatus;
	private Date admittedDate;
	private Date discharedDate;
    private Set<Appointment> appointments = new HashSet<Appointment>();
    private Set<TestReport> testReports = new HashSet<TestReport>();
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
}
