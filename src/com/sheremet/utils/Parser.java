package com.sheremet.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class Parser {
	public static final int INT=0;
	public static final int STRING=1;
	public static final int DATE=2;
	public static final int ERROR=-1;
	public int typeOf(String s){
		return 0;
		
	}
	public Object parse(String s) {
		
		return s;
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		System.out.println(URLDecoder.decode("%30"));
	}
}
