package com.sheremet.tests;

import java.io.IOException;
import java.net.ServerSocket;

public class SimpleTester {
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while(true){
			new SocketThread(socket.accept()).start();
		}
	}
}
