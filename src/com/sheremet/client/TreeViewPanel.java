package com.sheremet.client;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sheremet.utils.Bratchyk;



public class TreeViewPanel extends JPanel{
	private SBTree tree;
	private BratchykViewPanel bratchykView;
	private ClientFrame clientFrame;
	private JScrollPane pane;
	private DBSecureAPI api;
	public TreeViewPanel(DBSecureAPI api, ClientFrame clientFrame){
		this.api=api;
		this.clientFrame=clientFrame;
		setName("Name");
		setLayout(new GridLayout(1, 2));
//		setSize(WIDTH, HEIGHT);
		bratchykView = new BratchykViewPanel();
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
	public void load(boolean fullview, Bratchyk b) {
		bratchykView.load(fullview, b);
	}
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
}
