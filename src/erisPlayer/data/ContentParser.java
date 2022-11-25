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
	
	/* --- read Content --- */
	
	public ArrayList<Channel> readContent() {
		try {
			Document document = documentBuilder.parse(new File(path));
			Element root = document.getDocumentElement();
			root.normalize();
			
			checkRootElement(root);
			
			System.out.println("Root Element :" + root.getNodeName());
	        System.out.println("------");
			
	        NodeList contentList = root.getElementsByTagName("channel");
	        return processChannels(contentList);
			
		} catch (SAXException | IOException e) {
			logger.printError("While reading ChannelData.", e);
		} catch (ContentParserException e) {
			logger.printError("Can't read XML-file or does not match the standert.", e);
		}
		
		return new ArrayList<>();
	}
	
	private void checkRootElement(Element root) throws ContentParserException {
		if(!root.getNodeName().equals(rootName)) {
			throw new ContentParserException("Root-Element does not match the standert");
		}
	}
	
	private ArrayList<Channel> processChannels(NodeList contentList) {
		ArrayList<Channel> channelList = new ArrayList<>();
        for(int i = 0; i < contentList.getLength(); i++) {
        	Element channelNode = (Element) contentList.item(i);
        	
        	String channelName = channelNode.getAttribute("name");
        	String channelID = channelNode.getAttribute("id").toUpperCase();
        	String channelTag = channelNode.getAttribute("tag");
        	
        	System.out.println(i +" : "+ channelNode.getNodeName());
        	
        	Channel channel = new Channel(channelName, channelID, channelTag);
        	try {
				checkChannel(channel);
			} catch (ContentParserException e) {
				String errorLocation = "While loading " +channelName +"["+ channelTag +" : "+ channelID +"]";
				logger.printError(errorLocation, e);
				continue;
			}
        	
        	ArrayList<Video> videos = processVideos();
        	channel = new Channel(channelName, channelID, channelTag, videos);
        	channelList.add(channel);
        	
        	String logMessage = "Channel : ["+ channel.getTag() 
        						+"] \""+ channel.getName() 
        						+"\" (ID : "+ channel.getChanalID() 
        						+") loaded with "+ channel.getVideoList().size() +" videos.";
        	logger.printSubline(logMessage);
        }
        
        return channelList;
	}
	
	private void checkChannel(Channel channel) throws ContentParserException {
		 boolean isInvalid = channel.getName().isBlank() 
				 			|| channel.getChanalID().isBlank() 
				 			|| channel.getTag().isBlank();
		if(isInvalid) {
			throw new ContentParserException("Channel does not match the standert format.");
		}
	}
	
	private ArrayList<Video> processVideos() {
		ArrayList<Video> videos = new ArrayList<>();
		
		return videos;
	}
	
	
	/* --- write Content --- */
	
	public void writeContent(ArrayList<Channel> data) {
		
	}
	
}
