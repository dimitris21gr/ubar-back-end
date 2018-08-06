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
import com.ubar.dao.VehicleDAO;
import com.ubar.model.Driver;
import com.ubar.model.Passenger;
import com.ubar.model.User;
import com.ubar.model.Vehicle;
import com.ubar.user.dto.AvatarRequest;
import com.ubar.user.dto.DriverRegisterRequest;
import com.ubar.user.dto.LoginRequest;
import com.ubar.user.dto.LoginResponse;
import com.ubar.user.dto.PasswordRequest;
import com.ubar.user.dto.UserProfileResponse;
import com.ubar.utilities.Image;

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
	VehicleDAO vehicleDAO;

	@Autowired
	DriverDAO driverDAO;

	private ModelMapper modelMapper = new ModelMapper();

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse login(@RequestBody LoginRequest loginUser) {
		LoginResponse responseUser = new LoginResponse(-1, null, null);
		try {
			if (loginUser.getType().equals("passenger")) {
				Optional<Passenger> user = passengerDAO.findByUsernameAndPassword(loginUser.getUsername(),
						loginUser.getPassword());
				if (user.isPresent()) {
					responseUser = modelMapper.map(user.get(), LoginResponse.class);
					responseUser.setType("passenger");
				}
			} else if (loginUser.getType().equals("driver")) {
				Optional<Driver> user = driverDAO.findByUsernameAndPassword(loginUser.getUsername(),
						loginUser.getPassword());
				if (user.isPresent()) {
					responseUser = modelMapper.map(user.get(), LoginResponse.class);
					responseUser.setType("driver");
				}
			}
			logger.debug(responseUser.toString());
		} catch (Exception ex) {
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
		} catch (Exception ex) {
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.debug("email found: " + found);
		return found;
	}

	@RequestMapping(value = "/register/passenger", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerPassenger(@RequestBody User user) {
		boolean status = false;
		try {
			user.setAvatar(Image.DEFAULT_PATH);
			Passenger passenger = modelMapper.map(user, Passenger.class);
			passengerDAO.save(passenger);
			status = true;
			logger.debug("User - passenger saved to Database!");
		} catch (Exception ex) {
			ex.printStackTrace();
			status = false;
		}
		return status;
	}

	@RequestMapping(value = "/register/driver", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerDriver(@RequestBody DriverRegisterRequest request) {
		boolean status = false;
		try {
			Driver driver = modelMapper.map(request, Driver.class);
			driver.setAvatar(Image.DEFAULT_PATH);
			driverDAO.save(driver);
			Vehicle vehicle = modelMapper.map(request, Vehicle.class);
			vehicle.setDriver(driver);
			vehicleDAO.save(vehicle);
			status = true;
			logger.debug("User - driver saved to Database!");
		} catch (Exception ex) {
			ex.printStackTrace();
			status = false;
		}
		return status;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean changePassword(@RequestBody PasswordRequest pr) {
		boolean done = false;
		try {
			Optional<User> user = userDAO.findById(pr.getId());
			if (user.isPresent()) {
				user.get().setPassword(pr.getPassword());
				userDAO.save(user.get());
				done = true;
				logger.debug("Password changed!");
			} else {
				logger.debug("No such user");
				done = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			done = false;
		}
		return done;
	}

	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean uploadAvatar(@RequestBody AvatarRequest avatar) {
		boolean status = false;
		String name = "avatar_" + avatar.getId() + avatar.getType();
		try {
			Optional<User> user = userDAO.findById(avatar.getId());
			if (user.isPresent()) {
				// If user has not default avatar
				if (!user.get().getAvatar().equals(Image.DEFAULT_PATH)) {
					Image.delete(user.get().getAvatar());
					user.get().setAvatar(Image.DEFAULT_PATH);
				}
				Image.decoder(avatar.getAvatar(), Image.PATH + name);
				user.get().setAvatar(Image.PATH + name);
				userDAO.save(user.get());
				logger.debug("Avatar uploaded!");
				status = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			status = false;
		}
		return status;
	}
	
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public UserProfileResponse getProfile(@PathVariable long id) {
		UserProfileResponse response = new UserProfileResponse();
		try {
			Optional<User> user = userDAO.findById(id);
			if (user.isPresent()) {
				response = modelMapper.map(user.get(), UserProfileResponse.class);
				logger.debug("User profile found!");
			}
			else
				logger.debug("User profile NOT found!");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

}
