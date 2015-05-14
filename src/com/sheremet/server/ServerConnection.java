package com.sheremet.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnection extends Thread{
	private ServerSocket server;
	DBAPI dbapi=  new DBAPI();
	@Override
	public void run() {
		try {
			server = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			try {
				new ServerSocketThread(server.accept(),dbapi).start();
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}
}
