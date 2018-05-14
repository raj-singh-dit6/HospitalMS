package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;
import com.hospitalms.model.Bill;
import com.hospitalms.model.Patient;

public interface BillRepository extends CrudRepository<Bill, Integer>{

	Bill findByPatient(Patient patient);

}
