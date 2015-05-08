package com.sheremet.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignInPanel extends JPanel{
	private DBSecureAPI api; 
	public static void main(String[] args) throws UnknownHostException, IOException {
//		JFrame frame = new JFrame("Enter your account");
//		frame.setSize(300, 150);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		ClientConnection connection = new ClientConnection();
//		SignInPanel in = new SignInPanel(connection );
//		in.placeComponents(frame);
//		frame.setVisible(true);
	}
	public SignInPanel(DBSecureAPI api2, ClientFrame clientFrame) {
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		add(userLabel);

		final JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		add(loginButton);

		ActionListener loginButtonListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				api.login(userText.getText(), new String(passwordText.getPassword()));
			}
			
		};
		loginButton.addActionListener(loginButtonListener);
	}
}