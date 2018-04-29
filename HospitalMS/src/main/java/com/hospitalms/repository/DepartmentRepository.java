package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
