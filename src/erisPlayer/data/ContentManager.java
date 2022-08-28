package erisPlayer.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;

import erisPlayer.ErisLogger;

public class ContentManager {
	
	private String resourceDir;
	
	private ErisLogger logger;
	
	private ArrayList<Channel> channelList;
	public ArrayList<Channel> getChannelList() { return channelList; }
	public void setChannelList(ArrayList<Channel> channelList) { this.channelList = channelList; }

	public ContentManager(String resourceDir, ErisLogger logger) {
		this.resourceDir = resourceDir;
		this.logger = logger;
		
		loadContent();
		listContent();
	}
	
	@SuppressWarnings("unchecked")
	public void loadContent() {
		String path = resourceDir + "channels.eris";
		logger.print("ContentManager is loading Content ...");
		
		try(FileInputStream fis = new FileInputStream(Paths.get(new URI(path)).toFile());
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			channelList = (ArrayList<Channel>) ois.readObject();
		} catch (Exception e) {
			logger.printError("Can't read Channels from File!", e);
			channelList = new ArrayList<>();
		}
	}
	
	public void saveContent() {
		String path = resourceDir + "channels.eris";
		logger.print("ContentManager is saveing Content ...");
		
		try(FileOutputStream fos = new FileOutputStream(Paths.get(new URI(path)).toFile());
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(channelList);
		} catch (Exception e) {
			logger.printError("Can't write Channels to File!", e);
		}
	}
	
	public void listChanels() {
		System.out.println();
		logger.print("Content porvides " + channelList.size() + " Channels:");
		for(Channel current : channelList) {
			logger.printSubline("Channel : " + channelList.indexOf(current) +" : "+ current.getName() + " (ID:"+current.getChanalID()
								+") with "+current.getVideoList().size() + " Videos.");
		}
	}
	
	public void listVideos(int channelNumber) {
		System.out.println();
		logger.print("Channel porvides " + channelList.size() + " Videos:");
		for(Video current : channelList.get(channelNumber).getVideoList()) {
			logger.printSubline("Video : " + current.getName() + " ["+current.getUploadDate()
			+"], timeCategory: " + current.getTimeCategory());
		}
	}
	
	public void listContent() {
		System.out.println();
		logger.print("Content porvides " + channelList.size() + " Channels:");
		for(Channel currentChannel : channelList) {
			logger.printSubline("Channel : " + currentChannel.getName() + " (ID:"+currentChannel.getChanalID()
								+") with "+currentChannel.getVideoList().size() + " Videos.");
			for(Video currentVideo : currentChannel.getVideoList()) {
				logger.printSubline("Video : " + currentVideo.getName() + " ["+currentVideo.getUploadDate()
				+"], timeCategory: " + currentVideo.getTimeCategory());
			}
		}
	}
	
}
