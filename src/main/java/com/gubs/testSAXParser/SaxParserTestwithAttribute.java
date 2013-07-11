package com.gubs.testSAXParser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParserTestwithAttribute extends DefaultHandler {
	
	private String fname = null;

	  public void parse() {
	    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	      SAXParser parser = saxParserFactory.newSAXParser();
	      parser.parse("src/main/resources/testSaxParser.xml", this);
	    } catch (SAXException se) {
	      se.printStackTrace();
	    } catch (ParserConfigurationException pce) {
	      pce.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    
	  }
	  
	  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	    if (qName.equals("firstname")) {
	    	fname = attributes.getValue("firstname");
	    	System.out.println("Firstname " + fname);
	    }
	    
	  }

	  public static void main(String args[]) {
	    SaxParserTestwithAttribute obj = new SaxParserTestwithAttribute();
	    obj.parse();
	  }
}
