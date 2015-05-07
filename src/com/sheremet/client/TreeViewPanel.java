package com.sheremet.client;


import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;

import java.sql.*;


public class TreeViewPanel extends JPanel{

private Object[] root;
public TreeViewPanel()
{ 
setName("Name");
setSize(WIDTH, HEIGHT);

Connection con = null;

String result = "";
try
{
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection("jdbc:mysql://IP/DB", "username","passw");
if (!con.isClosed())
{
System.out.println("Successfully connected to DB server... ");
}
}
catch (Exception e)
{
System.err.println("Exception: " + e.getMessage());
}

try
{
Statement statement = con.createStatement();
ResultSet rs = statement.executeQuery("Select * from DB");
while (rs.next())
{
System.out.println(rs.getString("NAME") + " ");
result = result + rs.getString("NAME") + System.getProperty("line.separator");

}
result = "Вивід з бази даних: "+ System.getProperty("line.separator") +result ;
rs.close();
statement.close();

}
catch (SQLException sqle)
{
System.out.println("jdbc error: " + sqle);
}
finally
{
try
{
if (con != null)
con.close();
}

catch (SQLException e)
{
e.printStackTrace();
}
}


DefaultMutableTreeNode root = new DefaultMutableTreeNode("Корінь");

PreparedStatement ps = ClientConnection.prepeareStatement("Select * from DB");


Object data = null;
DefaultMutableTreeNode d = new DefaultMutableTreeNode(data);
root.add(d);
} 

JTree tree = new JTree(root);
Container contentPane = getRootPane();
contentPane.add(new JScrollPane(tree));
}

private static final int WIDTH = 800;
private static final int HEIGHT = 600;
}
