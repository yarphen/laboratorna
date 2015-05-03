package com.sheremet.client;

public class ClientStarter {
	private ClientConnection connection;
	private ClientFrame clientFrame;
	private DBSecureAPI api;
	public static void main(String[] args) {
		ClientStarter starter = new ClientStarter();
		starter.run();
		//run it
	}

	private void run() {
		try{
		connection = new ClientConnection();
		}catch(Exception e){
			
		}
		clientFrame = new ClientFrame(connection, this);
	}
}
