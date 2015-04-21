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
		map.put("access_token", access_token);
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
	public Bratchyk getBratchyk(long id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", access_token);
		map.put("act", "get");
		map.put("id", id);
		final Bratchyk bratchyk = new Bratchyk();
		connection.send(Parser.unparse(map), new StringResultHandler() {
			
			@Override
			void handle(String s) throws Exception {
				HashMap<String, Object> map = Parser.parse(s);
				bratchyk.dataankety = (Date) map.get("dataankety");
				bratchyk.datanarodzhennia = (Date) map.get("datanarodzhennia");
				bratchyk.dataopatronennia = (Date) map.get("dataopatronennia");
				bratchyk.dataposhanuvannia = (Date) map.get("dataposhanuvannia");
				bratchyk.datavysviaty = (Date) map.get("datavysviaty");
				bratchyk.imya = (String) map.get("imya");
				bratchyk.kontakty = (String) map.get("kontakty");
				bratchyk.patron_id = (Integer) map.get("patron_id");
				bratchyk.pobatkovi = (String) map.get("pobatkovi");
				bratchyk.posady = (String) map.get("posady");
				bratchyk.prizvysche = (String) map.get("prizvysche");
				bratchyk.rikvstupu = (Integer) map.get("rikvstupu");
			}
		});
		return bratchyk;
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id){
		return false;
	}
	public Bratchyk[] getBratchykHistory(Bratchyk bratchyk, long id){
		return null;
	}
	public User[] getUserList(){
		return null;
	}
	public User getUser(long id){
		return null;
	}
	public boolean setUser(User user, long id){
		return false;
	}
	public boolean addUser(User user){
		return false;
	}
	public boolean addBratchyk(Bratchyk bratchyk){
		return false;
	}
	public boolean deleteBratchyk(long id) {
		return false;
	}
	public boolean deleteBratchykHistory(long id, int part) {
		return false;
	}
	public boolean deleteUser(long id) {
		return false;
	}
}

