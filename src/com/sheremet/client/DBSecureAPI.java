package com.sheremet.client;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Command;
import com.sheremet.utils.Parser;
import com.sheremet.utils.User;

public class DBSecureAPI {
	private String access_token;
	private ClientConnection connection;
	public DBSecureAPI(ClientConnection connection,  String login, String password) {
//		this.connection = connection;
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("act", "login");
//		map.put("login", login);
//		map.put("password", password);
//		String string  = Parser.unparse(map);
//		connection.send(string, new StringResultHandler() {
//
//			@Override
//			public void handle(String s) throws Exception {
//				HashMap<String, Object> map = Parser.parse(s);
//				if (map.get("success").equals("true")){
//					access_token = (String) map.get("access_token");
//				}else{
//					throw new ConnectException("Cannot connect");
//				}
//			}
//		});
	}
	public boolean regRequest(String login){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "regRequest");
		map.put("login", login);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
	public String login(String login, String password){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "login");
		map.put("login", login);
		map.put("password", password);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		return (String) command.send(connection);
	}
	public Bratchyk[] getBratchykChildren(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "getBratchykChildren");
		map.put("access_token", access_token);
		map.put("id", id);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		return (Bratchyk[]) command.send(connection);
	}
	public Bratchyk[] getHeadBratchyks(){		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "getHeadBratchyks");
		map.put("access_token", access_token);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		return (Bratchyk[]) command.send(connection);
	}
	public Bratchyk getBratchyk(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "getBratchyk");
		map.put("access_token", access_token);
		map.put("id", id);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		return (Bratchyk) command.send(connection);
	}
	public Bratchyk[] getBratchykHistory(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "getBratchykHistory");
		map.put("access_token", access_token);
		map.put("id", id);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		return (Bratchyk[]) command.send(connection);
	}
	public User[] getUserList(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "getUserList");
		map.put("access_token", access_token);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		return (User[]) command.send(connection);
	}
	public User getUser(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "getUser");
		map.put("access_token", access_token);
		map.put("id", id);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		return (User) command.send(connection);
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "setBratchyk");
		map.put("access_token", access_token);
		map.put("bratchyk", bratchyk);
		map.put("id", id);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
	public boolean setUser(User user, long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "setUser");
		map.put("access_token", access_token);
		map.put("user", user);
		map.put("id", id);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
	public boolean addUser(User user){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "addUser");
		map.put("access_token", access_token);
		map.put("user", user);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
	public boolean addBratchyk(Bratchyk bratchyk){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "addBratchyk");
		map.put("access_token", access_token);
		map.put("bratchyk", bratchyk);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
	public boolean deleteBratchyk(long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "deleteBratchyk");
		map.put("access_token", access_token);
		map.put("id", id);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
	public boolean deleteBratchykHistory(long id, int part) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "deleteBratchykHistory");
		map.put("access_token", access_token);
		map.put("id", id);
		map.put("part", part);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
	public boolean deleteUser(long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "deleteUser");
		map.put("access_token", access_token);
		map.put("id", id);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
	public boolean setUserPermission(long id, int permission){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "setUserPermission");
		map.put("access_token", access_token);
		map.put("id", id);
		map.put("permission", permission);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		return (Boolean) command.send(connection);
	}
}

