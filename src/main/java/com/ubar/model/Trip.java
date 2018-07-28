package com.ubar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Entity
public class Trip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date departure, arrival;
	
	/**
	 * distance in km - price in euros
	 */
	private double distance, price;
	
	/**
	 * google maps longitude and latitude
	 */
	private String depLong, depLat, destLong, destLat;
	
	@NotNull
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@ManyToOne
    @JoinColumn(name = "passenger")
	private Passenger passenger;

	@NotNull
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@ManyToOne
    @JoinColumn(name = "driver")
	private Driver driver;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDepLong() {
		return depLong;
	}

	public void setDepLong(String depLong) {
		this.depLong = depLong;
	}

	public String getDepLat() {
		return depLat;
	}

	public void setDepLat(String deplat) {
		this.depLat = deplat;
	}

	public String getDestLong() {
		return destLong;
	}

	public void setDestLong(String destLong) {
		this.destLong = destLong;
	}

	public String getDestLat() {
		return destLat;
	}

	public void setDestLat(String destLat) {
		this.destLat = destLat;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
}
