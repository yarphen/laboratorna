package com.sheremet.tests;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.Random;

import com.sheremet.server.DBAPI;
import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.User;

public class Maneken {

	public static Bratchyk getBratchyk(Long id) {
		Bratchyk bratchyk = new Bratchyk();
		bratchyk.dataankety = getDate();
		bratchyk.datanarodzhennia = getDate();
		bratchyk.dataopatronennia =getDate();
		bratchyk.dataposhanuvannia = getDate();
		bratchyk.dataversii=getDate();
		bratchyk.datavysviaty = getDate();
		bratchyk.id =getLong();
		bratchyk.imya = getString("IM'YA:");
		bratchyk.kontakty = getString("KONTAKTY:");
		bratchyk.patron_id =id;
		bratchyk.pobatkovi = getString("POBATKOVI:");
		bratchyk.posady = getString("POSADY:");
		bratchyk.prizvysche = getString("PRIZVYSHCHE:");
		bratchyk.rikvstupu = getInt();
		bratchyk.rikvypusku = getInt();
		bratchyk.specialnist = getString("DPECIALNIST:");
		bratchyk.version_id = getLong();
		return bratchyk;
	}
	public static User getUser() {
		User user = new User();
		user.email = "email";
		user.id = getLong();
		user.name = getString("NAME:");
		user.passhash = DBAPI.md5("qwerty");
		user.permission = getInt();
		return user;
	}
	public static String getString(String prefix) {
		
		return prefix;
	}
	public static Long getLong() {
		return new Random().nextLong();
	}
	public static Integer getInt() {
		return new Random().nextInt();
	}
	public static Date getDate() {
		return new Date(System.currentTimeMillis());
	}
	public static void main(String[] args) {
		System.out.println(getString("ssdfsdf"));
	}
}
