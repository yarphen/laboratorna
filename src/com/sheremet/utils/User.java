package com.sheremet.utils;

public class User {
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(User u) {
		name = u.name;
		email = u.email;
		passhash = u.passhash;
		permission = u.permission;
		id = u.id;
	}
	public String name;
	public String email;
	public String passhash;
	public int permission;
	public long id;
}
