package com.sheremet.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.User;

public class DBWrappedAPI {
	private Connection con;
	private DBDirectAPI api;
	public Bratchyk[] getChildrenList(long id){
		LinkedList<Bratchyk> list = new LinkedList<>();
		ResultSet set = api.getBratchykSet(id, DBDirectAPI.BRATCHYKSALLBYPATRON);
		try {
			while(set.next()){
				list.add(api.currentBratchyk(set));
			}
		} catch (SQLException e) {}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public Bratchyk[] getHeadBratchykList(){
		LinkedList<Bratchyk> list = new LinkedList<>();
		ResultSet set = api.getBratchykSet(null, DBDirectAPI.BRATCHYKSALLBYPATRON);
		try {
			while(set.next()){
				list.add(api.currentBratchyk(set));
			}
		} catch (SQLException e) {}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public Bratchyk getBratchyk(long id){
		Bratchyk bratchyk = null;
		ResultSet set = api.getBratchykSet(null, DBDirectAPI.BRATCHYKSACTIVEBYID);
		try {
			if(set.next()){
				bratchyk= api.currentBratchyk(set);
			}
		} catch (SQLException e) {}
		return bratchyk;
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id){
		bratchyk.id = id;
		return api.addVersion(bratchyk);
	}
	public Bratchyk[] getBratchykHistory(long id){
		LinkedList<Bratchyk> list = new LinkedList<>();
		ResultSet set = api.getBratchykSet(id, DBDirectAPI.BRATCHYKSALLBYID);
		try {
			while(set.next()){
				list.add(api.currentBratchyk(set));
			}
		} catch (SQLException e) {}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public User[] getUserList(){
		LinkedList<User> list = new LinkedList<>();
		ResultSet set = api.getUserSet(null, null, DBDirectAPI.USERSALL);
		try {
			while(set.next()){
				list.add(api.currentUser(set));
			}
		} catch (SQLException e) {}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, User[].class);
	}
	public User getUser(long id){
		User user = null;
		ResultSet set = api.getUserSet(id, null, DBDirectAPI.USERSBYID);
		try {
			if(set.next()){
				user= api.currentUser(set);
			}
		} catch (SQLException e) {}
		return user;
	}
	public User getUser(String login){
		User user = null;
		ResultSet set = api.getUserSet(null, login, DBDirectAPI.USERSBYLOGIN);
		try {
			if(set.next()){
				user= api.currentUser(set);
			}
		} catch (SQLException e) {}
		return user;
	}
	public boolean setUser(User user, long id){
		user.id = id;
		return api.setUser(user);
	}
	public boolean addUser(User user){
		return api.addUser(user);
	}
	public boolean addBratchyk(Bratchyk bratchyk){
		return api.addVersion(bratchyk);
	}
	public boolean deleteBratchyk(long id) {
		return api.disableVersion(id);
	}
	public boolean deleteBratchykHistory(long id, long part ) {
		return api.delVersion(id, part);
	}
	public boolean deleteUser(long id) {
		return api.delUser(id);
	}
}
