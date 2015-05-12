package com.sheremet.server;

import java.util.HashMap;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Command;
import com.sheremet.utils.User;

public class SecurityManager {
	DBAPI api;
	CommandExecutor commandExecutor;
	public SecurityManager(DBAPI dbapi) {
		// load database
		// and init
		api = dbapi;
		commandExecutor = new CommandExecutor(api);
	}
	public Object tryToDo(Command command){
		String access_token = (String) command.getMapElement("access_token");
		int permission = api.getPermissionOfTheToken(access_token);
		if (Permissions.get(command.getType())<=permission){
			Object result =  commandExecutor.execute(command);
			if (result!=null){
				String resultType = result.getClass().getName();
				switch (resultType) {

				case "[Lcom.sheremet.utils.Bratchyk;":{
					Bratchyk[] bratchyks = (Bratchyk[]) result;
					for (int i=0; i<bratchyks.length; i++){
						bratchyks[i] = filtrateBratchyk(bratchyks[i], permission);
					}
					result = bratchyks;
				}
				break;
				case "com.sheremet.utils.User":{
					User user = (User) result;
					user = filtrateUser(user);
					result = user;
				}
				break;
				case "[Lcom.sheremet.utils.User;":{
					User[] users = (User[]) result;
					for (int i=0; i<users.length; i++){
						users[i] = filtrateUser(users[i]);
					}
					result = users;
				}
				break;
				case "com.sheremet.utils.Bratchyk":{
					Bratchyk  bratchyk = (Bratchyk) result;
					bratchyk = filtrateBratchyk(bratchyk, permission);
					result = bratchyk;
				}
				break;
				}
			}
			return result;
		}else 
			return "Exception occured: illegal access";
	}
	public User filtrateUser(User u) {
		u = new User(u);
		u.passhash = null;
		return u;
	}
	public Bratchyk filtrateBratchyk(Bratchyk b, int permission) {
		if (permission<Permissions.SBUSER){
			b = new Bratchyk(b);
			b.kontakty = null;
			b.datanarodzhennia = null;
			b.datavysviaty = null;
			b.version_id = null;
		}
		return b;
	}
	public static void main(String[] args) {
		Bratchyk[] bratchyks = new Bratchyk[0];
		User user = new  User();
		User[]users = new User[1];
		Object o = true;
		String s = "";
		Bratchyk bratchyk= new Bratchyk();
		System.out.println(bratchyks.getClass().getName());
		System.out.println(user.getClass().getName());
		System.out.println(users.getClass().getName());
		System.out.println(o.getClass().getName());
		System.out.println(s.getClass().getName());
		System.out.println(bratchyk.getClass().getName());
	}
}
