package com.sheremet.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

public class Parser {
	public static final int INT=0;
	public static final int STRING=1;
	public static final int DATE=2;
	public static final int ERROR=-1;
	public int typeOf(String s){
		return 0;
		
	}
	public static HashMap<String, Object> parse(String s) {
		
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		String builder = "12313131a\"f23123123";
		System.out.println(builder .substring(0, builder.indexOf("\"")));
	}
	private Object parseStringToObject(String s) {
		StringBuilder builder = new StringBuilder(s);
		while(builder.length()>0&&builder.charAt(0)==' '){
			builder.deleteCharAt(0);
		}
		if (builder.length()==0)return null;
		if (builder.charAt(0)=='%'){
			String string = builder.substring(0, builder.indexOf("\""));
			
		}
		return builder.toString();
	}
	
}
