package com.sheremet.client;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sheremet.utils.Bratchyk;

public class BratchykEditPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2402766140390126840L;
	private JLabel prizvysche;
	private JTextField prizvyschev;
	private JLabel imya;
	private JLabel pobatkovi;
	private JTextField imyav;
	private JTextField pobatkoviv;
	private JLabel da;
	private JTextField dav;
	private JLabel dn;
	private JTextField dnv;
	private JTextField dvysv;
	private JLabel dvys;
	private JTextField dverv;
	private JLabel dver;
	private JTextField dopv;
	private JLabel dop;
	private JTextField dpv;
	private JLabel dp;
	private JLabel kontakty;
	private JTextField kontaktyv;
	private JLabel rikvypusku;
	private JTextField rikvstupuv;
	private JLabel rikvstupu;
	private JTextField posadyv;
	private JLabel posady;
	private JLabel specialnist;
	private JTextField rikvypuskuv;
	private JTextField specialnistv;
	private JTextField patron_idv;
	private JLabel version_id;
	private JTextField version_idv;
	private JLabel patron_id;
	private JTextField idv;
	private JLabel id;
	private JButton save;
	private DBSecureAPI api;
	private Long number;
	private ClientFrame clientFrame;
	public BratchykEditPanel(DBSecureAPI api, ClientFrame clientFrame) {
		this.api=api;
		this.clientFrame=clientFrame;
		save = new JButton("SAVE");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (test()){
					try{
						if (number==null)
							BratchykEditPanel.this.api.addBratchyk(getBratchyk());
						else
							BratchykEditPanel.this.api.setBratchyk(getBratchyk(), number);
						BratchykEditPanel.this.clientFrame.reloadTree();
					}catch(ServerException exception){
						BratchykEditPanel.this.clientFrame.showMessage(exception.getMessage()+": "+exception.getLocalizedMessage());
					}
				}
			}
		});
	}
	protected boolean test() {
		try{
			Bratchyk bratchyk = getBratchyk();
		}catch(Exception exception){
			return false;
		}
		return true;
	}
	protected Bratchyk getBratchyk() {
		Bratchyk bratchyk = new Bratchyk();
		bratchyk.dataankety =parseDate( dav.getText());
		bratchyk.datanarodzhennia = parseDate(dnv.getText());
		bratchyk.dataopatronennia = parseDate(dopv.getText());
		bratchyk.dataposhanuvannia =parseDate( dpv.getText());
		bratchyk.datavysviaty =parseDate( dav.getText());
		bratchyk.imya = imyav.getText();
		bratchyk.kontakty = kontaktyv.getText();
		bratchyk.patron_id =Long.parseLong( patron_idv.getText().trim()); 
		bratchyk.pobatkovi = pobatkoviv.getText(); 
		bratchyk.posady = posadyv.getText(); 
		bratchyk.prizvysche = prizvyschev.getText(); 
		bratchyk.rikvstupu = Integer.parseInt(rikvstupuv.getText()); 
		bratchyk.rikvypusku = Integer.parseInt(rikvypuskuv.getText()); 
		bratchyk.specialnist = specialnistv.getText(); 
		return bratchyk; 
	}
	public void load(Bratchyk b,  Long numb) {
		if (b==null)return;
		number=numb;
		removeAll();
		setLayout(new GridLayout(17, 2));
		setVisible(false);
		prizvysche=new JLabel("Прізвище");
		if (b.prizvysche!=null)
			prizvyschev=new JTextField(""+b.prizvysche);
		else
			prizvyschev=new JTextField("not set!");
		imya=new JLabel("Ім'я");
		if (b.imya!=null)
			imyav=new JTextField(b.imya);
		else
			imyav=new JTextField("not set!");
		pobatkovi=new JLabel("По батькові");
		if (b.pobatkovi!=null)
			pobatkoviv=new JTextField(""+b.pobatkovi);
		else
			pobatkoviv=new JTextField("not set!");
		da=new JLabel("Дата анкети");
		if (b.dataankety!=null)
			dav=new JTextField(""+b.dataankety);
		else
			dav=new JTextField("not set!");
		dn=new JLabel("Дата народження");
		if (b.datanarodzhennia!=null)
			dnv=new JTextField(""+b.datanarodzhennia);
		else
			dnv=new JTextField("not set!");

		dop=new JLabel("Дата опатронення");
		if (b.dataopatronennia!=null)
			dopv=new JTextField(""+b.dataopatronennia);
		else
			dopv=new JTextField("not set!");
		dp=new JLabel("Дата пошановування");
		if (b.dataposhanuvannia!=null)
			dpv=new JTextField(""+b.dataposhanuvannia);
		else
			dpv=new JTextField("not set!");
		dver=new JLabel("Дата версії");
		if (b.dataversii!=null)
			dverv=new JTextField(""+b.dataversii);
		else
			dverv=new JTextField("not set!");
		dvys=new JLabel("Дата висвяти");
		if (b.datavysviaty!=null)
			dvysv=new JTextField(""+b.datavysviaty);
		else
			dvysv=new JTextField("not set!");
		kontakty=new JLabel("Контакти");
		if (b.kontakty!=null)
			kontaktyv=new JTextField(""+b.kontakty);
		else
			kontaktyv=new JTextField("not set!");
		posady=new JLabel("Посади");
		if (b.posady!=null)
			posadyv=new JTextField(""+b.posady);
		else
			posadyv=new JTextField("not set!");
		rikvstupu=new JLabel("Рік вступу");
		if (b.rikvstupu!=null)
			rikvstupuv=new JTextField(""+b.rikvstupu);
		else
			rikvstupuv=new JTextField("not set!");
		rikvypusku=new JLabel("Рік випуску");
		if (b.rikvypusku!=null)
			rikvypuskuv=new JTextField(""+b.rikvypusku);
		else
			rikvypuskuv=new JTextField("not set!");
		specialnist=new JLabel("Спеціальність");
		if (b.specialnist!=null)
			specialnistv=new JTextField(""+b.specialnist);
		else
			specialnistv=new JTextField("not set!");
		id=new JLabel("ID");
		if (b.id!=null)
			idv=new JTextField(""+b.id);
		else
			idv=new JTextField("not set!");
		patron_id=new JLabel("Patron ID");
		if (b.patron_id!=null)
			patron_idv=new JTextField(""+b.patron_id);
		else
			patron_idv=new JTextField("not set!");
		version_id=new JLabel("Version ID");
		if (b.version_id!=null)
			version_idv=new JTextField(""+b.version_id);
		else
			version_idv=new JTextField("not set!");
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
		add(save);
		setVisible(true);
	}
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setVisible(true);
		final BratchykViewPanel bratchykViewPanel = new BratchykViewPanel();
		frame.add(bratchykViewPanel);


	}
	private java.sql.Date parseDate(String s) {
		s=s.trim();
		String [] arr = s.split("-");
		int year = Integer.parseInt(arr[0]);
		int month = Integer.parseInt(arr[1]);
		int day = Integer.parseInt(arr[2]);
		return new Date(year-1900, month-1, day);
	}
}
