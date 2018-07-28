package com.ubar.user.dto;

/**
 * @author Dimitris.Anastasopoulos
 *
 */
public class AvatarRequest {
	
	private long id;
	
	private String avatar, type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
