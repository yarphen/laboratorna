package com.sheremet.server;

import java.util.HashMap;

import com.sheremet.utils.Commands;

public class Permissions{
	public static final int GUEST = 0;
	public static final int AUTHORIZEDUSER = 1;
	public static final int SBUSER = 2;
	public static final int ADMIN = 3;
	private static final HashMap<Commands, Integer> map;
	static{
		map = new HashMap<Commands, Integer>();
		map.put(Commands.addBratchyk, ADMIN);
		map.put(Commands.addUser, GUEST);
		map.put(Commands.deleteBratchyk, ADMIN);
		map.put(Commands.deleteBratchykHistory, ADMIN);
		map.put(Commands.deleteUser, AUTHORIZEDUSER);
		map.put(Commands.getBratchyk, GUEST);
		map.put(Commands.getBratchykChildren, GUEST);
		map.put(Commands.getBratchykHistory, ADMIN);
		map.put(Commands.getHeadBratchyks, GUEST);
		map.put(Commands.getUser, AUTHORIZEDUSER);
		map.put(Commands.getUserList, ADMIN);
		map.put(Commands.login, GUEST);
		map.put(Commands.setBratchyk, ADMIN);
		map.put(Commands.setUser, AUTHORIZEDUSER);
	}
	public static Integer get(Object key) {
		return map.get(key);
	}
	
}