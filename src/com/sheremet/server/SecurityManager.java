package com.sheremet.server;

import java.util.HashMap;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.User;

public class SecurityManager {
	DBWrappedAPI api;
	public SecurityManager() {
		// load database
		// and init
	}
	public boolean tryToDo(HashMap<String, Object> command){
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
