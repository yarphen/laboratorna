package com.sheremet.client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.sheremet.utils.Bratchyk;

public class SBTree extends JTree{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605639518805258728L;
	private HashSet<String> set = new HashSet<String>();
	private DBSecureAPI api;
	private DefaultTreeModel model;
	private TreeNode root = new DefaultMutableTreeNode();
	public SBTree(DBSecureAPI api) {
		this.api=api;
		setModel(new DefaultTreeModel(root));
		model = (DefaultTreeModel) getModel();
		init();
		addMouseListener(new MouseListener() {

			@Override public void mouseReleased(MouseEvent arg0) {}
			@Override public void mousePressed(MouseEvent arg0) {}
			@Override public void mouseExited(MouseEvent arg0) {}
			@Override public void mouseEntered(MouseEvent arg0) {}

			@Override public void mouseClicked(MouseEvent arg0) {
				TreePath path = getPathForLocation(arg0.getX(), arg0.getY());
				if (path != null)	load((DefaultMutableTreeNode)path.getLastPathComponent());
			}
		});
	}
	private void init(){
		Bratchyk[] heads = new Bratchyk[10];
		for (int i=0; i<10; i++){
			Bratchyk bratchyk = new Bratchyk();
			bratchyk.prizvysche = "" + (int)(100*Math.random());
			bratchyk.imya = "sdvsdv d sd sd sd sd sd ";
			bratchyk.pobatkovi = "DBSDSDVSDVDSV";
			heads[i] = bratchyk;
		}
		//		Bratchyk[] heads = api.getHeadBratchyks();

		for (Bratchyk b: heads){
			final DefaultMutableTreeNode node = new DefaultMutableTreeNode(b);
			final long id = new Random().nextLong();
			model.insertNodeInto(node, (MutableTreeNode) root, 0);
		}
	}
	private void load(DefaultMutableTreeNode node1) {
		if (set.contains(toPath(node1))) return;
		set.add(toPath(node1));
		Bratchyk[] children = new Bratchyk[new Random().nextInt(2)];
		for (int i=0; i<children.length; i++){
			Bratchyk bratchyk = new Bratchyk();
			bratchyk.prizvysche = "" + (int)(100*Math.random());
			//			bratchyk.imya = "sdvsdv d sd sd sd sd sd ";
			//			bratchyk.pobatkovi = "DBSDSDVSDVDSV";
			children[i] = bratchyk;
		}
		//				Bratchyk[] children = api.getBratchykChildren(((Bratchyk)node1.getUserObject()).id);

		for (Bratchyk b: children){
			final DefaultMutableTreeNode node = new DefaultMutableTreeNode(b);
			final long id = new Random().nextLong();
			model.insertNodeInto(node, (MutableTreeNode) node1, 0);
		}
	}
	public static void main(String[] args) {


		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setVisible(true);
		final SBTree sbTree = new SBTree(null);

		frame.add(sbTree);
		sbTree.setSize(100, 200);

	}
	private static String toPath(DefaultMutableTreeNode n){
		if (n!=null){
			if (n.isRoot())return n.getUserObject()+"";
			return  toPath((DefaultMutableTreeNode) n.getParent())+n.getUserObject().toString();}
		else return null;

	}
}
