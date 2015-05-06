package com.sheremet.utils;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.HashMap;
import java.util.Set;

public class Bratchyk {
	public Bratchyk(HashMap<String, Object> map) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		Class<Bratchyk> bratchykClass = Bratchyk.class;
		Set<String> set = map.keySet();
		for (String s:set){
			Field f = bratchykClass.getField(s);
			f.set(this, map.get(s));
		}
	}
	public Bratchyk(Bratchyk b) {
		dataankety = b.dataankety;
		datanarodzhennia = b.datanarodzhennia;
		dataopatronennia = b.dataopatronennia;
		dataposhanuvannia = b.dataposhanuvannia;
		dataversii=b.dataversii;
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
	public Date dataversii;//private
	public Date datavysviaty;//private
	public Long id;
	public String imya;
	public String kontakty;//private
	public Long patron_id;
	public String prizvysche;
	public String pobatkovi;
	public String posady;
	public Integer rikvstupu;
	public Integer rikvypusku;
	public String specialnist;
	public Long version_id;//private
	private void makeCopy(Bratchyk bratchyk) {
		// TODO Auto-generated method stub
		
	}
	
}
