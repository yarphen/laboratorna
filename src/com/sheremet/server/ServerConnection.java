package com.sheremet.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnection extends Thread{
	private ServerSocket server;
	@Override
	public void run() {
		try {
			server = new ServerSocket(80);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			try {
				new ServerSocketThread(server.accept()).start();
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}
}
