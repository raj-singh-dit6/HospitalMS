package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{

}
