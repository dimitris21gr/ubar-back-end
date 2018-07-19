package com.ubar.user.service;

/**
 * @author Dimitris.Anastasopoulos
 */

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ubar.dao.UserDAO;
import com.ubar.user.dto.LoginResponse;

@RestController
@RequestMapping("/user/service")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse login(@RequestBody User loginUser) {
		LoginResponse responseUser = new LoginResponse(-1, null, null);
		try {
			Optional<User> user = userDAO.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
			if (user.isPresent()) 
				responseUser = new LoginResponse(user.get().getId(), user.get().getUsername(), user.get().getType());
			logger.debug(responseUser.toString());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return responseUser;
	}

}
