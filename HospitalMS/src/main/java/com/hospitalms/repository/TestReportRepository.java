package com.hospitalms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.hospitalms.model.Patient;
import com.hospitalms.model.TestReport;

public interface TestReportRepository extends CrudRepository<TestReport, Integer>{

	List<TestReport> findAllByPatient(Patient patient);
	
}
