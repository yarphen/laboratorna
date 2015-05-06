package com.sheremet.server;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Command;
import com.sheremet.utils.User;

public class CommandExecutor {

	private DBAPI api;
	public CommandExecutor(DBAPI api2) {
		api=api2;
	}
	public Object execute(Command command) {
		switch (command.getType()) {
		case addBratchyk:
		{
			return api.addBratchyk((Bratchyk) command.getMapElement("bratchyk"));
		}
		case addUser:
		{
			return api.addUser((User) command.getMapElement("user"));
		}
		case deleteBratchyk:
		{
			return api.deleteBratchyk((Long) command.getMapElement("id"));
		}
		case deleteBratchykHistory:
		{
			return api.deleteBratchykHistory((Long)command.getMapElement("id"), (Integer)command.getMapElement("part"));
		}
		case deleteUser:
		{
			return api.deleteUser((Long)command.getMapElement("id"));
		}
		case getBratchyk:
		{
			return api.getBratchyk((Long) command.getMapElement("id"));
		}
		case getBratchykChildren:
		{
			return api.getChildrenList((Long)command.getMapElement("id"));
		}
		case getHeadBratchyks:
		{
			return api.getHeadBratchykList();
		}
		case getUser:
		{
			return api.getUser((Long)command.getMapElement("id"));
		}
		case getUserList:
		{
			return api.getUserList();
		}
		case login:
		{
			User u = api.getUser((String)command.getMapElement("login"));
			if (u.passhash ==api.md5( (String)command.getMapElement("password"))){
				String token = api.generateToken(u.passhash);
				api.addAccessToken(u.id, token);
				return token;
			}else return null;
		}
		case regRequest:
			return null;
		case setUser:
		{
			return api.setUser((User)command.getMapElement("user"), (Long)command.getMapElement("id"));
		}
		case getBratchykHistory:
		{
			return api.getBratchykHistory((Long)command.getMapElement("id"));
		}
		case setBratchyk:
		{
			return api.setBratchyk((Bratchyk)command.getMapElement("bratchyk"), (Long)command.getMapElement("id"));
		}
		default:
			return null;
		}

	}
}
