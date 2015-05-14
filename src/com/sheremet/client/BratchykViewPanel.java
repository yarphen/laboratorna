package com.sheremet.client;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.sheremet.tests.Maneken;
import com.sheremet.utils.Bratchyk;

public class BratchykViewPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2402766140390126840L;
	private JLabel prizvysche;
	private JLabel prizvyschev;
	private JLabel imya;
	private JLabel pobatkovi;
	private JLabel imyav;
	private JLabel pobatkoviv;
	private JLabel da;
	private JLabel dav;
	private JLabel dn;
	private JLabel dnv;
	private JLabel dvysv;
	private JLabel dvys;
	private JLabel dverv;
	private JLabel dver;
	private JLabel dpv;
	private JLabel dp;
	private JLabel kontakty;
	private JLabel kontaktyv;
	private JLabel rikvypusku;
	private JLabel rikvstupuv;
	private JLabel rikvstupu;
	private JLabel posadyv;
	private JLabel posady;
	private JLabel specialnist;
	private JLabel rikvypuskuv;
	private JLabel specialnistv;
	private JLabel patron_idv;
	private JLabel version_id;
	private JLabel version_idv;
	private JLabel patron_id;
	private JLabel idv;
	private JLabel id;
	private JLabel dopv;
	private JLabel dop;
	public void load(boolean fullview, Bratchyk b) {
		if (b==null)return;
		removeAll();
		setVisible(false);
		if(fullview){
			setLayout(new GridLayout(16, 2));
			prizvysche=new JLabel("Прізвище");
			if (b.prizvysche!=null)
				prizvyschev=new JLabel(""+b.prizvysche);
			else
				prizvyschev=new JLabel("not set!");
			imya=new JLabel("Ім'я");
			if (b.imya!=null)
				imyav=new JLabel(b.imya);
			else
				imyav=new JLabel("not set!");
			pobatkovi=new JLabel("По батькові");
			if (b.pobatkovi!=null)
				pobatkoviv=new JLabel(""+b.pobatkovi);
			else
				pobatkoviv=new JLabel("not set!");
			da=new JLabel("Дата анкети");
			if (b.dataankety!=null)
				dav=new JLabel(""+b.dataankety);
			else
				dav=new JLabel("not set!");
			dn=new JLabel("Дата народження");
			if (b.datanarodzhennia!=null)
				dnv=new JLabel(""+b.datanarodzhennia);
			else
				dnv=new JLabel("not set!");
			dop=new JLabel("Дата опатронення");
			if (b.dataposhanuvannia!=null)
				dopv=new JLabel(""+b.dataopatronennia);
			else
				dopv=new JLabel("not set!");
			dp=new JLabel("Дата пошановування");
			if (b.dataposhanuvannia!=null)
				dpv=new JLabel(""+b.dataposhanuvannia);
			else
				dpv=new JLabel("not set!");
			dver=new JLabel("Дата версії");
			if (b.dataversii!=null)
				dverv=new JLabel(""+b.dataversii);
			else
				dverv=new JLabel("not set!");
			dvys=new JLabel("Дата висвяти");
			if (b.datavysviaty!=null)
				dvysv=new JLabel(""+b.datavysviaty);
			else
				dvysv=new JLabel("not set!");
			kontakty=new JLabel("Контакти");
			if (b.kontakty!=null)
				kontaktyv=new JLabel(""+b.kontakty);
			else
				kontaktyv=new JLabel("not set!");
			posady=new JLabel("Посади");
			if (b.posady!=null)
				posadyv=new JLabel(""+b.posady);
			else
				posadyv=new JLabel("not set!");
			rikvstupu=new JLabel("Рік вступу");
			if (b.rikvstupu!=null)
				rikvstupuv=new JLabel(""+b.rikvstupu);
			else
				rikvstupuv=new JLabel("not set!");
			rikvypusku=new JLabel("Рік випуску");
			if (b.rikvypusku!=null)
				rikvypuskuv=new JLabel(""+b.rikvypusku);
			else
				rikvypuskuv=new JLabel("not set!");
			specialnist=new JLabel("Спеціальність");
			if (b.specialnist!=null)
				specialnistv=new JLabel(""+b.specialnist);
			else
				specialnistv=new JLabel("not set!");
			id=new JLabel("ID");
			if (b.id!=null)
				idv=new JLabel(""+b.id);
			else
				idv=new JLabel("not set!");
			patron_id=new JLabel("Patron ID");
			if (b.patron_id!=null)
				patron_idv=new JLabel(""+b.patron_id);
			else
				patron_idv=new JLabel("not set!");
			version_id=new JLabel("Version ID");
			if (b.version_id!=null)
				version_idv=new JLabel(""+b.version_id);
			else
				version_idv=new JLabel("not set!");
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
			prizvysche=new JLabel("Прізвище");
			if (b.prizvysche!=null)
				prizvyschev=new JLabel(""+b.prizvysche);
			else
				prizvyschev=new JLabel("not set!");
			imya=new JLabel("Ім'я");
			if (b.imya!=null)
				imyav=new JLabel(b.imya);
			else
				imyav=new JLabel("not set!");
			pobatkovi=new JLabel("По батькові");
			if (b.pobatkovi!=null)
				pobatkoviv=new JLabel(""+b.pobatkovi);
			else
				pobatkoviv=new JLabel("not set!");
			da=new JLabel("Дата анкети");
			if (b.dataankety!=null)
				dav=new JLabel(""+b.dataankety);
			else
				dav=new JLabel("not set!");
			dp=new JLabel("Дата пошановування");
			if (b.dataposhanuvannia!=null)
				dpv=new JLabel(""+b.dataposhanuvannia);
			else
				dpv=new JLabel("not set!");
			posady=new JLabel("Посади");
			if (b.posady!=null)
				posadyv=new JLabel(""+b.posady);
			else
				posadyv=new JLabel("not set!");
			rikvstupu=new JLabel("Рік вступу");
			if (b.rikvstupu!=null)
				rikvstupuv=new JLabel(""+b.rikvstupu);
			else
				rikvstupuv=new JLabel("not set!");
			rikvypusku=new JLabel("Рік випуску");
			if (b.rikvypusku!=null)
				rikvypuskuv=new JLabel(""+b.rikvypusku);
			else
				rikvypuskuv=new JLabel("not set!");
			specialnist=new JLabel("Спеціальність");
			if (b.specialnist!=null)
				specialnistv=new JLabel(""+b.specialnist);
			else
				specialnistv=new JLabel("not set!");
			patron_id=new JLabel("Patron ID");
			if (b.patron_id!=null)
				patron_idv=new JLabel(""+b.patron_id);
			else
				patron_idv=new JLabel("not set!");
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
		setVisible(true);
	}
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setVisible(true);
		final BratchykViewPanel bratchykViewPanel = new BratchykViewPanel();
		frame.add(bratchykViewPanel);


	}
}
