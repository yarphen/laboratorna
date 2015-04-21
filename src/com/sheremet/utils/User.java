package com.sheremet.utils;

import java.util.HashMap;

public class User {
	public User(HashMap<String, Object> map) {
		
	}
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
	public Integer permission;
	public Long id;
}
