package com.sheremet.client;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.HashSet;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sheremet.utils.User;

public class PermitionsEditPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1538191760540002057L;
	private static final String[] items = new String[]{"user", "sb user", "admin"};
	private DBSecureAPI api;
	private ClientFrame frame;
	private HashSet<JComponent> set = new HashSet<JComponent>();
	public PermitionsEditPanel(DBSecureAPI api, ClientFrame frame) {
		this.api=api;
		this.frame=frame;
	}
	public void load() {
		setVisible(false);
		removeAllComponents();
		try {
			final User[] uarr = api.getUserList();
			int count = uarr.length;
			setLayout(new GridLayout(count+1, 4));
			JLabel label1=new JLabel("Name of user:");
			JLabel label2=new JLabel("Login of user:");
			JLabel label3=new JLabel("");
			JLabel label4=new JLabel("Change permission:");
			JLabel label5=new JLabel("");
			set.add(label1);
			set.add(label2);
			set.add(label3);
			set.add(label4);
			set.add(label5);
			add(label1);
			add(label2);
			add(label3);
			add(label4);
			add(label5);
			
			for (int i=0; i<count; i++){
				final int j=i;
				JLabel name=new JLabel(uarr[i].name);
				add(name);
				JLabel email=new JLabel(uarr[i].email);
				add(email);
				ActionListener actionListener = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							api.deleteUser(uarr[j].id);
							load();
						} catch (ServerException e1) {
							frame.showMessage(e1.getMessage()+": "+e1.getLocalizedMessage());
						}
					}
				};
				JButton button = new JButton("Delete!");
				button.addActionListener(actionListener);
				add(button);
				final JComboBox<String> box = new JComboBox<>(items);
				box.setSelectedIndex(uarr[i].permission-1);
				add(box );
				JButton button2 = new JButton("Set permission!");
				button2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (box.getSelectedIndex()+1!=uarr[j].permission){
							try {
								api.setUserPermission(uarr[j].id, box.getSelectedIndex()+1);
								load();
								setVisible(true);
							} catch (ServerException e1) {
								frame.showMessage(e1.getMessage()+": "+e1.getLocalizedMessage());
							}
						}
					}
				});
				add(button2);
				set.add(box);
				set.add(button);
				set.add(email);
				set.add(name);
				set.add(button2);
			}
		} catch (ServerException e) {
			frame.showMessage(e.getMessage()+": "+e.getLocalizedMessage());
		}
		setVisible(true);
	}
	private void removeAllComponents() {
		for(JComponent component: set)
			remove(component);
		set.clear();
	}
}