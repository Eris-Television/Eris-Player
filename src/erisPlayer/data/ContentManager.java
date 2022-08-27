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
	}
	
	@SuppressWarnings("unchecked")
	public void loadContent() {
		String path = resourceDir + "channels.eris";
		
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
		
		try(FileOutputStream fos = new FileOutputStream(Paths.get(new URI(path)).toFile());
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(channelList);
		} catch (Exception e) {
			logger.printError("Can't write Channels to File!", e);
		}
	}
	
}
