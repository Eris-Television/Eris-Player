package erisPlayer.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;

import erisPlayer.ErisLogger;

public class ErisDataEditor {
	
	private String resourceDir;
	
	private ErisLogger logger;
	private ContentLoader contentLoader;
	
	private ArrayList<Channel> channelList;
	
	public ErisDataEditor() {
		this.resourceDir = getPath() + "resources/";
		
		this.logger = new ErisLogger(resourceDir + "EditorLogs/");
		this.contentLoader = new ContentLoader(getPath(), logger);
		
		this.channelList = contentLoader.getContent();
	}
	
	private String getPath() {
		try {
			return new File(".").getCanonicalFile().toURI().toString();
		} catch (IOException e) { return null; }
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void main() {
		
		Channel testChannel = new Channel("TestName", "Test-ID", "TST");
		channelList.add(testChannel);
		Channel testChannel2 = new Channel("TestName 2", "Test-ID 2", "TST 2");
		channelList.add(testChannel2);
		String path = resourceDir + "channels.eris";
		
		try(FileOutputStream fos = new FileOutputStream(Paths.get(new URI(path)).toFile());
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(channelList);
			
		} catch (FileNotFoundException e) {} catch (IOException e) {} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try(FileInputStream fis = new FileInputStream(Paths.get(new URI(path)).toFile());
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			channelList = (ArrayList<Channel>) ois.readObject();;
		} catch (Exception e) {
			logger.printError("Can't read Channels from File!", e);
			channelList = new ArrayList<>();
		}
		
		
		for(Channel current : channelList) {
			System.out.println("Channel : " +current.getName());
		}
		
		close();
	}
	
	
	
	private void close() {
		logger.printLog();
	}
	
	public static void main(String[] args) {
		ErisDataEditor run = new ErisDataEditor();
		run.main();
	}
	
}
