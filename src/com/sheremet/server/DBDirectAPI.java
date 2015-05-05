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
		return null;
		
	}
	public User currentUser(ResultSet set){
		return null;
		
	}
	public boolean addVersion(Bratchyk version){
		return false;
		
	}
	public boolean delVersion(long id, long version_id){
		return false;
		
	}
	public boolean disableVersion(long id){
		return false;
		
	}
	public boolean addUser(User user){
		return false;
		
	}
	public boolean delUser(long id){
		return false;
		
	}
	public boolean setUser(User user){
		return false;
		
	}
	public ResultSet getUserSet(Long id, String login, int mode){
		return null;
		
	}
	public ResultSet getBratchykSet(Long id, int mode){
		return null;
		
	}
}
