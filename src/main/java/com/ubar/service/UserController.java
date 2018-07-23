package com.ubar.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ubar.dao.DriverDAO;
import com.ubar.dao.PassengerDAO;
import com.ubar.dao.UserDAO;
import com.ubar.model.Driver;
import com.ubar.model.Passenger;
import com.ubar.model.User;
import com.ubar.user.dto.LoginRequest;
import com.ubar.user.dto.LoginResponse;

/**
 * All services regarding the user entity
 * 
 * @author Dimitris.Anastasopoulos
 *
 */
@RestController
@RequestMapping("/user/service")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	PassengerDAO passengerDAO;
	
	@Autowired
	DriverDAO driverDAO;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse login(@RequestBody LoginRequest loginUser) {
		LoginResponse responseUser = new LoginResponse(-1, null, null);
		try {
			if (loginUser.getType().equals("passenger")) {
				Optional<Passenger> user = passengerDAO.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
				if (user.isPresent()) {
					responseUser = modelMapper.map(user.get(), LoginResponse.class);
					responseUser.setType("passenger");
				}
			}
			else if  (loginUser.getType().equals("driver")) {
				Optional<Driver> user = driverDAO.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
				if (user.isPresent()) {
					responseUser = modelMapper.map(user.get(), LoginResponse.class);
					responseUser.setType("driver");
				}
			}
			logger.debug(responseUser.toString());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return responseUser;
	}
	
	@RequestMapping(value = "/checkUsername/{username}", method = RequestMethod.GET)
	public boolean usernameExists(@PathVariable String username) {
		boolean found = false;
		try {
			Optional<User> user = userDAO.findByUsername(username);
			if (user.isPresent())
				found = true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		logger.debug("username found: " + found);
		return found;
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public boolean emailExists(@RequestBody String email) {
		boolean found = false;
		try {
			Optional<User> user = userDAO.findByEmail(email);
			if (user.isPresent())
				found = true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		logger.debug("email found: " + found);
		return found;
	}
	
	@RequestMapping(value = "/register/{type}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean register(@RequestBody User user, @PathVariable String type) {
		try {
			if (type.equals("driver")) {
				Driver driver = modelMapper.map(user, Driver.class);
				driverDAO.save(driver);
				logger.debug("User - driver saved to Database!");
			}
			else if (type.equals("passenger")) {
				Passenger passenger = modelMapper.map(user, Passenger.class);
				passengerDAO.save(passenger);
				logger.debug("User - passenger saved to Database!");
			}
			else
				return false;
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
