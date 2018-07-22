package com.ubar.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import com.ubar.model.Driver;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Transactional
public interface DriverDAO extends UserBaseDAO<Driver>{
	
	public Optional<Driver> findById(long id);

}
