package com.sheremet.utils;

import java.util.HashMap;

public class Command {


	public Command(HashMap<String, Object> map) throws Exception {
		String commandtype = (String) map.get("act");
		switch (commandtype) {
		case 	"value":
		case 	"login":
		case	"getBratchykChildren":
		case	"getHeadBratchyks":
		case	"getBratchyk":
		case	"getBratchykHistory":
		case	"getUserList":
		case	"getUser":
		case	"setBratchyk":
		case	"setUser":
		case	"addUser":
		case	"addBratchyk":
		case	"deleteBratchyk":
		case	"deleteBratchykHistory":
		case	"deleteUser":
			break;

		default:
			throw new Exception();
		}
	}

	public Object execute(boolean cacheble) {
		// TODO Auto-generated method stub
		return null;
	}

}
