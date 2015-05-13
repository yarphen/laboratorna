package com.sheremet.client;

public class ClientStarter {
	private ClientConnection connection;
	private ClientFrame clientFrame;
	private DBSecureAPI api;
	public static void main(String[] args) {
		ClientStarter starter = new ClientStarter();
		starter.run();
	}
	private void run() {
		try{
			connection = new ClientConnection();
		}catch(Exception e){}
		DBSecureAPI api = new DBSecureAPI(connection);
		clientFrame = new ClientFrame(connection, this, api);
		api.setClientFrame(clientFrame);
		clientFrame.setMode(0);
	}
}
