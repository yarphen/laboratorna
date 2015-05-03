package com.sheremet.client;

import java.net.ConnectException;
import java.sql.Date;
import java.util.HashMap;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Parser;
import com.sheremet.utils.User;

public class DBSecureAPI {
	private String access_token;
	private ClientConnection connection;
	public DBSecureAPI(ClientConnection connection,  String login, String password) {
		this.connection = connection;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("act", "login");
		map.put("login", login);
		map.put("password", password);
		String string  = Parser.unparse(map);
		connection.send(string, new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				HashMap<String, Object> map = Parser.parse(s);
				if (map.get("success").equals("true")){
					access_token = (String) map.get("access_token");
				}else{
					throw new ConnectException("Cannot connect");
				}
			}
		});
	}
	public Bratchyk[] getBratchykChildren(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "get");
		map.put("id", id);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk.makeCopy(new Bratchyk(Parser.parse(s)));
			}
		});
		return bratchyk;
	}
	public Bratchyk[] getHeadBratchyks(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "get");
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk.makeCopy(new Bratchyk(Parser.parse(s)));
			}
		});
		return bratchyk;
	}
	public Bratchyk getBratchyk(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "get");
		map.put("id", id);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk.makeCopy(new Bratchyk(Parser.parse(s)));
			}
		});
		return bratchyk;
	}
	public Bratchyk[] getBratchykHistory(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
	public User[] getUserList(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
	public User getUser(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
	public boolean setUser(User user, long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
	public boolean addUser(User user){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
	public boolean addBratchyk(Bratchyk bratchyk){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
	public boolean deleteBratchyk(long id) {
		return false;
	}
	public boolean deleteBratchykHistory(long id, int part) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
	public boolean deleteUser(long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "getversion");
		map.put("id", id);
		map.put("history", version);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				bratchyk = new Bratchyk(Parser.parse(s));
			}
		});
		return bratchyk;
	}
}

