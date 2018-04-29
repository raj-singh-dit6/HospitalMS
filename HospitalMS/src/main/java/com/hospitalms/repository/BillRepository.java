package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Bill;

public interface BillRepository extends CrudRepository<Bill, Integer>{

}
