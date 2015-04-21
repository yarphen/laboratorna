package com.sheremet.tests;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.sheremet.utils.Bratchyk;
import com.sheremet.utils.Parser;

public class SimpleTester {
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
		HashMap<String, Object> map = new HashMap<>();
		map.put("imya", "678");
		map.put("kontakty", "456");
		Bratchyk b = new Bratchyk(map);
	}
}
