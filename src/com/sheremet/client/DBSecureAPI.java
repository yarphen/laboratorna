package com.sheremet.client;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.User;

public class DBSecureAPI {
	public DBSecureAPI(ClientConnection connection, String access_token) {
		//link all requests to this connection
		//set access token
	}
	public Bratchyk getBratchyk(long id, String access_token){
		return null;
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id, String access_token){
		return false;
	}
	public Bratchyk[] getBratchykHistory(Bratchyk bratchyk, long id, String access_token){
		return null;
	}
	public User[] getUserList(String access_token){
		return null;
	}
	public User getUser(long id, String access_token){
		return null;
	}
	public boolean setUser(User user, long id, String access_token){
		return false;
	}
	public boolean addUser(User user, String access_token){
		return false;
	}
	public boolean addBratchyk(Bratchyk bratchyk, String access_token){
		return false;
	}
	public boolean deleteBratchyk(long id, String access_token) {
		return false;
	}
	public boolean deleteBratchykHistory(long id, int part , String access_token) {
		return false;
	}
	public boolean deleteUser(long id, String access_token) {
		return false;
	}
}
