package com.hospitalms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hospitalms.model.PersistentLogin;

public interface PersistentLoginRespository extends CrudRepository<PersistentLogin,String> {

	PersistentLogin findByUsername(String username);

	Optional<PersistentLogin> findById(String seriesId);
}
