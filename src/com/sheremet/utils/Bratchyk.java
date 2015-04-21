package com.sheremet.utils;

import java.sql.Date;

public class Bratchyk {
	public Bratchyk(Bratchyk b) {
		dataankety = b.dataankety;
		datanarodzhennia = b.datanarodzhennia;
		dataopatronennia = b.dataopatronennia;
		dataposhanuvannia = b.dataposhanuvannia;
		datavysviaty = b.datavysviaty;
		id = b.id;
		imya = b.imya;
		kontakty = b.kontakty;
		patron_id = b.patron_id;
		pobatkovi = b.pobatkovi;
		prizvysche = b.prizvysche;
		rikvstupu = b.rikvstupu;
		rikvypusku = b.rikvypusku;
		specialnist = b.specialnist;
		version_id = b.version_id;
		
	}
	public Bratchyk() {
		// TODO Auto-generated constructor stub
	}
	public Date dataankety;
	public Date datanarodzhennia;//private
	public Date dataopatronennia;
	public Date dataposhanuvannia;
	public Date datavysviaty;//private
	public long id;
	public String imya;
	public String kontakty;//private
	public Integer patron_id;
	public String prizvysche;
	public String pobatkovi;
	public String posady;
	public Integer rikvstupu;
	public Integer rikvypusku;
	public String specialnist;
	public Integer version_id;//private
	
}
