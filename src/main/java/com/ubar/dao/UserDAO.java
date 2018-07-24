package com.ubar.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import com.ubar.model.User;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Transactional
public interface UserDAO extends UserBaseDAO<User>{
	
	public Optional<User> findById(long id);
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByEmail(String email);

}
