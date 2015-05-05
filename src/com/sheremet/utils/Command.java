package com.sheremet.utils;

import java.util.HashMap;

import com.sheremet.client.ClientConnection;
import com.sheremet.client.StringResultHandler;

public class Command {
	private Commands type;
	private HashMap map;

	public Command(HashMap<String, Object> map) throws Exception {
		String commandtype = (String) map.get("act");
		switch (commandtype) {
		case 	"regRequest": type = Commands.regRequest;break;
		case 	"login": type = Commands.login;break;
		case	"getBratchykChildren":type = Commands.getBratchykChildren;break;
		case	"getHeadBratchyks":type = Commands.getHeadBratchyks;break;
		case	"getBratchyk":type = Commands.getBratchyk;break;
		case	"getBratchykHistory":type = Commands.getBratchykHistory;break;
		case	"getUserList":type = Commands.getUserList;break;
		case	"getUser":type = Commands.getUser;break;
		case	"setBratchyk":type = Commands.setBratchyk;break;
		case	"setUser":type = Commands.setUser;break;
		case	"addUser":type = Commands.addUser;break;
		case	"addBratchyk":type = Commands.addBratchyk;break;
		case	"deleteBratchyk":type = Commands.deleteBratchyk;break;
		case	"deleteBratchykHistory":type = Commands.deleteBratchykHistory;break;
		case	"deleteUser":type = Commands.deleteUser;break;
		default:
			throw new Exception("Incorrect command: "+commandtype);
		}
		this.map=map;
	}

	public Object send(final ClientConnection connection) {
		String str = Parser.unparseXMLstring(type, map);
		final Result result = new Result();
		connection.send(str, new StringResultHandler() {
			
			@Override
			public void handle(String s) throws Exception {
				result.setValue(Parser.parserXMLstring(s));
				synchronized (connection) {
					connection.notifyAll();
				}
			}
		});
		try {
			synchronized (connection) {
				connection.wait();
			}
		} catch (InterruptedException e) {}
		
		return result.getValue();
	}

}
