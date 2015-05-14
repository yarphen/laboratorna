package com.sheremet.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.sheremet.server.Permissions;

public class MenuPanel extends JPanel {
	private ClientFrame clientFrame;
	 private HashSet<JComponent> set = new HashSet<JComponent>(); 
	public MenuPanel(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
		setMode(0);
	}
	public void setMode(int mode) {
		removeAllComponents();
		switch (mode) {
		case Permissions.ADMIN:{
			addLogoutButton();
			addPermitionsEditButton();
			addOwnAccountButton();
			addTreeEditButton();
			addTreeViewButton();
		}
		break;
		case Permissions.AUTHORIZEDUSER:{
			addLogoutButton();
			addOwnAccountButton();
			addTreeViewButton();
		}
		break;
		case Permissions.GUEST:{
			addSignInButton();
			addSignUpButton();
			addTreeViewButton();
		}
		break;
		case Permissions.SBUSER:{
			addLogoutButton();
			addOwnAccountButton();
			addTreeViewButton();
		}
		break;
		}
	}
	private void removeAllComponents() {
		for(JComponent c:set)
			remove(c);
		set.clear();
	}
	private void addSignInButton(){
		JButton button = new JButton("SignIn");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientFrame.setMode(ClientFrame.SignInMode);
			}
		});
		add(button);
		set.add(button);
	}
	private void addSignUpButton(){
		JButton button = new JButton("SignUp");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientFrame.setMode(ClientFrame.SignUpMode);
			}
		});
		add(button);
		set.add(button);
	}
	private void addOwnAccountButton(){
		JButton button = new JButton("OwnAccount");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientFrame.setMode(ClientFrame.OwnAccountMode);
			}
		});
		add(button);
		set.add(button);
	}
	private void addPermitionsEditButton(){
		JButton button = new JButton("PermitionsEdit");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientFrame.setMode(ClientFrame.PermitionsEditMode);
			}
		});
		add(button);
		set.add(button);
	}
	private void addTreeEditButton(){
		JButton button = new JButton("TreeEdit");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientFrame.setMode(ClientFrame.TreeEditMode);
			}
		});
		add(button);
		set.add(button);
	}
	private void addTreeViewButton(){
		JButton button = new JButton("TreeView");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientFrame.setMode(ClientFrame.TreeViewMode);
			}
		});
		add(button);
		set.add(button);
	}
	private void addLogoutButton(){
		JButton button = new JButton("LOG OUT");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientFrame.logOut();
			}
		});
		add(button);
		set.add(button);
	}
}
