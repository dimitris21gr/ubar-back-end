package com.ubar.dao;

/**
 * @author Dimitris.Anastasopoulos
 */

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ubar.model.User;

@Transactional
public interface UserDAO extends CrudRepository<User, Long>{
	
	public Optional<User> findByUsernameAndPasswordAndType(String username, String password, String type);
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByEmail(String email);

}
