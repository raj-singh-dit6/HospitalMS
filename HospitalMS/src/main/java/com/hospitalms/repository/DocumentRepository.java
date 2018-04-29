package com.hospitalms.repository;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.Document;

public interface DocumentRepository extends CrudRepository<Document, Integer>{

}
