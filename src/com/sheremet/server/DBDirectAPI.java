package com.sheremet.server;

import com.sheremet.utils.*;

public class DBDirectAPI {
	public DBDirectAPI() {
		
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
	public User[] getUserList(){
		return null;
	}
	public User getUser(long id){
		return null;
	}
	public User getUser(String login){
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
	public boolean deleteBratchykHistory(long id, int part ) {
		return false;
	}
	public boolean deleteUser(long id) {
		return false;
	}
}
