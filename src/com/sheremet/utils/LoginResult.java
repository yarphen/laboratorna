package com.sheremet.utils;

public class LoginResult {
	private final String access_token;
	private final User user;
	public LoginResult(String access_token, User user) {
		super();
		this.access_token = access_token;
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public String getAccess_token() {
		return access_token;
	}
}
