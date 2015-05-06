package com.sheremet.server;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.User;

public class DBAPI {
	private Connection con;
	private static final String FILENAME = "";
	public static final int BRATCHYKSALLBYPATRON = 0;
	public static final int BRATCHYKSACTIVEBYID = 1;
	public static final int BRATCHYKSALLBYID = 2;
	public static final int USERSBYLOGIN = 3;
	public static final int USERSBYID = 4;
	public static final int USERSALL = 5;
	public DBAPI() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + FILENAME);
			Statement statement = con.createStatement();
			statement.execute("create table if not exists 'users' ( 'email' TEXT, 'id' INTEGER PRIMARY KEY,  'name' TEXT, 'passhash' TEXT, 'permission' INTEGER)");
			statement.execute("create table if not exists 'bratchyky' ('name' TEXT, 'golova' TEXT, 'id' INTEGER PRIMARY KEY AUTOINCREMENT)");
			statement.execute("create table if not exists 'tokens' ('name' TEXT, 'typeofprogram' TEXT,  'years' INTEGER, 'id' INTEGER PRIMARY KEY AUTOINCREMENT)");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public Bratchyk[] getChildrenList(long id){
		LinkedList<Bratchyk> list = new LinkedList<>();
		ResultSet set = getBratchykSet(id, BRATCHYKSALLBYPATRON);
		try {
			while(set.next()){
				list.add(currentBratchyk(set));
			}
		} catch (SQLException e) {}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public Bratchyk[] getHeadBratchykList(){
		LinkedList<Bratchyk> list = new LinkedList<>();
		ResultSet set = getBratchykSet(null, BRATCHYKSALLBYPATRON);
		try {
			while(set.next()){
				list.add(currentBratchyk(set));
			}
		} catch (SQLException e) {}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public Bratchyk getBratchyk(long id){
		Bratchyk bratchyk = null;
		ResultSet set = getBratchykSet(null, BRATCHYKSACTIVEBYID);
		try {
			if(set.next()){
				bratchyk= currentBratchyk(set);
			}
		} catch (SQLException e) {}
		return bratchyk;
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id){
		bratchyk.id = id;
		return addVersion(bratchyk);
	}
	public Bratchyk[] getBratchykHistory(long id){
		LinkedList<Bratchyk> list = new LinkedList<>();
		ResultSet set = getBratchykSet(id, BRATCHYKSALLBYID);
		try {
			while(set.next()){
				list.add(currentBratchyk(set));
			}
		} catch (SQLException e) {}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
	}
	public User[] getUserList(){
		LinkedList<User> list = new LinkedList<>();
		ResultSet set = getUserSet(null, null, USERSALL);
		try {
			while(set.next()){
				list.add(currentUser(set));
			}
		} catch (SQLException e) {}
		Object[] objects = list.toArray();
		return Arrays.copyOf(objects, objects.length, User[].class);
	}
	public User getUser(long id){
		User user = null;
		ResultSet set = getUserSet(id, null, USERSBYID);
		try {
			if(set.next()){
				user= currentUser(set);
			}
		} catch (SQLException e) {}
		return user;
	}

	public boolean addBratchyk(Bratchyk bratchyk){
		bratchyk.id = generateID();
		return addVersion(bratchyk);
	}
	public boolean deleteBratchyk(long id) {
		return disableVersion(id);
	}
	public boolean deleteBratchykHistory(long id, long part ) {
		return delVersion(id, part);
	}

	public boolean addUser(User user){
		try{
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'users' VALUES(?, ?, ?, ?, ?, ?)");
			statement.setString(1, user.email);
			statement.setLong(2, user.id);
			statement.setString(3, user.name);
			statement.setString(4, user.passhash);
			statement.setInt(5, user.permission);
			statement.execute();
			statement.close();

		}catch (SQLException e){
			return false;
		}
		return true;
	}
	public boolean deleteUser(long id){
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'users' WHERE id="+id);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean setUser(User user, long id){
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE students SET email = ?, name = ?, passhash = ? WHERE id=" + id);
			statement.setString(1, user.email);
			statement.setString(2, user.name);
			statement.setString(3, user.passhash);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	public boolean setUserPermission(long user_id, int permission){
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE students SET permission=? WHERE id=" + user_id);
			statement.setInt(1,permission);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	//is using for login operation, not sending directly
	User getUser(String login){
		User user = null;
		ResultSet set = getUserSet(null, login, USERSBYLOGIN);
		try {
			if(set.next()){
				user= currentUser(set);
			}
		} catch (SQLException e) {}
		return user;
	}
	//is using just in manager, not int commands
	Integer getPermissionOfTheToken(String access_token){
		if (access_token.isEmpty())
			return Permissions.GUEST;
		try{
			PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE token="+access_token);
			ResultSet set = statement.executeQuery();
			return getUser(set.getLong("user_id")).permission;
		}catch (SQLException e){
			return Permissions.GUEST;
		}
	}
	//is using for login operation, not sending directly
	boolean addAccessToken(Long user_id, String access_token){
		try{
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'tokens' VALUES(?, ?)");
			statement.setLong(1, user_id);
			statement.setString(2, access_token);
			statement.execute();
			statement.close();

		}catch (SQLException e){
			return false;
		}
		return true;
	}
	//is using for logout operation, not sending directly
	boolean delAccessToken(String access_token){
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'tokens' WHERE token="+access_token);
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
	private User currentUser(ResultSet set){
		try{
			User user = new User();
			user.email = set.getString("email");
			user.id = set.getLong("id");
			user.name = set.getString("name");
			user.passhash = set.getString("passhash");
			user.permission = set.getInt("permission");
			return user;
		}catch (SQLException e){
			return null;
		}
	}
	private boolean addVersion(Bratchyk version){
		try{
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'bratchyky' VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setDate(1, version.dataankety);
			statement.setDate(2, version.datanarodzhennia);
			statement.setDate(3, version.dataopatronennia);
			statement.setDate(4, version.dataposhanuvannia);
			statement.setDate(5, new Date(System.currentTimeMillis()));
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
			statement.setLong(17, generateID());
			statement.execute();
			statement.close();
		}catch (SQLException e){
			return false;
		}
		return true;
	}
	private boolean delVersion(long id, long version_id){
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'teachers' WHERE version_id="+version_id+" AND id="+id);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	private boolean disableVersion(long id){
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE bratshyky SET actual = 0 WHERE actual=1 AND id=" + id);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}

	private ResultSet getUserSet(Long id, String email, int mode){
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
	private ResultSet getBratchykSet(Long id, int mode){
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
	public static String md5(String st) {
		MessageDigest messageDigest = null;
		byte[] digest = new byte[0];

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(st.getBytes());
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			// тут можно обработать ошибку
			// возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
			e.printStackTrace();
		}

		BigInteger bigInt = new BigInteger(1, digest);
		String md5Hex = bigInt.toString(16);

		while( md5Hex.length() < 32 ){
			md5Hex = "0" + md5Hex;
		}

		return md5Hex;
	}
	public static String generateToken(String material) {
		String token = "";
		for (int i=0; i<3; i++){
			material+=System.currentTimeMillis()+Math.random();
			token+= md5(material);
			material+=token;
		}
		return token;
		
	}
}
