package com.sheremet.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import java.util.Arrays;
import java.util.LinkedList;

import com.sheremet.utils.*;

public class DBDirectAPI {
	private static final String FILENAME = "";
	private Connection con;
	public static final int BRATCHYKSALLBYPATRON = 0;
	public static final int BRATCHYKSACTIVEBYID = 1;
	public static final int BRATCHYKSALLBYID = 2;
	public static final int USERSBYLOGIN = 3;
	public static final int USERSBYID = 4;
	public static final int USERSALL = 5;
	public DBDirectAPI() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + FILENAME);
			Statement statement = con.createStatement();
			//			statement.execute("create table if not exists 'teachers' ('name' TEXT, 'department' INTEGER, 'academicTitle' TEXT, 'birth' DATE, 'contacts' TEXT , 'id' INTEGER PRIMARY KEY AUTOINCREMENT)");
			//			statement.execute("create table if not exists 'departments' ('name' TEXT, 'golova' TEXT, 'id' INTEGER PRIMARY KEY AUTOINCREMENT)");
			//			statement.execute("create table if not exists 'specialities' ('name' TEXT, 'typeofprogram' TEXT,  'years' INTEGER, 'id' INTEGER PRIMARY KEY AUTOINCREMENT)");
			//			statement.execute("create table if not exists 'students' ('name' TEXT, 'speciality' INTEGER,  'year' INTEGER, 'birth' DATE, 'contacts' TEXT , 'id' INTEGER PRIMARY KEY AUTOINCREMENT)");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public long generateID() {
		long l = System.currentTimeMillis();
		l = l%1000000000000L;
		l=l*1000;
		l=l+(int)(Math.random()*1000+0.5);
		return l;
	}
	public Bratchyk currentBratchyk(ResultSet set){
		try{
			Bratchyk bratchyk = new Bratchyk();
			bratchyk.dataankety = set.getDate("dataankety");
			bratchyk.datanarodzhennia = set.getDate("datanarodzhennia");
			bratchyk.dataopatronennia = set.getDate("dataopatronennia");
			bratchyk.dataposhanuvannia = set.getDate("dataposhanuvannia");
			bratchyk.dataversii = set.getDate("dataversii");
			bratchyk.datavysviaty = set.getDate("datavysviaty");
			bratchyk.id = set.getLong("id");
			bratchyk.imya = set.getString("imya");
			bratchyk.kontakty = set.getString("kontakty");
			bratchyk.patron_id = set.getLong("patron_id");
			bratchyk.pobatkovi = set.getString("pobatkovi");
			bratchyk.posady = set.getString("posady");
			bratchyk.prizvysche = set.getString("prizvysche");
			bratchyk.rikvstupu = set.getInt("rikvstupu");
			bratchyk.rikvypusku = set.getInt("rikvypusku");
			bratchyk.specialnist = set.getString("specialnist");
			bratchyk.version_id = set.getLong("version_id");
			return bratchyk;
		}catch (SQLException e){
			return null;
		}

	}
	public User currentUser(ResultSet set){
		try{
			User user = new User();
			user.name = set.getString("name");
			user.email = set.getString("email");
			user.passhash = set.getString("passhash");
			user.permission = set.getInt("permission");
			user.id = set.getLong("id");
			return user;
		}catch (SQLException e){
			return null;
		}
	}
	public boolean addVersion(Bratchyk version){
		try{
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'bratchyky' VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setDate(1, version.dataankety);
			statement.setDate(2, version.datanarodzhennia);
			statement.setDate(3, version.dataopatronennia);
			statement.setDate(4, version.dataposhanuvannia);
			statement.setDate(5, version.dataversii);
			statement.setDate(6, version.datavysviaty);
			statement.setLong(7, version.id);
			statement.setString(8, version.imya);
			statement.setString(9, version.kontakty);
			statement.setLong(10, version.patron_id);
			statement.setString(11, version.pobatkovi);
			statement.setString(12, version.posady);
			statement.setString(13, version.prizvysche);
			statement.setInt(14, version.rikvstupu);
			statement.setInt(15, version.rikvypusku);
			statement.setString(16, version.specialnist);
			statement.setLong(17, version.version_id);
			statement.execute();
			statement.close();
		}catch (SQLException e){
			return false;
		}
		return true;
	}
	public boolean delVersion(long id, long version_id){
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'teachers' WHERE version_id="+version_id+" AND id="+id);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean disableVersion(long id){
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE bratshyky SET actual = 0 WHERE actual=1 AND id=" + id);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean addUser(User user){
		try{
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'users' VALUES(?, ?, ?, ?, ?, ?)");
			statement.setString(1, user.email);
			statement.setString(2, user.name);
			statement.setLong(3, user.id);
			statement.setString(4, user.passhash);
			statement.setInt(5, user.permission);
			statement.execute();
			statement.close();
			
		}catch (SQLException e){
			return false;
		}
		return true;
	}
	public boolean delUser(long id){
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'users' WHERE id="+id);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean setUser(User user){
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE students SET email = ?, name = ?, passhash = ?, permission=? WHERE id=" + user.id);
			statement.setString(1, user.email);
			statement.setString(2, user.name);
			statement.setString(3, user.passhash);
			statement.setInt(4, user.permission);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public ResultSet getUserSet(Long id, String email, int mode){
		String query;
		try{
			switch (mode) {
			case USERSALL:
				query = "SELECT * FROM users";
				break;
			case USERSBYID:
				query = "SELECT * FROM users WHERE id = "+id;
				break;
			case USERSBYLOGIN:
				query = "SELECT * FROM users WHERE email = '"+email+"'";
				break;
			default:
				throw new IllegalArgumentException();
			}
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet set = statement.executeQuery();
			return set;
		}catch (Exception e){
			return null;
		}
	}
	public ResultSet getBratchykSet(Long id, int mode){
		String query;
		try{
			switch (mode) {
			case BRATCHYKSACTIVEBYID:
				query = "SELECT * FROM bratchyky WHERE actual=1 AND id = "+id;
				break;
			case BRATCHYKSALLBYID:
				query = "SELECT * FROM bratchyky WHERE id = "+id;
				break;
			case BRATCHYKSALLBYPATRON:
				query = "SELECT * FROM bratchyky WHERE patron_id = "+id;
				break;
			default:
				throw new IllegalArgumentException();
			}
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet set = statement.executeQuery();
			return set;
		}catch (Exception e){
			return null;
		}
	}
	
}
