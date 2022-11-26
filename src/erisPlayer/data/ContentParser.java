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

import erisPlayer.ErisDateTimer;
import erisPlayer.ErisLogger;

public class ContentParser {
	
	private final String rootName = "eris";
	
	private URI path;
	private ErisLogger logger;
	
	public ContentParser(URI resourceDir, ErisLogger logger) {
		this.path = resourceDir;
		this.logger = logger;
	}
	
	/* --- read Content --- */
	
	public ArrayList<Channel> readContent() {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(new File(path));
			Element root = document.getDocumentElement();
			root.normalize();
			
			checkRootElement(root);
			
	        NodeList contentList = root.getElementsByTagName("channel");
	        return processChannels(contentList);
			
		} catch (ParserConfigurationException e) {
			logger.printError("Can't initialize DocumentBuilder.", e);
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
        	String channelID = channelNode.getAttribute("id");
        	String channelTag = channelNode.getAttribute("tag").toUpperCase();
        	
        	Channel channel = new Channel(channelName, channelID, channelTag);
        	try {
				checkChannel(channel);
			} catch (ContentParserException e) {
				String errorLocation = "While loading " +channelName +"["+ channelTag +" : "+ channelID +"]";
				logger.printError(errorLocation, e);
				continue;
			}
        	
        	ArrayList<Video> videos = processVideos(channelNode.getChildNodes());
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
	
	private ArrayList<Video> processVideos(NodeList videoList) {
		ArrayList<Video> videos = new ArrayList<>();
		
		for(int i = 0; i < videoList.getLength(); i++) {
			if(!videoList.item(i).getNodeName().equals("video")) { continue; }
			
			Element videoNode = (Element) videoList.item(i);
			
			String videoName 		= videoNode.getAttribute("name");
			String videoUploadDate 	= videoNode.getAttribute("uploadDate");
			String videoPlayTime	= videoNode.getAttribute("playTime");
			
			Video video = new Video(videoName, ErisDateTimer.toLocalDate(videoUploadDate), Integer.parseInt(videoPlayTime));
			try {
				checkVideo(video);
			} catch (ContentParserException e) {
				String errorLocation = "While loading " +videoName +"["+ videoUploadDate +" : "+ videoPlayTime +"]";
				logger.printError(errorLocation, e);
				continue;
			}
			
			videos.add(video);
		}
		
		return videos;
	}
	
	private void checkVideo(Video video) throws ContentParserException {
		boolean isInvalid = video.getName().isBlank() 
	 			|| video.getUploadDate().toString().isBlank();
		if(isInvalid) {
			throw new ContentParserException("Channel does not match the standert format.");
		}
	}
	
	
	/* --- write Content --- */
	
	public void writeContent(ArrayList<Channel> channelList) {
		
	}
	
}
