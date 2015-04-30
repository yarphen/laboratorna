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
	public Bratchyk[] getChildrenList(long id){
		LinkedList<User> list = new LinkedList<>();
		try{
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM bratchyk WHERE patron_id="+id);
			Bratchyk b = new Bratchyk();
			while (rs.next()){
				b.dataankety = rs.getDate("dataankety");
				b.datanarodzhennia = rs.getDate("datanarodzhennia");
				b.dataopatronennia = rs.getDate("dataopatronennia");
				b.dataposhanuvannia = rs.getDate("dataposhanuvannia");
				b.datavysviaty = rs.getDate("datavysviaty");
				b.id = rs.getLong("id");
				b.imya = rs.getString("imya");
				b.kontakty = rs.getString("kontakty");
				b.patron_id = rs.getInt("patron_id");
				b.pobatkovi = rs.getString("pobatkovi");
				b.posady = rs.getString("posady");
				b.prizvysche = rs.getString("prizvysche");
				b.rikvstupu = rs.getInt("rikvstupu");
				b.rikvypusku = rs.getInt("rikvypusku");
				b.specialnist = rs.getString("specialnist");
				b.version_id = rs.getInt("version_id");	
			}
		}catch (SQLException e){
			return null;
		}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public Bratchyk[] getBratchykList(){
		LinkedList<User> list = new LinkedList<>();
		try{
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM bratchyk WHERE patron_id=NULL AND nodetype=1");
			Bratchyk b = new Bratchyk();
			while (rs.next()){
				b.dataankety = rs.getDate("dataankety");
				b.datanarodzhennia = rs.getDate("datanarodzhennia");
				b.dataopatronennia = rs.getDate("dataopatronennia");
				b.dataposhanuvannia = rs.getDate("dataposhanuvannia");
				b.datavysviaty = rs.getDate("datavysviaty");
				b.id = rs.getLong("id");
				b.imya = rs.getString("imya");
				b.kontakty = rs.getString("kontakty");
				b.patron_id = rs.getInt("patron_id");
				b.pobatkovi = rs.getString("pobatkovi");
				b.posady = rs.getString("posady");
				b.prizvysche = rs.getString("prizvysche");
				b.rikvstupu = rs.getInt("rikvstupu");
				b.rikvypusku = rs.getInt("rikvypusku");
				b.specialnist = rs.getString("specialnist");
				b.version_id = rs.getInt("version_id");	
			}
		}catch (SQLException e){
			return null;
		}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public Bratchyk getBratchyk(long id){
		try{
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM bratchyk WHERE patron_id=NULL AND nodetype=1 AND id="+id);
			Bratchyk b = new Bratchyk();
			if (rs.next()){
				b.dataankety = rs.getDate("dataankety");
				b.datanarodzhennia = rs.getDate("datanarodzhennia");
				b.dataopatronennia = rs.getDate("dataopatronennia");
				b.dataposhanuvannia = rs.getDate("dataposhanuvannia");
				b.datavysviaty = rs.getDate("datavysviaty");
				b.id = rs.getLong("id");
				b.imya = rs.getString("imya");
				b.kontakty = rs.getString("kontakty");
				b.patron_id = rs.getInt("patron_id");
				b.pobatkovi = rs.getString("pobatkovi");
				b.posady = rs.getString("posady");
				b.prizvysche = rs.getString("prizvysche");
				b.rikvstupu = rs.getInt("rikvstupu");
				b.rikvypusku = rs.getInt("rikvypusku");
				b.specialnist = rs.getString("specialnist");
				b.version_id = rs.getInt("version_id");				
				return b;
			}else return null;
		}catch (SQLException e){
			return null;
		}
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id){
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE bratchyk SET nodetype=? WHERE nodetype=1 AND id=" + id);
			statement.setInt(1, 0);
			statement.execute();
			statement = con.prepareStatement("INSERT INTO 'bratchyk' (dataankety,datanarodzhennia,dataopatronennia,dataposhanuvannia,datavysviaty,imya,kontakty,patron_id,prizvysche,pobatkovi,posady,rikvstupu,rikvypusku,specialnist,version_id, nodetype) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setDate(1, bratchyk.dataankety);
			statement.setDate(2, bratchyk.datanarodzhennia);
			statement.setDate(3, bratchyk.dataopatronennia);
			statement.setDate(4, bratchyk.dataposhanuvannia);
			statement.setDate(5, bratchyk.datavysviaty);
			statement.setLong(6, bratchyk.id);
			statement.setString(7, bratchyk.imya);
			statement.setString(8, bratchyk.kontakty);
			statement.setLong(9, bratchyk.patron_id);
			statement.setString(10, bratchyk.prizvysche);
			statement.setString(11, bratchyk.pobatkovi);
			statement.setString(12, bratchyk.posady);
			statement.setInt(13, bratchyk.rikvstupu);
			statement.setInt(14, bratchyk.rikvypusku);
			statement.setString(15, bratchyk.specialnist);
			statement.setLong(16, System.currentTimeMillis());
			statement.setInt(17, 1);
			statement.executeUpdate();
			statement.close();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public Bratchyk[] getBratchykHistory(long id){
		LinkedList<User> list = new LinkedList<>();
		try{
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM bratchyk WHERE nodetype=0 AND id="+id);
			Bratchyk b = new Bratchyk();
			while (rs.next()){
				b.dataankety = rs.getDate("dataankety");
				b.datanarodzhennia = rs.getDate("datanarodzhennia");
				b.dataopatronennia = rs.getDate("dataopatronennia");
				b.dataposhanuvannia = rs.getDate("dataposhanuvannia");
				b.datavysviaty = rs.getDate("datavysviaty");
				b.id = rs.getLong("id");
				b.imya = rs.getString("imya");
				b.kontakty = rs.getString("kontakty");
				b.patron_id = rs.getInt("patron_id");
				b.pobatkovi = rs.getString("pobatkovi");
				b.posady = rs.getString("posady");
				b.prizvysche = rs.getString("prizvysche");
				b.rikvstupu = rs.getInt("rikvstupu");
				b.rikvypusku = rs.getInt("rikvypusku");
				b.specialnist = rs.getString("specialnist");
				b.version_id = rs.getInt("version_id");	
			}
		}catch (SQLException e){
			return null;
		}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public User[] getUserList(){
		LinkedList<User> list = new LinkedList<>();
		try{
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM students");
			User u = new User();
			while (rs.next()){
				u.name = rs.getString("name");
				u.email = rs.getString("email");
				u.passhash = rs.getString("passhash");
				u.permission = rs.getInt("permission");
				u.id = rs.getLong("id");
				list.add(u);
			}
		}catch (SQLException e){
			return null;
		}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, User[].class);
	}
	public User getUser(long id){
		try{
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM students WHERE id="+id);
			User u = new User();
			if (rs.next()){
				u.name = rs.getString("name");
				u.email = rs.getString("email");
				u.passhash = rs.getString("passhash");
				u.permission = rs.getInt("permission");
				u.id = rs.getLong("id");
				return u;
			}else return null;
		}catch (SQLException e){
			return null;
		}
	}
	public User getUser(String login){
		try{
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM students WHERE email="+login);
			User u = new User();
			if (rs.next()){
				u.name = rs.getString("name");
				u.email = rs.getString("email");
				u.passhash = rs.getString("passhash");
				u.permission = rs.getInt("permission");
				u.id = rs.getLong("id");
				return u;
			}else return null;
		}catch (SQLException e){
			return null;
		}
	}
	public boolean setUser(User user, long id){
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE user SET name = ?, email = ?, passhash = ?, permission=? WHERE id=" + id);
			statement.setString(1, user.name);
			statement.setString(2, user.email);
			statement.setString(3, user.passhash);
			statement.setInt(4, user.permission);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean addUser(User user){
		try{
			long id = generateID();
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'user' (name,email,passhash,permission,id) VALUES(?, ?, ?, ?, ?)");
			statement.setString(1, user.name);
			statement.setString(2, user.email);
			statement.setString(3, user.passhash);
			statement.setInt(4, user.permission);
			statement.setLong(5, id);
			statement.execute();
			statement.close();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean addBratchyk(Bratchyk bratchyk){
		try{
			long id = generateID();
			bratchyk.id = generateID();
			
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean deleteBratchyk(long id) {
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE bratchyk SET nodetype=? WHERE nodetype=1 AND id=" + id);
			statement.setInt(1, 0);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean deleteBratchykHistory(long id, long part ) {
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'bratchyk' WHERE id="+id +" AND version_id="+part);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean deleteUser(long id) {
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'user' WHERE id="+id);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
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
	private Bratchyk setBratchyk(PreparedStatement st, boolean setId, boolean active){
		if (setId){
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'bratchyk' (dataankety,datanarodzhennia,dataopatronennia,dataposhanuvannia,datavysviaty,imya,kontakty,patron_id,prizvysche,pobatkovi,posady,rikvstupu,rikvypusku,specialnist,version_id, nodetype) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		}else{
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'bratchyk' (dataankety,datanarodzhennia,dataopatronennia,dataposhanuvannia,datavysviaty,imya,kontakty,patron_id,prizvysche,pobatkovi,posady,rikvstupu,rikvypusku,specialnist,version_id, nodetype) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
