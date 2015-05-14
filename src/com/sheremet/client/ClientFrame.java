package com.sheremet.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout.Constraints;

import com.sheremet.utils.User;

public class ClientFrame extends JFrame{
	protected static final int SignInMode = 0;
	protected static final int SignUpMode = 1;
	protected static final int OwnAccountMode = 2;
	protected static final int PermitionsEditMode = 3;
	protected static final int TreeEditMode = 4;
	protected static final int TreeViewMode = 5;
	private DBSecureAPI api;
	private User user;
	private JPanel acc;
	private JPanel perm;
	private JPanel signIn;
	private JPanel signUp;
	private JPanel tEdit;
	private JPanel tView;
	private MenuPanel menu;
	private boolean logged;
	public ClientFrame(DBSecureAPI api2) {
		setLayout(new GridBagLayout());
		setVisible(true);
		this.api = api2;
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy=1;
		constraints.anchor=GridBagConstraints.PAGE_START;
		acc=new OwnAccountPanel(api);
		add(acc, constraints);
		perm=new PermitionsEditPanel(api);
		add(perm, constraints);
		signIn=new SignInPanel(api, this);
		add(signIn, constraints);
		signUp=new SignUpPanel(api, this);
		add(signUp, constraints);
		tEdit = new TreeEditPanel(api);
		add(tEdit, constraints);
		tView = new TreeViewPanel(api);
		add(tView, constraints);
		constraints.gridy=0;
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
			signIn.setVisible(true);//visible
			signUp.setVisible(false);
			tEdit.setVisible(false);
			tView.setVisible(false);
			break;
		case SignUpMode:
			acc.setVisible(false);
			perm.setVisible(false);
			signIn.setVisible(false);
			signUp.setVisible(true);//visible
			tEdit.setVisible(false);
			tView.setVisible(false);

			break;
		case OwnAccountMode:
			acc.setVisible(true);//visible
			perm.setVisible(false);
			signIn.setVisible(false);
			signUp.setVisible(false);
			tEdit.setVisible(false);
			tView.setVisible(false);

			break;
		case PermitionsEditMode:
			acc.setVisible(false);
			perm.setVisible(true);//visible
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
			tEdit.setVisible(true);//visible
			tView.setVisible(false);

			break;
		case TreeViewMode:
			acc.setVisible(false);
			perm.setVisible(false);
			signIn.setVisible(false);
			signUp.setVisible(false);
			tEdit.setVisible(false);
			tView.setVisible(true);//visible

			break;

		default:
			break;
		}
	}
	public boolean getLogged() {
		return logged;
	}
	public void setLogged(boolean b) {
		logged=b;
		refreshMode();
	}
	public void refreshMode() {
		if (user!=null)
			menu.setMode(user.permission);
		else
			menu.setMode(0);
		setMode(5);
	}
}
