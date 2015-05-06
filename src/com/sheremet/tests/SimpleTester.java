package com.sheremet.tests;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.sheremet.client.DBSecureAPI;
import com.sheremet.server.DBAPI;
import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Parser;

public class SimpleTester {
	@SuppressWarnings({ "unused", "deprecation" })
	public static void main(String[] args) throws Exception{
//		HashMap<Integer, ServerSocket> map = new HashMap<>();
//		while(true){
//			for(int i=0; i<256*256; i++){
//				try{
//					ServerSocket socket = new ServerSocket(i);
//					map.put(i, socket);
//					System.out.println(i);
//				}catch(Exception e){
//					System.err.println(i);
//				}
//				
//			}
//		}

		/*HashMap<String, Object> map = new HashMap<>();
		map.put("imya", "678");
		map.put("id", 123);
		map.put("rikvstupu", 1999);
		int y=2004;
		int m=6;
		int d=29;
		map.put("dataankety", new Date(y-1900, m-1, d));
		Bratchyk b = new Bratchyk(map);*/
		
		
		
//		System.out.println(Parser.convertFromObject(new java.util.Date(1995, 10, 14)));
//
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("imya", "678");
//		map.put("id", 123);
//		map.put("rikvstupu", 1999);
//		int y=2004;
//		int m=6;
//		int d=29;
//		map.put("dataankety", new Date(y-1900, m-1, d));
//		Bratchyk b = new Bratchyk(map);

		
		
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("imya", "678");
//		map.put("id", 123);
//		map.put("rikvstupu", 1999);
//		int y=2004;
//		int m=6;
//		int d=29;
//		map.put("dataankety", new Date(y-1900, m-1, d));
//		Bratchyk b = new Bratchyk(map);
		
		
//		new DBSecureAPI(null, "asfsafasf fvwsvwsv", "erbserbresbesb ");
 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println(DBAPI.generateToken("a"));
		
		System.out.println(DBAPI.md5("a"));
	}
}
