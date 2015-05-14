package com.sheremet.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
	private PermitionsEditPanel perm;
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
		constraints.anchor=GridBagConstraints.NORTH;
		acc=new OwnAccountPanel(api);
		add(acc, constraints);
		perm=new PermitionsEditPanel(api, this);
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
		addWindowListener(new WindowListener() {
			@Override public void windowOpened(WindowEvent e) {}
			@Override public void windowIconified(WindowEvent e) {}
			@Override public void windowDeiconified(WindowEvent e) {}
			@Override public void windowDeactivated(WindowEvent e) {}
			@SuppressWarnings("deprecation")
			@Override public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			@Override public void windowClosed(WindowEvent e) {}
			@Override public void windowActivated(WindowEvent e) {}
		});
	}
	public void setUser(User user ) {
		this.user=user;
	}
	public void logOut(){
		try {
			api.logOut();
			refreshMode();
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
			setAllInvisible();
			signIn.setVisible(true);//visible
			break;
		case SignUpMode:
			setAllInvisible();
			signUp.setVisible(true);//visible
			break;
		case OwnAccountMode:
			setAllInvisible();
			acc.setVisible(true);//visible
			break;
		case PermitionsEditMode:
			 perm.load();
			setAllInvisible();
			perm.setVisible(true);//visible
			break;
		case TreeEditMode:
			setAllInvisible();
			tEdit.setVisible(true);//visible

			break;
		case TreeViewMode:
			setAllInvisible();
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
		if (logged)
			menu.setMode(user.permission);
		else
			menu.setMode(0);
		setMode(5);
	}
	private void setAllInvisible() {
		acc.setVisible(false);
		perm.setVisible(false);
		signIn.setVisible(false);
		signUp.setVisible(false);
		tEdit.setVisible(false);
		tView.setVisible(false);
	}
}
