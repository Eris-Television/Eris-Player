package ErisPlayer.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;

import javax.xml.XMLConstants;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ErisPlayer.ErisDateTimer;
import ErisPlayer.ErisLogger;

public class ContentParser {

	private File contentXML;
	private ErisLogger logger;
	
	private final String rootName = "eris";
	private Document document;
	private Element root;
	
	public ContentParser(URI contentXML, ErisLogger logger) {
		this.contentXML = new File(contentXML);
		this.logger = logger;
	}
	
	/* --- read Content --- */
	
	public ArrayList<Channel> readContent() {
		if(!contentXML.exists()) { return new ArrayList<>(); }
		
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			document = documentBuilder.parse(contentXML);
			root = document.getDocumentElement();
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
	
	private final String channelTag = "channel";
	private final String videoTag = "video";
	
	public void writeContent(ArrayList<Channel> channelList) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		    document = docBuilder.newDocument();
		    root = document.createElement(rootName);
		    document.appendChild(root);
		    
		    printChannels(channelList);
		    
		    if(contentXML.exists()) { contentXML.delete(); }
		    contentXML.createNewFile();
		    
		    FileOutputStream output = new FileOutputStream(contentXML);
		    writeXml(output);
		    output.close();
		    
		} catch (ParserConfigurationException e) {
			logger.printError("Can't initialize DocumentBuilder.", e);
		} catch (TransformerException | IOException e) {
			logger.printError("Can't print content.", e);
		}
	}
	
	private void printChannels(ArrayList<Channel> channelList) {
		for(Channel channel : channelList) {
			Element channelElement = document.createElement(channelTag);
			root.appendChild(channelElement);
			
			channelElement.setAttribute("name", channel.getName());
			channelElement.setAttribute("id", channel.getChanalID());
			channelElement.setAttribute("tag", channel.getTag());
			
			printVideos(channel.getVideoList(), channelElement);
		}
	}
	
	private void printVideos(ArrayList<Video> videoList, Element channelElement) {
		for(Video video : videoList) {
			Element videoElement = document.createElement(videoTag);
			channelElement.appendChild(videoElement);
			
			videoElement.setAttribute("name", video.getName());
			videoElement.setAttribute("uploadDate", ""+ErisDateTimer.toInt(video.getUploadDate()));
			videoElement.setAttribute("playTime", ""+video.getPlayTime());
		}
	}
	
	private void writeXml(OutputStream output) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		// pretty print
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(output);
		
		transformer.transform(source, result);
	}
	
}
