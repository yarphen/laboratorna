package com.sheremet.client;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientFrame extends JFrame{
	private ClientStarter clientStarter;
	private ClientConnection connection;
	public ClientFrame(ClientConnection connection, ClientStarter clientStarter) {
		// TODO Auto-generated constructor stub
		this.connection=connection;
		this.clientStarter=clientStarter;
		private DBSecureAPI api;
	}
	public void showMessage(String s) {
		// TODO Auto-generated method stub
		public void create(){
			 ClientFrame main=new ClientFrame(connection, clientStarter);
			  main.setName("panelTabContent");
			  this.add(main);
			JPanel panel = new JPanel();
			  Object Unit;
			panel.getElement().getStyle().setBorderWidth(0,Unit.PX);
			  panel.getElement().getStyle().setOverflow(Overflow.AUTO);
			  panel.getElement().getStyle().setWidth(100,Unit.PCT);
			 panel.getElement().getStyle().setHeight(100,Unit.PCT);
			  main.add(panel);
			}
	}
	private void create() {
		// TODO Auto-generated method stub
		
	}
}
