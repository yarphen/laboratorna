package com.sheremet.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sheremet.server.DBAPI;
import com.sheremet.utils.LoginResult;
import com.sheremet.utils.User;

public class SignUpPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7522674650270197959L;
	private DBSecureAPI api; 
	public static void main(String[] args) throws UnknownHostException, IOException {
		JFrame frame = new JFrame("Sign up for Spydei bratstvo");
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClientConnection connection = new ClientConnection();
		DBSecureAPI api = new DBSecureAPI(connection);
		SignInPanel in = new SignInPanel(api, new ClientFrame(api) );
//		in.placeComponents(frame);
		frame.add(in);
		frame.setVisible(true);
	}
	public SignUpPanel(DBSecureAPI api2, final ClientFrame clientFrame) {
		setLayout(new GridLayout(5, 2));
		api=api2;
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
				HashMap<String, Object > map = new  HashMap<String, Object>();
				map.put("email", email.getText());
				map.put("name", nameText.getText());
				map.put("passhash", DBAPI.md5(new String(passwordText.getPassword())));
				Object result = null;
				LoginResult loginResult = null;
				try{
					result = api.addUser(new User(map));
				 loginResult = (LoginResult) result;
				}catch(ClassCastException castException){
					clientFrame.showMessage((String) result);
				}
				if (loginResult!=null&&!loginResult.getAccess_token().isEmpty()){
					clientFrame.showMessage("Successful!");
				}else{
					clientFrame.showMessage("Failed!");
				}
				if (clientFrame.getLogged()){
					clientFrame.refreshMode();
				}
			}

		};
		registerButton.addActionListener(registerButtonListener);
	}
}



