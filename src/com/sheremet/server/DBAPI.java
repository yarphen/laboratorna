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
import java.util.Random;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.LoginResult;
import com.sheremet.utils.User;

public class DBAPI {
	private Connection con;
	private static final String FILENAME = "db1.db";
	public static final int BRATCHYKSACTIVEBYPATRON = 0;
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
			statement.execute("create table if not exists 'bratchyky' ('actual' INTEGER, 'dataankety' DATETIME,'datanarodzhennia' DATETIME,'dataopatronennia' DATETIME,'dataposhanuvannia' DATETIME,'dataversii' DATETIME,'datavysviaty' DATETIME,'id' INTEGER,'imya' TEXT,'kontakty' TEXT,'patron_id' INTEGER,'pobatkovi' TEXT,'posady' TEXT,'prizvysche' TEXT,'rikvstupu' INTEGER,'rikvypusku' INTEGER,	'specialnist' TEXT,	'version_id' INTEGER PRIMARY KEY)");
			statement.execute("create table if not exists 'tokens' ('user_id' INTEGER, 'token' TEXT)");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public Bratchyk[] getChildrenList(long id){
		LinkedList<Bratchyk> list = new LinkedList<>();
		ResultSet set = getBratchykSet(id, BRATCHYKSACTIVEBYPATRON);
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
		ResultSet set = getBratchykSet(null, BRATCHYKSACTIVEBYPATRON);
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
		ResultSet set = getBratchykSet(id, BRATCHYKSACTIVEBYID);
		try {
			if(set.next()){
				bratchyk= currentBratchyk(set);
			}
		} catch (SQLException e) {}
		return bratchyk;
	}
	public boolean setBratchyk(Bratchyk bratchyk, long id){
		disableVersion(id, false);
		bratchyk.id = id;
		try {
			return addVersion(bratchyk);
		} catch (SQLException e) {
			return false;
		}
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
		try {
			return addVersion(bratchyk);
		} catch (SQLException e) {
			return false; 
		}
	}
	public boolean deleteBratchyk(long id) {
		return disableVersion(id, true);
	}
	public boolean deleteBratchykHistory(long id, Long long1 ) {
		return delVersion(id, long1);
	}

