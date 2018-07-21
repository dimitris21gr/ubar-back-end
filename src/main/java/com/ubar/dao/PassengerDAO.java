package com.ubar.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ubar.model.Passenger;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Transactional
public interface PassengerDAO extends CrudRepository<Passenger, Long>{
	
	public Optional<Passenger> findById(long id);

}
