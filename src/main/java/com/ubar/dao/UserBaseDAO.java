package com.ubar.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ubar.model.User;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@NoRepositoryBean
public interface UserBaseDAO<T extends User> extends CrudRepository<T, Long>{
	
	public Optional<T> findByUsernameAndPassword(String username, String password);

}
