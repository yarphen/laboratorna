package com.sheremet.client;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sheremet.utils.User;

public class ClientFrame extends JFrame{
	protected static final int SignInMode = 0;
	protected static final int SignUpMode = 1;
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
		try {
			api.logOut();
		} catch (ServerException e) {
			showMessage(e.getMessage()+": "+e.getLocalizedMessage());
		}
		user = null;
	}
	public void showMessage(final String string) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				javax.swing.JOptionPane.showMessageDialog(null, string);
			}
		}).start();
	}
	public void setMode(int mode) {
		switch (mode) {
		case SignInMode:
			acc.setVisible(false);
			perm.setVisible(false);
			signIn.setVisible(true);
			signUp.setVisible(false);
			tEdit.setVisible(false);
			tView.setVisible(false);
			break;
		case SignUpMode:
			acc.setVisible(false);
			perm.setVisible(false);
			signIn.setVisible(false);
			signUp.setVisible(true);
			tEdit.setVisible(false);
			tView.setVisible(false);
			
			break;
		case OwnAccountMode:
			acc.setVisible(true);
			perm.setVisible(false);
			signIn.setVisible(false);
			signUp.setVisible(false);
			tEdit.setVisible(false);
			tView.setVisible(false);
			
			break;
		case PermitionsEditMode:
			acc.setVisible(false);
			perm.setVisible(true);
			signIn.setVisible(false);
			signUp.setVisible(false);
			tEdit.setVisible(false);
			tView.setVisible(false);
			
			break;
		case TreeEditMode:
			acc.setVisible(false);
			perm.setVisible(false);
			signIn.setVisible(false);
			signUp.setVisible(false);
			tEdit.setVisible(true);
			tView.setVisible(false);
			
			break;
		case TreeViewMode:
			acc.setVisible(false);
			perm.setVisible(false);
			signIn.setVisible(false);
			signUp.setVisible(false);
			tEdit.setVisible(false);
			tView.setVisible(true);
			
			break;

		default:
			break;
		}
	}
}
