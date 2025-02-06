package com.hr.scms.dto;

import org.springframework.stereotype.Component;

@Component
public class AuthResponse {

	private String token;

	public AuthResponse() {

	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
