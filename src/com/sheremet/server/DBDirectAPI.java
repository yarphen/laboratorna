package com.sheremet.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.sheremet.utils.*;

public class DBDirectAPI {
	private static final String FILENAME = "";
	private Connection con;
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
