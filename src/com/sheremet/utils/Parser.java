package com.sheremet.utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.text.StyledEditorKit.BoldAction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
		HashMap<String, Object> map = new HashMap<String, Object>();

    	Document document = null;
		try {
			document = builder.parse(new ByteArrayInputStream(string.getBytes()));
		} catch (SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Element e = document.getDocumentElement();
		if (e.getTagName().equals("command")){
			NodeList nl=e.getChildNodes();
			HashSet  hs=new HashSet<String>();
			switch (e.getAttribute("type")) {
			case "login":
				hs.add("act");
				hs.add("login");
				hs.add("password");
				break;
			case "getBratchykChildren":
				hs.add("act");
				hs.add("access_token");
				hs.add("id");
				break;
			case "getHeadBratchyks":
				hs.add("act");
				hs.add("access_token");
				break;
			case "getBratchyk":
				hs.add("act");
				hs.add("access_token");
				hs.add("id");
				break;
			case "getBratchykHistory":
				hs.add("act");
				hs.add("access_token");
				hs.add("id");
				break;
			case "getUserList":
				hs.add("act");
				hs.add("access_token");
				break;
			case "getUser":
				hs.add("act");
				hs.add("access_token");
				hs.add("id");
				break;
			case "setBratchyk":
				hs.add("act");
				hs.add("access_token");
				hs.add("bratchyk");
				hs.add("id");
				break;
			case "setUser":
				hs.add("act");
				hs.add("access_token");
				hs.add("user");
				hs.add("id");
				break;
			case "addUser":
				hs.add("act");
				hs.add("access_token");
				hs.add("user");
				break;
			case "addBratchyk":
				hs.add("act");
				hs.add("access_token");
				hs.add("bratchyk");
				break;
			case "deleteBratchyk":
				hs.add("act");
				hs.add("access_token");
				hs.add("id");
				break;
			case "deleteBratchykHistory":
				hs.add("act");
				hs.add("access_token");
				hs.add("id");
				hs.add("part");
				break;
			case "deleteUser":
				hs.add("act");
				hs.add("access_token");
				hs.add("id");
				break;
			case "setUserPermission":
				hs.add("act");
				hs.add("access_token");
				hs.add("id");
				hs.add("permission");
				break;
			case "logout":
				hs.add("act");
				hs.add("access_token");
				break;
			default:
				break;
				
			}
		}
		return null;
	}

	public static String unparseXMLfromCommandHashMap(Commands type, HashMap<String, Object> map) {
		try {
			Document document = builder.newDocument();
			Element command2 = (Element) document.createElement("command");
			command2.setAttribute("type", type.toString());
			command2=elementFromHashMap(document, command2, map);
			return unparseElement(document, command2);
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

			Element element = (Element) doc.getFirstChild();
			return parseObject(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String unparseXMLfromResultObject(Object o) throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();
		Element main = null;
		if (o==null){
			String oType = o.getClass().getName();
			switch (oType) {

			case "[Lcom.sheremet.utils.Bratchyk;":{
				Bratchyk[] bratchyks = (Bratchyk[]) o;
				main = elementFromBratchykArray(document, bratchyks);
			}
			break;
			case "com.sheremet.utils.User":{
				User user = (User) o;
				main = elementFromUser(document, user);
			}
			break;
			case "[Lcom.sheremet.utils.User;":{
				User[] users = (User[]) o;
				main = elementFromUserArray(document, users);
			}
			break;
			case "com.sheremet.utils.Bratchyk":{
				Bratchyk  bratchyk = (Bratchyk) o;
				main = elementFromBratchyk(document, bratchyk);
			}
			break;
			case "java.lang.Boolean":{
				Boolean bool = (Boolean) o;
				main = elementFromBoolean(document, bool);
			}
			break;
			case "java.lang.String":{
				String  string = (String) o;
				main = elementFromString(document, string);
			}
			break;
			case "com.sheremet.utils.LoginResult":{
				LoginResult  loginResult = (LoginResult) o;
				main = elementFromLoginResult(document, loginResult);
			}
			break;
			}
		}else {
			main = elementFromString(document, "Error!");
		}
		try {
			return unparseElement(document, main);
		} catch (TransformerException e) {
			return null;
		}
	}
	private static HashMap<String, Object> mapFromParams(NodeList list,  Set<String> names) {
		HashMap<String, Object> map = new  HashMap<String, Object> ();
		for (int i=0; i<list.getLength(); i++){
			Element element = (Element) list.item(i);
			try {
				String name = element.getAttribute("name");
				if (names.contains(name)){
					map.put(name, parseObject((Element) element.getFirstChild()));
				}
			} catch (Exception e1) {}
		}
		return map;
	}
	private static Element elementFromHashMap(Document document, Element head, HashMap<String, Object> map) {
		Set<String> set = map.keySet();
		for (String s: set){
			head.appendChild(paramElement(document, s));
		}
		return head;
	}
	private static String unparseElement(Document document, Element element) throws TransformerException{
		document.appendChild(element);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		DOMSource domSource = new DOMSource(document);
		transformer.transform(domSource, new StreamResult(writer));
		return  writer.getBuffer().toString();
	}
	private static Object parseObject(Element e){
		String tag=e.getTagName();
		switch(tag){
		case "userarray":
			return parseUserArray(e);
		case "boolean":
			return parseBoolean(e);

		case "loginresult":
			return parseLoginResult(e); 

		case "date":
			return parseDate(e);

		case "long":
			return parseLong(e);

		case "string":
			return parseString(e);

		case "int":
			return parseInt(e);

		case "user":
			return parseUser(e);

		case "bratchyk":
			try {
				return parseBratchyk(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		case "bratchykarray":
			return parseBratchykArray(e);

		}
		return null;
	}
	private static Element elementFromBoolean(Document document, Boolean bool) {
		Element element = document.createElement("boolean");
		element.setTextContent(bool.toString());
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

	private static Element elementFromUserArray(Document document, User[] users){
		Element element = document.createElement("userarray");
		for (User user: users){
			element.appendChild(elementFromUser(document, user));
		}
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
	private static Element elementFromBratchykArray(Document document, Bratchyk[] bratchyks){
		Element element = document.createElement("bratchykarray");
		for (Bratchyk bratchyk2: bratchyks){
			element.appendChild(elementFromBratchyk(document, bratchyk2));
		}
		return element;
	}
	@SuppressWarnings("deprecation")
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
	private static Element elementFromLoginResult(Document document,  LoginResult result){
		Element element = document.createElement("loginresult");
		Element token = elementFromString(document, result.getAccess_token());
		Element user = elementFromUser(document, result.getUser());
		element.appendChild(token);
		element.appendChild(user);
		return element;
	}
	private static Element elementFromInt(Document document, Integer int1) {
		Element element = document.createElement("int");
		element.setTextContent(int1+"");
		return element;
	}
	private static Element paramElement(Document document, String name){
		Element element = document.createElement("param");
		element.setAttribute("name", name);
		return element;
	}
	private static Bratchyk[] parseBratchykArray(Element e){
		LinkedList<Bratchyk> list = new LinkedList<Bratchyk>();
		if (e.getTagName().equals("bratchykarray")){
			NodeList list2 = e.getChildNodes();
			for (int i=0; i<list2.getLength(); i++){
				Element element = (Element) list2.item(i);
				try {
					list.add(parseBratchyk(element));
				} catch (Exception e1) {}
			}
			Object[] objects = list.toArray();
			return Arrays.copyOf(objects, objects.length, Bratchyk[].class);
		}else{
			return null;
		}
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
	private static User[] parseUserArray(Element e){
		LinkedList<User> list = new LinkedList<User>();
		if (e.getTagName().equals("userarray")){
			NodeList list2 = e.getChildNodes();
			for (int i=0; i<list2.getLength(); i++){
				Element element = (Element) list2.item(i);
				try {
					list.add(parseUser(element));
				} catch (Exception e1) {}
			}
			Object[] objects = list.toArray();
			return Arrays.copyOf(objects, objects.length, User[].class);
		}else{
			return null;
		}
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
	private static LoginResult parseLoginResult(Element element){
		if (element.getTagName()=="loginresult"){
			String token = parseString((Element) element.getChildNodes().item(0));
			User user = parseUser((Element) element.getChildNodes().item(1));
			return new LoginResult(token, user);
		}else{
			return null;
		}
	}
	private static Boolean parseBoolean(Element e) {
		if (e.getTagName().equals("boolean")){
			try {
				return Boolean.parseBoolean(e.getTextContent());
			}catch(NumberFormatException exception){
				return null;
			}
		}else{
			return null;
		}
	}
}

