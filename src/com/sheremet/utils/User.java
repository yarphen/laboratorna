package com.sheremet.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

public class User {
	public User(HashMap<String, Object> map) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<User> userClass = User.class;
		Set<String> set = map.keySet();
		for (String s:set){
			Field f = userClass.getField(s);
			f.set(this, map.get(s));
		}
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
