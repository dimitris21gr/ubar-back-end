package com.ubar.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ubar.model.User;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Transactional
public interface UserDAO extends CrudRepository<User, Long>{
	
	public Optional<User> findByUsernameAndPassword(String username, String password);
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByEmail(String email);

}
