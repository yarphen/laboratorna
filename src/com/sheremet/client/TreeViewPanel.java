package com.sheremet.client;


import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class TreeViewPanel extends JPanel{
	private SBTree tree;
	private JPanel bratchykView;
	private Object[] root;
	public TreeViewPanel(DBSecureAPI api){ 
		
		setName("Name");
		setSize(WIDTH, HEIGHT);
		String result = "";
		SBTree tree = new SBTree(api);
		add(new JScrollPane(tree));
	} 
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
}
