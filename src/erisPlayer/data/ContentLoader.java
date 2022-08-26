package erisPlayer.data;

import java.util.ArrayList;

import erisPlayer.ErisLogger;

public class ContentLoader {
	
	private final String resourcesDir;
	private final ErisLogger logger;
	
	private ArrayList<Channel> channelList;
	
	public ContentLoader(String localDir, ErisLogger logger) {
		this.resourcesDir = localDir + "resources/";
		this.logger = logger;
		this.channelList = new ArrayList<>();
		
		loadContent();
	}
	
	
	private void loadContent() {
		
		logger.print("Loading Channels ...");
		
		
		logger.printSubline(channelList.size() + " Channels found.");
		
		
		logger.print("Loading Videos ...");
		for(Channel currentChannel : channelList) {
			loadVideos(currentChannel);
			logger.printSubline(currentChannel.getName() +" : "+ currentChannel.getVideoList().size() + " Videos found.");
		}
	}
	
	private void loadVideos(Channel channel) {
		
	}
	
	public ArrayList<Channel> getContent() {
		return channelList;
	}
	
	
}
