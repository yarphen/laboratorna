package com.sheremet.utils;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

public class Parser {
	
	public static final int TYPE_IS_INTEGER = 0;
	
	public static String convertFromObject(Object o){
		String temp = o.getClass().getName();
		String res="";
		switch (temp){
		case "java.lang.Integer":
		case "java.lang.Long":
		case "java.lang.Boolean":
			res=temp+" "+o;
			break;
		case "java.lang.String":
			res=temp+" \""+o+"\"";
			break;
		case "java.util.Date":
			res=temp+" "+((java.util.Date)o).getDay()+"/"
			+((java.util.Date)o).getMonth()+"/"+((java.util.Date)o).getYear();
			break;
		case "java.sql.Date":
			res=temp+" "+(((java.sql.Date)o).getDay()+10)+"/"
			+((java.sql.Date)o).getMonth()+"/"+((java.sql.Date)o).getYear();
		}
		return res;	
	}


	public static String unparseXMLfromCommandHashMap(Commands type, HashMap<String, Object> map) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
        	DocumentBuilder db = dbf.newDocumentBuilder();
        	Document document = db.newDocument();
//        	document.
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	public static Object parseXMLtoResultObject(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	public static String unparseXMLfromResultObject(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	public static HashMap<String, Object> parseXMLtoCommandHashMap(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
