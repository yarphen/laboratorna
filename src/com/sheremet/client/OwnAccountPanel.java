package com.sheremet.client;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class OwnAccountPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6358870323043639245L;
	private JLabel labelName = new JLabel("labelName"
			+ "");
	private JLabel labelEmail = new JLabel("labelEmail");
	private JLabel labelPermission = new JLabel("labelPermission");
	private JLabel labelChangePassword = new JLabel("labelChangePassword");
	private JPasswordField fieldOld = new JPasswordField("fieldOld");
	private JPasswordField fieldNew = new JPasswordField("fieldNew");
	private JPasswordField fieldConfirm = new JPasswordField("fieldConfirm");
	public OwnAccountPanel(DBSecureAPI api) {
		add(fieldConfirm);
		add(fieldNew);
		add(fieldOld);
		add(labelChangePassword);
		add(labelEmail);
		add(labelName);
		add(labelPermission);
	}
}
