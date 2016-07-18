import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;

class XMLParser {
	public static void main(String[] args) {
		try {
			File inputFile = new File(args[0]);
			SAXParserFactory saxFact = SAXParserFactory.newInstance();
			SAXParser saxParser = saxFact.newSAXParser();
			UserHandler userhandler = new UserHandler();
			saxParser.parse(inputFile, userhandler);
		} catch (Exception ex) {
			System.out.println("EXCEPTION: " + ex.getMessage());
			System.exit(-1);
		}
	}
}

class UserHandler extends DefaultHandler {
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("speaker")) {
			System.out.println(attributes.getValue(0));
		}	
	}
}