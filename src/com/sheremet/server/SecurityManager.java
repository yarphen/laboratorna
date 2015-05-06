package com.sheremet.server;

import java.util.HashMap;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Command;
import com.sheremet.utils.User;

public class SecurityManager {
	DBAPI api;
	public SecurityManager(DBAPI dbapi) {
		// load database
		// and init
		api = dbapi;
	}
	public Object tryToDo(Command command){
		int permission = api.getPermissionOfTheToken((String) command.getMapElement("access_token"));
		
		return true;
	}
	public User filtrateUser(User u) {
		u = new User(u);
		u.passhash = null;
		return u;
	}
	public Bratchyk filtrateBratchyk(Bratchyk b, String access_token) {
		if (api.getPermissionOfTheToken(access_token)>1){
			b = new Bratchyk(b);
			b.kontakty = null;
			b.datanarodzhennia = null;
			b.datavysviaty = null;
			b.version_id = null;
		}
		return b;
	}
}
