package com.ubar.user.dto;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
public class PasswordRequest {
	
	private long id;
	
	private String password;

	public PasswordRequest() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
