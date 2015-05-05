package com.sheremet.client;


	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.sql.*;
	 
	public class SignInPanel  extends JFrame implements ActionListener 
	{ 
	  JLabel l1, l2, l3;
	  JTextField tf1, tf2;
	  JButton btn1;
	  JPasswordField p1;

	 SignInPanel()
	   {
	      setVisible(true);
	      setSize(700, 700);
	      setLayout(null);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setTitle("Login ");

	      l1 = new JLabel("Enter your account");
	      l1.setForeground(Color.blue);
	      l1.setFont(new Font("Serif", Font.BOLD, 20));

	      l2 = new JLabel("Login");
	      l3 = new JLabel("Passowrd:");
	      tf1 = new JTextField();
	      tf2 = new JTextField();
	      p1 = new JPasswordField();
	     btn1 = new JButton("OK");
	      

	     btn1.addActionListener(this);
	      

	      l1.setBounds(100, 30, 400, 30);
	      l2.setBounds(80, 70, 200, 30);
	      l3.setBounds(80, 110, 200, 30);
	      tf1.setBounds(300, 70, 200, 30);
	      tf2.setBounds(300, 110, 200, 30);
	      p1.setBounds(300, 150, 200, 30);
	     btn1.setBounds(50, 350, 100, 30);
	      

	      add(l1);
	      add(l2);
	      add(tf1);
	      add(l3);
	      add(tf2);
	      add(p1);
	      add(btn1);
	
	  }

	  public void actionPerformed(ActionEvent e) 
	   {
	      if (e.getSource() == btn1)
	       {
	          int x = 0;
	          String s1 = tf1.getText();
	          String s2 = tf2.getText();

	          char[] s3 = p1.getPassword();
	          String s8 = new String(s3);
	          String s9 = new String(s8);

	          
	          if (s8.equals(s9))
	         {
	              try
	             {
	                  Class.forName("oracle.jdbc.driver.OracleDriver");
	                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@mcndesktop07:1521:xe", "sandeep", "welcome");
	                  PreparedStatement ps = con.prepareStatement("insert into reg values(?,?,?,?,?,?)");
	                  ps.setString(1, s1);
	                  ps.setString(2, s2);
	                  ps.setString(3, s8);
	                  
	                  ResultSet rs = ps.executeQuery();
	                  x++;
	                  if (x > 0) 
	                  {
	                      JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
	                  }
	              }
	        catch (Exception ex) 
	              {
	                  System.out.println(ex);
	              }
	          }
	        else
	         {
	              JOptionPane.showMessageDialog(btn1, "Wrong password ");
	          } 
	      } 
	        else
	     {
	          tf1.setText("");
	          tf2.setText("");
	          p1.setText("");
	         
	         
	      }
	  } 
	  public static void main(String args[])
	 {
	      new SignInPanel();
	  }
	}

