package com.sheremet.server;

import java.net.Socket;

public class ServerSocketThread extends Thread{
	Socket socket;
	public ServerSocketThread(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
