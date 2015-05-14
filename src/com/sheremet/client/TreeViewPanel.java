package com.sheremet.client;


import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class TreeViewPanel extends JPanel{
	private SBTree tree;
	private BratchykViewPanel bratchykView;
	private Object[] root;
	public TreeViewPanel(DBSecureAPI api){ 
		setName("Name");
		setSize(WIDTH, HEIGHT);
		tree = new SBTree(api);
		add(new JScrollPane(tree));
		bratchykView = new BratchykViewPanel();
	} 
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
}
