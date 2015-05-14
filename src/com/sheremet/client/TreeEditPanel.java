package com.sheremet.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;

import com.sheremet.tests.Maneken;
import com.sheremet.utils.Bratchyk;

public class TreeEditPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1599946953143492304L;
	private SBTree tree;
	private JList recyclebin; 
	private BratchykEditPanel editBratchyk;
	private JList history;
	private ClientFrame clientFrame;
	private DBSecureAPI api;
	private BratchykEditPanel bratchykView;
	private JScrollPane pane; 
	private JButton add;
	private GridBagConstraints bagConstraints;
	private JButton del; 
	public TreeEditPanel(final DBSecureAPI api, final ClientFrame clientFrame) {
		this.api=api;
		this.clientFrame=clientFrame;
		setName("Name");
		setLayout(new GridBagLayout());
//		setSize(WIDTH, HEIGHT);
		bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = 0;
		bratchykView = new BratchykEditPanel(api, clientFrame);
		bratchykView.setSize(WIDTH/2, HEIGHT);
		add(bratchykView, bagConstraints);
		add=new JButton("ADD");
		add.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bratchykView.load(Maneken.getBratchyk(tree.getSelectedId()), null);
			}
		});
		bagConstraints.gridx=2;
		add(add, bagConstraints);
		del=new JButton("DEL");
		del.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					api.deleteBratchyk(tree.getSelectedId());
					reload();
				} catch (ServerException e) {
					clientFrame.showMessage(e.getMessage()+": "+e.getLocalizedMessage());
				}
			}
		});
		bagConstraints.gridx=3;
		add(del, bagConstraints);
		bagConstraints.gridx=1;
		reload();
	}
	public void reload() {
		if (pane!=null)
			remove(pane);
		tree = new SBTree(api, clientFrame, bratchykView);
		pane = new JScrollPane(tree);
		pane.setSize(WIDTH/2, HEIGHT);
		add(pane, bagConstraints);
	}
}
