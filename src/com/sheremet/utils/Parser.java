package com.sheremet.utils;
import java.sql.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

public class Parser {
	public static final int TYPE_IS_INTEGER = 0;
	public static HashMap<String, Object> parse(String s) {
		String[] arr=s.split(Pattern.quote("\\"));
		HashMap<String, Object> map=new HashMap();
		String temp[]=new String[2];
		for(String s1:arr){
			temp=s1.split(Pattern.quote("="),2);
			map.put(temp[0], temp[1]);
		}
		return map;
	}
	
	public static String unparse(HashMap<String, Object> map) {
		String res="";
		Set<String> set=map.keySet();
		for(String s:set)
			res+=s+"="+map.get(s)+"\\";
		return res;
	}
	
//	int getType(Object o){
//		String temp = o.getClass().getName();
//		switch (temp) {
//		case "":
//			return 0;
//
//		default:
//			return 0;
//		}
//		
//	}
	
}
