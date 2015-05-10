package com.sheremet.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	private ClientFrame clientFrame;
	public MenuPanel(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
	}
	public void setMode(int mode) {
		// TODO Auto-generated method stub

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
	}
	private void addSignUpButton(){
		JButton button = new JButton("SignUp");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientFrame.setMode(ClientFrame.SignInUpMode);
			}
		});
		add(button);
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
	}
}
