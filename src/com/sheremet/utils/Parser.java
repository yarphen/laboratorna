package com.sheremet.utils;
import java.io.ByteArrayInputStream;
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
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class Parser {

	public static final int TYPE_IS_INTEGER = 0;
	private static DocumentBuilder builder;
	static{

		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			builder= factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
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
		Element main = elementFromUser(document, );
		return null;
	}

	public static HashMap<String, Object> parseXMLtoCommandHashMap(String string) {

		return null;
	}
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//String xml = "<xml><g>a</g><h><j t='' y='12' /></h></xml>";
		String xml="<command><type>getBratchykChildren</type><args><arg name=\"id\" value=\"\" type=\"long\"/></args></command>	";
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.newDocument();
			User user = new User();
			user.email = "svcuysgdvyagwsv@gmail.com";
			user.id = 213713123213123123L;
			user.name = "my_name";
			user.passhash = "kydsavisadvbgi;usgvs";
			user.permission = 3;
			//			Element main = (Element) document.createElement("command");
			//			main.setAttribute("a", "l");
			//			main.setTextContent("sidhvbsidvbsdivb");
			Element main = elementFromUser(document, user );
			document.appendChild(main);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			DOMSource domSource = new DOMSource(document);
			transformer.transform(domSource, new StreamResult(writer));
			String output = writer.getBuffer().toString();//.replaceAll("\n|\r", "");
			document.getDocumentElement().getNodeName();
			//System.out.println(Parser.loadXMLFromString(xml));





		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private static Element elementFromDate(Document document, Date date){
		Element element = document.createElement("date");
		element.setAttribute("year", ""+date.getYear());
		element.setAttribute("month", ""+date.getMonth());
		element.setAttribute("day", ""+date.getDay());
		return element;

	}
	//	private static Element elementFromBratchyk(Document document, Bratchyk bratchyk){
	//		Element element = document.createElement("bratchyk");
	//		element.setAttribute("year", ""+date.getYear());
	//		element.setAttribute("dataankety")
	//		dataankety : Date
	//		datanarodzhennia : Date
	//		dataopatronennia : Date
	//		dataposhanuvannia : Date
	//		dataversii : Date
	//		datavysviaty : Date
	//		id : Long
	//		imya : String
	//		kontakty : String
	//		patron_id : Long
	//		prizvysche : String
	//		pobatkovi : String
	//		posady : String
	//		rikvstupu : Integer
	//		rikvypusku : Integer
	//		specialnist : String
	//		version_id : Long
	//		return element;
	//		
	//	}
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

}

