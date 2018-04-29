package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Occupancy;

public interface OccupancyRepository extends CrudRepository<Occupancy,Integer>{

	Occupancy findAllById(Integer id);

}
