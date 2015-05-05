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
		boolean first=true;
		for(String s:set)
			if(first){
				res+=s+"="+map.get(s);
				first=false;
		}
			else{
				res+="\\"+s+"="+map.get(s);
		}
		return res;
		}
	
	public static String convertFromObject(Object o){
		String temp = o.getClass().getName();
		String res="";
		switch (temp){
		case "java.lang.Integer":
		case "java.lang.Long":
		case "java.lang.Boolean":
			res=temp+" "+o;
			break;
		case "java.lang.String":
			res=temp+" \""+o+"\"";
			break;
		case "java.util.Date":
			res=temp+" "+((java.util.Date)o).getDay()+"/"
			+((java.util.Date)o).getMonth()+"/"+((java.util.Date)o).getYear();
			break;
		case "java.sql.Date":
			res=temp+" "+(((java.sql.Date)o).getDay()+10)+"/"
			+((java.sql.Date)o).getMonth()+"/"+((java.sql.Date)o).getYear();
		}
		return res;	
	}


	public static String unparseXMLstring(Commands type, HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object parserXMLstring(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
