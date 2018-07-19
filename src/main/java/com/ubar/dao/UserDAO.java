package com.ubar.dao;

/**
 * @author Dimitris.Anastasopoulos
 */

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ubar.user.service.User;

@Transactional
public interface UserDAO extends CrudRepository<User, Long>{
	
	public Optional<User> findByUsernameAndPassword(String username, String password);

}
