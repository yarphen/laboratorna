package com.sheremet.client;

import java.net.ConnectException;
import java.util.HashMap;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Parser;
import com.sheremet.utils.User;

public class DBSecureAPI {
	private String access_token;
	private ClientConnection connection;
	public DBSecureAPI(ClientConnection connection,  String login, String password) {
		this.connection = connection;
		connection.send("act=login\\login="+login+"\\password="+password, new StringResultHandler() {
			
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
		return null;
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id){
		return false;
	}
	public Bratchyk[] getBratchykHistory(Bratchyk bratchyk, long id){
		return null;
	}
	public User[] getUserList(String access_token){
		return null;
	}
	public User getUser(long id, String access_token){
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

