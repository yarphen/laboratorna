package com.sheremet.client;

import javax.swing.*;

import com.sheremet.utils.Bratchyk;

public class BratchykViewPanel extends JPanel{

	public BratchykViewPanel(Bratchyk b,boolean fullView){
		if(fullView){
			JLabel da=new JLabel("Дата анкети");
			JLabel dav=new JLabel(""+b.dataankety);
			JLabel dn=new JLabel("Дата народження");
			JLabel dnv=new JLabel(""+b.datanarodzhennia);
			JLabel dp=new JLabel("Дата пошановування");
			JLabel dpv=new JLabel(""+b.dataposhanuvannia);
			JLabel dver=new JLabel("Дата версії");
			JLabel dverv=new JLabel(""+b.dataversii);
			JLabel dvys=new JLabel("Дата висвяти");
			JLabel dvysv=new JLabel(""+b.datavysviaty);
			JLabel id=new JLabel("ID");
			JLabel idv=new JLabel(""+b.id);
			JLabel imya=new JLabel("Ім'я");
			JLabel imyav=new JLabel(b.imya);
			JLabel l1=new JLabel("Дата анкети");
			JLabel l2=new JLabel(""+b.dataankety);
			JLabel l1=new JLabel("Дата анкети");
			JLabel l2=new JLabel(""+b.dataankety);
			JLabel l1=new JLabel("Дата анкети");
			JLabel l2=new JLabel(""+b.dataankety);
			JLabel l1=new JLabel("Дата анкети");
			JLabel l2=new JLabel(""+b.dataankety);
		}
	}
}
