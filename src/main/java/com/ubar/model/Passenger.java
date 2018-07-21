package com.ubar.model;

import javax.persistence.Entity;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Entity
public class Passenger extends User {

	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
}
