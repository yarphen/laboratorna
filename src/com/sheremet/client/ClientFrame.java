package com.sheremet.client;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sheremet.utils.User;

public class ClientFrame extends JFrame{
	protected static final int SignInMode = 0;
	protected static final int SignInUpMode = 1;
	protected static final int OwnAccountMode = 2;
	protected static final int PermitionsEditMode = 3;
	protected static final int TreeEditMode = 4;
	protected static final int TreeViewMode = 5;
	private ClientStarter clientStarter;
	private ClientConnection connection;
	private DBSecureAPI api;
	private User user;
	private JPanel acc;
	private JPanel perm;
	private JPanel signIn;
	private JPanel signUp;
	private JPanel tEdit;
	private JPanel tView;
	private JPanel menu;
	public ClientFrame(ClientConnection connection, ClientStarter clientStarter, DBSecureAPI api2) {

		setVisible(true);
		this.connection=connection;
		this.clientStarter=clientStarter;
		this.api = api2;
		acc=new OwnAccountPanel(api);
				add(acc);
		perm=new PermitionsEditPanel(api);
				add(perm);
		signIn=new SignInPanel(api, this);
				add(signIn);
		signUp=new SignUpPanel(api, this);
				add(signUp);
		tEdit = new TreeEditPanel(api);
				add(tEdit);
		tView = new TreeViewPanel(api);
				add(tView);
		menu=new MenuPanel(this);
				add(menu);
	}
	public void setUser(User user ) {
		this.user=user;
	}
	public void logOut(){
		api.logOut();
		user = null;
	}
	public void setMode(int signinupmode2) {
		// TODO Auto-generated method stub

	}
}
