package com.sheremet.utils;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Parser {

	public static final int TYPE_IS_INTEGER = 0;
	private static DocumentBuilder builder;
	static{
			try {
				DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
				builder= factory.newDocumentBuilder();
			}catch (ParserConfigurationException e) {
				e.printStackTrace();
		}
	}
	
	/*public static String convertFromObject(Object o){
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
	}*/

	public static HashMap<String, Object> parseXMLtoCommandHashMap(String string) {

		return null;
	}
	
	public static String unparseXMLfromCommandHashMap(Commands type, HashMap<String, Object> map) {
		try {
			Document document = builder.newDocument();
			Element command2 = (Element) document.createElement("command");
			Element type2 = (Element) document.createElement("type");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object parseXMLtoResultObject(String s) throws Exception{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder= factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(s));
			Document doc = builder.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String unparseXMLfromResultObject(Object o) throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();
		Element main = elementFromUser(document, null);
		return null;
	}

	private static User parseUser(Element e) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (e.getTagName().equals("user")){
			NodeList list = e.getChildNodes();
			for (int i=0; i<list.getLength(); i++){
				Element element = (Element) list.item(i);
				if (element.getTagName()!="param") continue;
				String elementName = element.getAttribute("name");
				switch (elementName) {
				case "email":
					map.put("email", parseString((Element) element.getFirstChild()));
					break;
				case "id":
					map.put("id", parseLong((Element) element.getFirstChild()));
					break;
				case "name":
					map.put("name", parseString((Element) element.getFirstChild()));
					break;
				case "passhash":
					map.put("passhash", parseString((Element) element.getFirstChild()));
					break;
				case "permission":
					map.put("permission", parseInt((Element) element.getFirstChild()));
					break;
				default:
					break;
				}
			}
		}else{
			return null;
		}
		return new User(map);
	}

	private static Bratchyk parseBratchyk(Element e) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (e.getTagName().equals("bratchyk")){
			NodeList list = e.getChildNodes();
			for (int i=0; i<list.getLength(); i++){
				Element element = (Element) list.item(i);
				if (element.getTagName()!="param") continue;
				String elementName = element.getAttribute("name");
				switch (elementName) {
				case "dataankety":
					map.put("dataankety", parseDate((Element) element.getFirstChild()));
					break;
				case "datanarodzhennia":
					map.put("datanarodzhennia", parseDate((Element) element.getFirstChild()));
					break;
				case "dataopatronennia":
					map.put("dataopatronennia", parseDate((Element) element.getFirstChild()));
					break;
				case "dataposhanuvannia":
					map.put("dataposhanuvannia", parseDate((Element) element.getFirstChild()));
					break;
				case "datavysviaty":
					map.put("datavysviaty", parseDate((Element) element.getFirstChild()));
					break;
				case "id":
					map.put("id", parseLong((Element) element.getFirstChild()));
					break;
				case "imya":
					map.put("imya", parseString((Element) element.getFirstChild()));
					break;
				case "kontakty":
					map.put("kontakty", parseString((Element) element.getFirstChild()));
					break;
				case "patron_id":
					map.put("patron_id", parseLong((Element) element.getFirstChild()));
					break;
				case "prizvysche":
					map.put("prizvysche", parseString((Element) element.getFirstChild()));
					break;
				case "pobatkovi":
					map.put("pobatkovi", parseString((Element) element.getFirstChild()));
					break;
				case "rikvstupu":
					map.put("rikvstupu", parseInt((Element) element.getFirstChild()));
					break;
				case "rikvypusku":
					map.put("rikvypusku", parseInt((Element) element.getFirstChild()));
					break;
				case "specialnist":
					map.put("specialnist", parseString((Element) element.getFirstChild()));
					break;
				case "version_id":
					map.put("version_id", parseLong((Element) element.getFirstChild()));
					break;
				default:
					break;
				}
			}
		}else{
			return null;
		}
		return new Bratchyk(map);
			}
	
	private static Element elementFromDate(Document document, Date date){
		Element element = document.createElement("date");
		element.setAttribute("year", ""+date.getYear());
		element.setAttribute("month", ""+date.getMonth());
		element.setAttribute("day", ""+(date.getDay()+1));
		return element;

	}
		
	private static Element elementFromLong(Document document, Long long1){
		Element element = document.createElement("long");
		element.setTextContent(long1+"");
		return element;
	}
	private static Element elementFromString(Document document, String string){
		Element element = document.createElement("string");
		element.setTextContent(string);
		return element;
	}
	private static Element paramElement(Document document, String name){
		Element element = document.createElement("param");
		element.setAttribute("name", name);
		return element;
	}

	private static Element elementFromInt(Document document, Integer int1) {
		Element element = document.createElement("int");
		element.setTextContent(int1+"");
		return element;
	}
	private static Element elementFromUser(Document document, User user){
		Element element = document.createElement("user");
		Element email = paramElement(document, "email");
		email.appendChild(elementFromString(document, user.email));
		Element id = paramElement(document, "id");
		id.appendChild(elementFromLong(document, user.id));
		Element name = paramElement(document, "name");
		name.appendChild(elementFromString(document, user.name));
		Element passhash = paramElement(document, "passhash");
		passhash.appendChild(elementFromString(document, user.passhash));
		Element permission = paramElement(document, "permission");
		permission.appendChild(elementFromInt(document, user.permission));
		element.appendChild(email);
		element.appendChild(id);
		element.appendChild(name);
		element.appendChild(passhash);
		element.appendChild(permission);
		return element;
	}
	
	private static Element elementFromBratchyk(Document document, Bratchyk bratchyk){
		Element element = document.createElement("bratchyk");
		Element dataankety = paramElement(document, "dataankety");
		dataankety.appendChild(elementFromDate(document, bratchyk.dataankety));
		Element datanarodzhennia = paramElement(document, "datanarodzhennia");
		datanarodzhennia.appendChild(elementFromDate(document,bratchyk.datanarodzhennia));
		Element dataopatronennia = paramElement(document, "dataopatronennia");
		dataopatronennia.appendChild(elementFromDate(document, bratchyk.dataopatronennia));
		Element dataposhanuvannia = paramElement(document, "dataposhanuvannia");
		dataposhanuvannia.appendChild(elementFromDate(document,bratchyk.dataposhanuvannia));
		Element dataversii = paramElement(document, "dataversii");
		dataversii.appendChild(elementFromDate(document, bratchyk.dataversii));
		Element datavysviaty = paramElement(document, "datavysviaty");
		datavysviaty.appendChild(elementFromDate(document, bratchyk.datavysviaty));
		Element id = paramElement(document, "id");
		id.appendChild(elementFromLong(document, bratchyk.id));
		Element imya = paramElement(document, "imya");
		imya.appendChild(elementFromString(document, bratchyk.imya));
		Element kontakty = paramElement(document, "kontakty");
		kontakty.appendChild(elementFromString(document, bratchyk.kontakty));
		Element patron_id = paramElement(document, "patron_id");
		patron_id.appendChild(elementFromLong(document, bratchyk.patron_id));
		Element prizvysche = paramElement(document, "prizvysche");
		prizvysche.appendChild(elementFromString(document, bratchyk.prizvysche));
		Element pobatkovi = paramElement(document, "pobatkovi");
		pobatkovi.appendChild(elementFromString(document, bratchyk.pobatkovi));
		Element posady = paramElement(document, "posady");
		posady.appendChild(elementFromString(document, bratchyk.posady));
		Element rikvstupu = paramElement(document, "rikvstupu");
		rikvstupu.appendChild(elementFromInt(document, bratchyk.rikvstupu));
		Element rikvypusku = paramElement(document, "rikvypusku");
		rikvypusku.appendChild(elementFromInt(document, bratchyk.rikvypusku));
		Element specialnist = paramElement(document, "specialnist");
		specialnist.appendChild(elementFromString(document, bratchyk.specialnist));
		Element version_id = paramElement(document, "version_id");
		version_id.appendChild(elementFromLong(document, bratchyk.version_id));
		element.appendChild(dataankety);
		element.appendChild(datanarodzhennia);
		element.appendChild(dataopatronennia);
		element.appendChild(dataposhanuvannia);
		element.appendChild(dataversii);
		element.appendChild(datavysviaty);
		element.appendChild(id);
		element.appendChild(imya);
		element.appendChild(kontakty);
		element.appendChild(patron_id);
		element.appendChild(prizvysche);
		element.appendChild(pobatkovi);
		element.appendChild(posady);
		element.appendChild(rikvstupu);
		element.appendChild(rikvypusku);
		element.appendChild(specialnist);
		element.appendChild(version_id);
		return element;
	}
	
	private static Integer parseInt(Element e){
		if (e.getTagName().equals("int")){
			try {
				return Integer.parseInt(e.getTextContent());
			}catch(NumberFormatException exception){
				return null;
			}
		}else{
			return null;
		}
	}
	
	private static String parseString(Element e){
		if (e.getTagName().equals("string")){
			return e.getTextContent();
		}else{
			return null;
		}
	}
	private static Long parseLong(Element e){
		if (e.getTagName().equals("long")){
			try {
				return Long.parseLong(e.getTextContent());
			}catch(NumberFormatException exception){
				return null;
			}
		}else{
			return null;
		}
	}
	
	private static Date parseDate(Element e){
		if (e.getTagName().equals("date")){
			try{
				return new Date(Integer.parseInt(e.getAttribute("year")), Integer.parseInt(e.getAttribute("month")), Integer.parseInt(e.getAttribute("day")));
			}catch(NumberFormatException exception){
				return null;
			}
		}else{
			return null;
		}
	}
}

