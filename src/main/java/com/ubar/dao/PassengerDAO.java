package com.ubar.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import com.ubar.model.Passenger;


/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Transactional
public interface PassengerDAO extends UserBaseDAO<Passenger>{
	
	public Optional<Passenger> findById(long id);

}
