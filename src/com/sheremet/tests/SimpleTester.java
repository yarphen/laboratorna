package com.sheremet.tests;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.sheremet.utils.Parser;

public class SimpleTester {
	public static void main(String[] args) throws IOException {
		HashMap<Integer, ServerSocket> map = new HashMap<>();
		while(true){
			for(int i=0; i<256*256; i++){
				try{
					ServerSocket socket = new ServerSocket(i);
					map.put(i, socket);
				}catch(Exception e){}
				System.out.println(i);
			}
		}
	}
}
