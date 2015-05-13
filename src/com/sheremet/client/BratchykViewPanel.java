package com.sheremet.client;

import java.awt.GridLayout;

import javax.swing.*;

import com.sheremet.tests.Maneken;
import com.sheremet.utils.Bratchyk;

public class BratchykViewPanel extends JPanel{

	public BratchykViewPanel(Bratchyk b,boolean fullView){
		if(fullView){
			setLayout(new GridLayout(16, 2));
			JLabel prizvysche=new JLabel("Прізвище");
			JLabel prizvyschev=new JLabel(""+b.prizvysche);
			JLabel imya=new JLabel("Ім'я");
			JLabel imyav=new JLabel(b.imya);
			JLabel pobatkovi=new JLabel("По батькові");
			JLabel pobatkoviv=new JLabel(""+b.pobatkovi);
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
			JLabel kontakty=new JLabel("Контакти");
			JLabel kontaktyv=new JLabel(""+b.kontakty);
			JLabel posady=new JLabel("Посади");
			JLabel posadyv=new JLabel(""+b.posady);
			JLabel rikvstupu=new JLabel("Рік вступу");
			JLabel rikvstupuv=new JLabel(""+b.rikvstupu);
			JLabel rikvypusku=new JLabel("Рік випуску");
			JLabel rikvypuskuv=new JLabel(""+b.rikvypusku);
			JLabel specialnist=new JLabel("Спеціальність");
			JLabel specialnistv=new JLabel(""+b.specialnist);
			JLabel id=new JLabel("ID");
			JLabel idv=new JLabel(""+b.id);
			JLabel patron_id=new JLabel("Patron ID");
			JLabel patron_idv=new JLabel(""+b.patron_id);
			JLabel version_id=new JLabel("Version ID");
			JLabel version_idv=new JLabel(""+b.version_id);
			add(prizvysche);
			add(prizvyschev);
			add(imya);
			add(imyav);
			add(pobatkovi);
			add(pobatkoviv);
			add(da);
			add(dav);
			add(dn);
			add(dnv);
			add(dp);
			add(dpv);
			add(dver);
			add(dverv);
			add(dvys);
			add(dvysv);
			add(kontakty);
			add(kontaktyv);
			add(posady);
			add(posadyv);
			add(rikvstupu);
			add(rikvstupuv);
			add(rikvypusku);
			add(rikvypuskuv);
			add(specialnist);
			add(specialnistv);
			add(id);
			add(idv);
			add(patron_id);
			add(patron_idv);
			add(version_id);
			add(version_idv);
		}else{
			setLayout(new GridLayout(10, 2));
			JLabel prizvysche=new JLabel("Прізвище");
			JLabel prizvyschev=new JLabel(""+b.prizvysche);
			JLabel imya=new JLabel("Ім'я");
			JLabel imyav=new JLabel(b.imya);
			JLabel pobatkovi=new JLabel("По батькові");
			JLabel pobatkoviv=new JLabel(""+b.pobatkovi);
			JLabel da=new JLabel("Дата анкети");
			JLabel dav=new JLabel(""+b.dataankety);
			JLabel dp=new JLabel("Дата пошановування");
			JLabel dpv=new JLabel(""+b.dataposhanuvannia);
			JLabel posady=new JLabel("Посади");
			JLabel posadyv=new JLabel(""+b.posady);
			JLabel rikvstupu=new JLabel("Рік вступу");
			JLabel rikvstupuv=new JLabel(""+b.rikvstupu);
			JLabel rikvypusku=new JLabel("Рік випуску");
			JLabel rikvypuskuv=new JLabel(""+b.rikvypusku);
			JLabel specialnist=new JLabel("Спеціальність");
			JLabel specialnistv=new JLabel(""+b.specialnist);
			JLabel patron_id=new JLabel("Patron ID");
			JLabel patron_idv=new JLabel(""+b.patron_id);
			add(prizvysche);
			add(prizvyschev);
			add(imya);
			add(imyav);
			add(pobatkovi);
			add(pobatkoviv);
			add(da);
			add(dav);
			add(dp);
			add(dpv);
			add(posady);
			add(posadyv);
			add(rikvstupu);
			add(rikvstupuv);
			add(rikvypusku);
			add(rikvypuskuv);
			add(specialnist);
			add(specialnistv);
			add(patron_id);
			add(patron_idv);
		}
	}
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setVisible(true);
		frame.add(new BratchykViewPanel(Maneken.getBratchyk(), false));
	}
}
