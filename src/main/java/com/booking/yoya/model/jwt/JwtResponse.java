package com.booking.yoya.model.jwt;

import com.booking.yoya.model.User;

public class JwtResponse {

	private User user;
	private String jwtToken;
	private String message;
	private boolean status;

	public JwtResponse(User user, String jwtToken, String message, boolean status) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
		this.message = message;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
