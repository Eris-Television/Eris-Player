package erisPlayer.data;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import erisPlayer.ErisLogger;
import erisPlayer.data.editor.ContentParserException;

public class ContentParser {
	
	private final String rootName = "eris";
	
	private DocumentBuilder documentBuilder;
	
	private URI path;
	private ErisLogger logger;
	
	public ContentParser(URI resourceDir, ErisLogger logger) {
		this.path = resourceDir;
		this.logger = logger;
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
		} catch (ParserConfigurationException e) {
			logger.printError("Can't initialize DocumentBuilder.", e);
		}
	}
	
	public ArrayList<Channel> readContent() {
		
		try {
			Document document = documentBuilder.parse(new File(path));
			Element root = document.getDocumentElement();
			root.normalize();
			
			checkRootElement(root);
			
			System.out.println("Root Element :" + root.getNodeName());
	        System.out.println("------");
			
	        NodeList contentList = root.getChildNodes();
	        
	       
			
		} catch (SAXException | IOException e) {
			logger.printError("While reading ChannelData.", e);
		} catch (ContentParserException e) {
			logger.printError("Can't read XML-file or does not match the standert.", e);
		}
		
		
		
		
		return null;
	}
	
	private void checkRootElement(Element root) throws ContentParserException {
		if(!root.getNodeName().equals(rootName)) {
			throw new ContentParserException("Root-Element does not match the standert");
		}
	}
	
	public void writeContent(ArrayList<Channel> data) {
		
	}
	
}
