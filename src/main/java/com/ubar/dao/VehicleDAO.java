package com.ubar.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ubar.model.Vehicle;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Transactional
public interface VehicleDAO extends CrudRepository<Vehicle, Long>{

}
