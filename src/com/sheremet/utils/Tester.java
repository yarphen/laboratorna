package com.sheremet.utils;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class Tester {

		public static void main(String[] args) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//String xml = "<xml><g>a</g><h><j t='' y='12' /></h></xml>";
			String xml="<command><type>getBratchykChildren</type><args><arg name=\"id\" value=\"\" type=\"long\"/></args></command>	";
	        try {
	        	DocumentBuilder db = dbf.newDocumentBuilder();
	        	Document document = db.parse(new ByteArrayInputStream(xml.getBytes()));
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
//	public static void main(String[] args) {
//		Runnable runnable = new Runnable() {
//			public void run() {
//				LinkedList<Object> list = new LinkedList<Object>();
//				try{
//					long counter =0;
//					while(true){
//						if (counter%10000==0)System.out.println(Thread.currentThread().getId()+": "+ counter);
//						list.add(new String(""+Math.random()));
//						counter++;
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		};
//		while(true){
//			new Thread(runnable).start();
//		}
//	}
}
