package com.gubs.testSAXParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author gubs
 *
 */
public class ReadXMLFileUsingSAXParser {

  private static final Logger log = Logger.getLogger(ReadXMLFileUsingSAXParser.class);

	public static void main(String argv[]) {
		 
	    try {
	 
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
	 
		DefaultHandler handler = new DefaultHandler() {
	 
		boolean bfname = false;
		boolean blname = false;
		boolean bnname = false;
		boolean bsalary = false;
	 
		public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {
	 
			log.info("Start Element :" + qName);
	 
			if (qName.equalsIgnoreCase("FIRSTNAME")) {
				bfname = true;
			}
	 
			if (qName.equalsIgnoreCase("LASTNAME")) {
				blname = true;
			}
	 
			if (qName.equalsIgnoreCase("NICKNAME")) {
				bnname = true;
			}
	 
			if (qName.equalsIgnoreCase("SALARY")) {
				bsalary = true;
			}
	 
		}
	 
		public void endElement(String uri, String localName,
			String qName) throws SAXException {
	 
			log.info("End Element :" + qName);
	 
		}
	 
		public void characters(char ch[], int start, int length) throws SAXException {
	 
			if (bfname) {
				log.info("First Name : " + new String(ch, start, length));
				bfname = false;
			}
	 
			if (blname) {
				log.info("Last Name : " + new String(ch, start, length));
				blname = false;
			}
	 
			if (bnname) {
				log.info("Nick Name : " + new String(ch, start, length));
				bnname = false;
			}
	 
			if (bsalary) {
				log.info("Salary : " + new String(ch, start, length));
				bsalary = false;
			}
	 
		}
	 
	     };
	 
	       saxParser.parse("src/main/resources/testSaxParser.xml", handler);
	 
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	 
	   }
}