	public LoginResult addUser(User user){
		if(getUser(user.email)!=null) return null;
		Long id = generateID() ;
		try{
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'users' VALUES(?, ?, ?, ?, ?)");
			if (user.email!=null)
				statement.setString(1, user.email);
			else
				statement.setNull(1, java.sql.Types.VARCHAR);
			if (id!=null)
				statement.setLong(2, id);
			else
				statement.setNull(2, java.sql.Types.INTEGER);
			if (user.name!=null)
				statement.setString(3, user.name);
			else
				statement.setNull(3, java.sql.Types.VARCHAR);
			if (user.passhash!=null)
				statement.setString(4, user.passhash);
			else
				statement.setNull(4, java.sql.Types.VARCHAR);
			statement.setInt(5, currentPermission());
			statement.execute();
			statement.close();

		}catch (SQLException e){
			return null;
		}
		String access_token = generateToken(user.passhash);
		addAccessToken(id, access_token);
		return new LoginResult(access_token, getUser(id));
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
			PreparedStatement statement = con.prepareStatement("UPDATE users SET email = ?, name = ?, passhash = ? WHERE id=" + id);
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
			PreparedStatement statement = con.prepareStatement("UPDATE users SET permission=? WHERE id=" + user_id);
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
			PreparedStatement statement = con.prepareStatement("SELECT * FROM tokens WHERE token='"+access_token+"'");
			ResultSet set = statement.executeQuery();
			return getUser(set.getLong("user_id")).permission;
		}catch (Exception e){
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
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'tokens' WHERE token='"+access_token+"'");
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	private int currentPermission() {
		if (getUserList().length==0)
			return Permissions.ADMIN;
		else
			return Permissions.AUTHORIZEDUSER;
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
	private boolean addVersion(Bratchyk version) throws SQLException{
		if (version.patron_id!=null){
			ResultSet resultSet = getBratchykSet(version.patron_id, BRATCHYKSACTIVEBYID);
			if (!resultSet.next()) version.patron_id=null;
		}
		try{
			PreparedStatement statement = con.prepareStatement("INSERT INTO 'bratchyky' VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, 1);
			if (version.dataankety!=null)
				statement.setDate(2, version.dataankety);
			else
				statement.setNull(2, java.sql.Types.DATE);
			if (version.datanarodzhennia!=null)
				statement.setDate(3, version.datanarodzhennia);
			else
				statement.setNull(3, java.sql.Types.DATE);
			if (version.dataopatronennia!=null)
				statement.setDate(4, version.dataopatronennia);
			else
				statement.setNull(4, java.sql.Types.DATE);
			if (version.dataposhanuvannia!=null)
				statement.setDate(5, version.dataposhanuvannia);
			else
				statement.setNull(5, java.sql.Types.DATE);
			statement.setDate(6, new Date(System.currentTimeMillis()));
			if (version.datavysviaty!=null)
				statement.setDate(7, version.datavysviaty);
			else
				statement.setNull(7, java.sql.Types.DATE);
			if (version.id!=null)
				statement.setLong(8, version.id);
			else
				statement.setNull(8, java.sql.Types.INTEGER);
			if (version.imya!=null)
				statement.setString(9, version.imya);
			else
				statement.setNull(9, java.sql.Types.VARCHAR);
			if (version.kontakty!=null)
				statement.setString(10, version.kontakty);
			else
				statement.setNull(10, java.sql.Types.VARCHAR);
			if (version.patron_id!=null)
				statement.setLong(11, version.patron_id);
			else
				statement.setNull(11, java.sql.Types.INTEGER);
			if (version.pobatkovi!=null)
				statement.setString(12, version.pobatkovi);
			else
				statement.setNull(12, java.sql.Types.VARCHAR);
			if (version.posady!=null)
				statement.setString(13, version.posady);
			else
				statement.setNull(13, java.sql.Types.VARCHAR);
			if (version.prizvysche!=null)
				statement.setString(14, version.prizvysche);
			else
				statement.setNull(14, java.sql.Types.VARCHAR);
			if (version.rikvstupu!=null)
				statement.setInt(15, version.rikvstupu);
			else
				statement.setNull(15, java.sql.Types.INTEGER);
			if (version.rikvypusku!=null)
				statement.setInt(16, version.rikvypusku);
			else
				statement.setNull(16, java.sql.Types.INTEGER);
			if (version.specialnist!=null)
				statement.setString(17, version.specialnist);
			else
				statement.setNull(17, java.sql.Types.VARCHAR);
			statement.setInt(18, new Random().nextInt());
			statement.execute();
			statement.close();
		}catch (SQLException e){
			return false;
		}
		return true;
	}
	private boolean delVersion(long id, long version_id){
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM 'bratchyky' WHERE version_id="+version_id+" AND id="+id);
			statement.execute();
			return true;
		}catch (SQLException e){
			return false;
		}
	}
	private boolean disableVersion(long id, boolean b){
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE bratchyky SET actual = 0 WHERE actual=1 AND id=" + id);
			statement.execute();
			ResultSet resultSet = getBratchykSet(id, BRATCHYKSACTIVEBYPATRON);
			if (b){
				while(resultSet.next()){
					Bratchyk bratchyk = currentBratchyk(resultSet);
					bratchyk.patron_id = null;
					addVersion(bratchyk);
				}
			}
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
				if (id==null)
					query = "SELECT * FROM bratchyky WHERE actual=1 AND id IS NULL";
				else
					query = "SELECT * FROM bratchyky WHERE actual=1 AND id = "+id;
				break;
			case BRATCHYKSALLBYID:
				if (id==null)
					query = "SELECT * FROM bratchyky WHERE id IS NULL";
				else
					query = "SELECT * FROM bratchyky WHERE id = "+id;
				break;
			case BRATCHYKSACTIVEBYPATRON:
				if (id==null)
					query = "SELECT * FROM bratchyky WHERE actual=1 AND patron_id IS NULL";
				else
					query = "SELECT * FROM bratchyky WHERE actual=1 AND patron_id = "+id;
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
			// ��� ����� ���������� ������
			// ��������� ��� ���� � ������������ �������� � getInstance(,,,) �� ����������
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
	public static void main(String[] args) {
		System.out.println(generateToken("")); 
	}
}
