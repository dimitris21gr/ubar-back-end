package com.ubar.user.dto;

/**
 * @author Dimitris.Anastasopoulos
 */

public class LoginResponse {
	
	private long id;
	
	private String username, type;
	
	public LoginResponse() {
		super();
	}
	
	public LoginResponse(long id, String username, String type) {
		super();
		this.id = id;
		this.username = username;
		this.type = type;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "LoginResponse [id=" + id + ", username=" + username + ", type=" + type + "]";
	}
	
}
