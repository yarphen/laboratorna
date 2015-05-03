package com.sheremet.client;

import javax.swing.JFrame;

public class ClientFrame extends JFrame{
	private ClientStarter clientStarter;
	private ClientConnection connection;
	public ClientFrame(ClientConnection connection, ClientStarter clientStarter) {
		// TODO Auto-generated constructor stub
		this.connection=connection;
		this.clientStarter=clientStarter;
	}
	public void showMessage(String s) {
		// TODO Auto-generated method stub

	}
}
