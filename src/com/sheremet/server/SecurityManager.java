package com.sheremet.server;

import java.util.HashMap;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.User;

public class SecurityManager {
	public SecurityManager() {
		// load database
		// and init
	}
	public boolean tryToDo(HashMap<String, Object> command){
		return true;
	}
	public User publicFiltrateUser(User u) {
		u = new User(u);
		u.passhash = null;
		return u;
	}
	public Bratchyk publicFiltrateBratchyk(Bratchyk b) {
		b = new Bratchyk(b);
		b.kontakty = null;
		b.datanarodzhennia = null;
		b.datavysviaty = null;
		b.version_id = null;
		return b;
	}
}
