package com.sheremet.client;

import java.util.HashMap;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Command;
import com.sheremet.utils.LoginResult;
import com.sheremet.utils.User;

public class DBSecureAPI {
	private String access_token="";
	private User user;
	private ClientConnection connection;
	private ClientFrame clientFrame;
	public DBSecureAPI(ClientConnection connection) {
		this.connection = connection;
	}
	public void setClientFrame(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
	}
	public LoginResult login(String login, String password) throws ServerException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "login");
		map.put("access_token", access_token);
		map.put("login", login);
		map.put("password", password);
		Command command = null;
		try {
			command = new Command(map);
			LoginResult result = (LoginResult) command.send(connection);
			access_token = result.getAccess_token();
			user = result.getUser();
			if (clientFrame!=null){
				clientFrame.setUser(user);
				clientFrame.setLogged(true);
			}
			return result;
		} catch (Exception e) {
			try{
				return new LoginResult("", null);
			}catch(ClassCastException exception){
				throw new ServerException((String)command.send(connection));
			}
		}
	}
	public Bratchyk[] getBratchykChildren(long id) throws ServerException{
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
		try{
			return (Bratchyk[]) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public Bratchyk[] getHeadBratchyks() throws ServerException{		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "getHeadBratchyks");
		map.put("access_token", access_token);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		try{
			return (Bratchyk[]) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public Bratchyk getBratchyk(long id) throws ServerException{
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
		try{
			return (Bratchyk) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public Bratchyk[] getBratchykHistory(long id) throws ServerException{
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
		try{
			return (Bratchyk[]) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public User[] getUserList() throws ServerException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "getUserList");
		map.put("access_token", access_token);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		try{
			return (User[]) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public User getUser(long id) throws ServerException{
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
		try{
			return (User) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id) throws ServerException{
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
		try{
			return (Boolean) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public boolean setUser(User user, long id) throws ServerException{
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
		try{
			return (Boolean) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public LoginResult addUser(User user){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "addUser");
		map.put("access_token", access_token);
		map.put("user", user);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return null;
		}
		LoginResult result = (LoginResult) command.send(connection);
		access_token=result.getAccess_token();
		this.user = result.getUser();
		if (clientFrame!=null){
			clientFrame.setUser(user);
			clientFrame.setLogged(true);
		}
		return result;
	}
	public boolean addBratchyk(Bratchyk bratchyk) throws ServerException{
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
		try{
			return (Boolean) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public boolean deleteBratchyk(long id) throws ServerException {
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
		try{
			return (Boolean) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public boolean deleteBratchykHistory(long id, long part) throws ServerException {
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
		try{
			return (Boolean) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public boolean deleteUser(long id) throws ServerException {
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
		try{
			return (Boolean) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public boolean setUserPermission(long id, int permission) throws ServerException{
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
		try{
			return (Boolean) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
	public boolean logOut() throws ServerException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "logOut");
		map.put("access_token", access_token);
		Command command;
		try {
			command = new Command(map);
		} catch (Exception e) {
			return false;
		}
		access_token = "";
		clientFrame.setLogged(false);
		try{
			return (Boolean) command.send(connection);
		}catch(ClassCastException exception){
			throw new ServerException((String)command.send(connection));
		}
	}
}

