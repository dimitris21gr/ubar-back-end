package com.ubar.model;

import javax.persistence.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class Passenger extends User {

	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
}
