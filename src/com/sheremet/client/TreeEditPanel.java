package com.sheremet.client;

import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;

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
	public TreeEditPanel(DBSecureAPI api, ClientFrame clientFrame) {
		this.api=api;
		this.clientFrame=clientFrame;
		setName("Name");
		setLayout(new GridLayout(1, 2));
//		setSize(WIDTH, HEIGHT);
		bratchykView = new BratchykEditPanel(api, clientFrame);
		bratchykView.setSize(WIDTH/2, HEIGHT);
		add(bratchykView);
		reload();
	}
	public void reload() {
		if (pane!=null)
			remove(pane);
		tree = new SBTree(api, clientFrame, bratchykView);
		pane = new JScrollPane(tree);
		pane.setSize(WIDTH/2, HEIGHT);
		add(pane);
	}
}
