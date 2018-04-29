package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.TestReport;

public interface TestReportRepository extends CrudRepository<TestReport, Integer>{
	
}
