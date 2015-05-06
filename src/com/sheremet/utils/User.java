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

	}
	public User(User u) {
		email = u.email;
		id = u.id;
		name = u.name;
		passhash = u.passhash;
		permission = u.permission;
	}
	public String email;
	public Long id;
	public String name;
	public String passhash;
	public Integer permission;
	
}
