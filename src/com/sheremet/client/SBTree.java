package com.sheremet.client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
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

import com.sheremet.server.Permissions;
import com.sheremet.utils.Bratchyk;

public class SBTree extends JTree{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605639518805258728L;
	private HashSet<String> set = new HashSet<String>();
	private DBSecureAPI api;
	private DefaultTreeModel model;
	private ClientFrame clientFrame;
	private BratchykViewPanel bratchykViewPanel;
	private TreeNode root = new DefaultMutableTreeNode();
	private BratchykEditPanel bratchykEditPanel;
	public SBTree(DBSecureAPI api, ClientFrame clientFrame, BratchykViewPanel bratchykViewPanel) {
		this.api=api;
		this.clientFrame=clientFrame;
		this.bratchykViewPanel=bratchykViewPanel;
		setShowsRootHandles(false);
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
	public SBTree(DBSecureAPI api2, ClientFrame clientFrame2,
			BratchykEditPanel bratchykEdit) {
		this.api=api2;
		this.clientFrame=clientFrame2;
		this.bratchykEditPanel=bratchykEdit;
		setShowsRootHandles(false);
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
	public void init(){
		Enumeration enumeration = root.children();
		while(enumeration.hasMoreElements()){
			model.removeNodeFromParent((MutableTreeNode) enumeration.nextElement());
		}
		set.clear();
		Bratchyk[] heads = null;
		try {
			heads = api.getHeadBratchyks();
		} catch (ServerException e) {
			clientFrame.showMessage(e.getMessage()+": "+e.getLocalizedMessage());
		}

		for (Bratchyk b: heads){
			final DefaultMutableTreeNode node = new DefaultMutableTreeNode(b);
			final long id = new Random().nextLong();
			model.insertNodeInto(node, (MutableTreeNode) root, 0);
		}
		expandPath(new TreePath(root));
	}
	private void load(DefaultMutableTreeNode node1) {
		if (bratchykEditPanel!=null)
			bratchykEditPanel.load((Bratchyk)node1.getUserObject(), ((Bratchyk)node1.getUserObject()).id); 
		if (bratchykViewPanel!=null)
			bratchykViewPanel.load(clientFrame.getUser()!=null&&clientFrame.getUser().permission>=Permissions.SBUSER, (Bratchyk)node1.getUserObject()); 
		if (set.contains(toPath(node1))) return;
		set.add(toPath(node1));
		//		Bratchyk[] children = new Bratchyk[new Random().nextInt(2)];
		//		for (int i=0; i<children.length; i++){
		//			Bratchyk bratchyk = new Bratchyk();
		//			bratchyk.prizvysche = "" + (int)(100*Math.random());
		//			//			bratchyk.imya = "sdvsdv d sd sd sd sd sd ";
		//			//			bratchyk.pobatkovi = "DBSDSDVSDVDSV";
		//			children[i] = bratchyk;
		//		}
		Bratchyk[] children = null;
		try {

			children = api.getBratchykChildren(((Bratchyk)node1.getUserObject()).id);
		} catch (ServerException e) {
			clientFrame.showMessage(e.getMessage()+": "+e.getLocalizedMessage());
		}catch (NullPointerException e) {return;}

		for (Bratchyk b: children){
			final DefaultMutableTreeNode node = new DefaultMutableTreeNode(b);
			final long id = new Random().nextLong();
			model.insertNodeInto(node, (MutableTreeNode) node1, 0);
		}
	}

	public static void main(String[] args) {


		//		JFrame frame = new JFrame();
		//		frame.setSize(300, 300);
		//		frame.setVisible(true);
		//		final SBTree sbTree = new SBTree(null, clientFrame, bratchykViewPanel);
		//
		//		frame.add(sbTree);
		//		sbTree.setSize(100, 200);

	}
	private static String toPath(DefaultMutableTreeNode n){
		if (n!=null){
			if (n.isRoot())return n.getUserObject()+"";
			return  toPath((DefaultMutableTreeNode) n.getParent())+n.getUserObject().toString();}
		else return null;

	}
	public Long getSelectedId() {
		return ((Bratchyk)((DefaultMutableTreeNode) getSelectionPath().getLastPathComponent()).getUserObject()).id;
	}
}
