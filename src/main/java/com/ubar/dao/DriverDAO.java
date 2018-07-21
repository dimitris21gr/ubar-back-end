package com.ubar.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ubar.model.Driver;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Transactional
public interface DriverDAO extends CrudRepository<Driver, Long>{
	
	public Optional<Driver> findById(long id);

}
