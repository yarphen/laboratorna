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

public class SignUpPanel extends JPanel {
	private DBSecureAPI api; 
	public static void main(String[] args) throws UnknownHostException, IOException {
		JFrame frame = new JFrame("Sign up for Spydei bratstvo");
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClientConnection connection = new ClientConnection();
		SignInPanel in = new SignInPanel(connection );
		in.placeComponents(frame);
		frame.setVisible(true);
	}
	public SignUpPanel(ClientConnection connection) {
		api = new DBSecureAPI(connection);
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(10, 10, 80, 25);
		add(nameLabel);

		final JTextField nameText = new JTextField(20);
		nameText.setBounds(100, 10, 160, 25);
		add(nameText);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(10, 40, 80, 25);
		add(emailLabel);
		
		final JTextField email = new JTextField(20);
		email.setBounds(10,40,80,25);
		add(email);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		add(passwordText);
		
		JLabel confirmPassword = new JLabel ("Confirm password");
		confirmPassword.setBounds(10,40,80,25);
		add (confirmPassword);
		
		final JPasswordField confirmPasswordText = new JPasswordField(20);
		confirmPasswordText.setBounds(100,40,160,25);
		add(confirmPasswordText);
		
		JButton registerButton = new JButton("register");
		registerButton.setBounds(180, 80, 80, 25);
		add(registerButton);

		ActionListener registerButtonListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				api.register
			}
			
		};
		registerButton.addActionListener(registerButtonListener);
	}
	void placeComponents(JFrame frame) {
		
	}
}
		


