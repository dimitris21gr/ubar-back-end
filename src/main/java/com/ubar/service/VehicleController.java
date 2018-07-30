package com.ubar.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ubar.dao.DriverDAO;
import com.ubar.dao.VehicleDAO;
import com.ubar.model.Driver;
import com.ubar.model.Vehicle;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	private final static Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
	@Autowired
	private VehicleDAO vehicleDAO;
	
	@Autowired
	private DriverDAO driverDAO;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean addVehicle(@RequestBody Vehicle vehicle) {
		boolean status = false;
		try {
			Optional<Driver> driver = driverDAO.findById(vehicle.getId());
			if (driver.isPresent()) {
				vehicle.setDriver(driver.get());
				vehicle.setId(-1);
				vehicleDAO.save(vehicle);
				status = true;
			} else {
				status = false;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			status = false;
		}
		logger.debug("Card added: " + status);
		return status;
	}

}
