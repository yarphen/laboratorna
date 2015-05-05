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
	private long generateID() {
		long l = System.currentTimeMillis();
		l = l%1000000000000L;
		l=l*1000;
		l=l+(int)(Math.random()*1000+0.5);
		return l;
	}
	private Bratchyk currentBratchyk(ResultSet set){
		
	}
	private Bratchyk currentUser(ResultSet set){
		
	}
	private Bratchyk setUser(PreparedStatement st, boolean setId){
		
	}
	private void setBratchyk(Bratchyk bratchyk, boolean setId, boolean active) throws SQLException{
		PreparedStatement statement;
		if (setId){
			statement = con.prepareStatement("INSERT INTO 'bratchyk' (dataankety,datanarodzhennia,dataopatronennia,dataposhanuvannia,datavysviaty,imya,kontakty,patron_id,prizvysche,pobatkovi,posady,rikvstupu,rikvypusku,specialnist,version_id, nodetype) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		}else{
			statement = con.prepareStatement("INSERT INTO 'bratchyk' (dataankety,datanarodzhennia,dataopatronennia,dataposhanuvannia,datavysviaty,imya,kontakty,patron_id,prizvysche,pobatkovi,posady,rikvstupu,rikvypusku,specialnist,version_id, nodetype) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		}
		statement.setDate(1, bratchyk.dataankety);
		statement.setDate(2, bratchyk.datanarodzhennia);
		statement.setDate(3, bratchyk.dataopatronennia);
		statement.setDate(4, bratchyk.dataposhanuvannia);
		statement.setDate(5, bratchyk.datavysviaty);
		statement.setString(6, bratchyk.imya);
		statement.setString(7, bratchyk.kontakty);
		statement.setLong(8, bratchyk.patron_id);
		statement.setString(9, bratchyk.prizvysche);
		statement.setString(10, bratchyk.pobatkovi);
		statement.setString(11, bratchyk.posady);
		statement.setInt(12, bratchyk.rikvstupu);
		statement.setInt(13, bratchyk.rikvypusku);
		statement.setString(14, bratchyk.specialnist);
		statement.setLong(15, System.currentTimeMillis());
		if (active){
			statement.setInt(17, 1);
		}else{
			statement.setInt(17, 0);
		}
		if (active){
			statement.setInt(17, 1);
		}else{
			statement.setInt(17, 0);
		}
		statement.execute();
		statement.close();
	}
}
