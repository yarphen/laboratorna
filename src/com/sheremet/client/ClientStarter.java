package com.sheremet.client;

import java.net.ConnectException;

import javax.swing.JOptionPane;

public class ClientStarter {
	private ClientConnection connection;
	private ClientFrame clientFrame;
	private DBSecureAPI api;
	public static void main(String[] args) {
		ClientStarter starter = new ClientStarter();
		starter.run();
	}
	private void run() {
		while(true){
			try{
				connection = new ClientConnection();
				break;
			}catch(ConnectException e){
				if (javax.swing.JOptionPane.showConfirmDialog(null, "Cannot connect, bliat! Retry?", "Error!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE)==JOptionPane.YES_OPTION)
					continue;
				else
					return;
			}catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, "Sorry, starting failed!");
				return;
			}
		}
		api = new DBSecureAPI(connection);
		clientFrame = new ClientFrame(api);
		api.setClientFrame(clientFrame);
		clientFrame.setMode(0);
	}
}
