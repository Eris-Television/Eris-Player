package erisPlayer.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import erisPlayer.ErisLogger;

public class ErisDataEditor {
	
	private String resourceDir;
	
	private ErisLogger logger;
	private ContentLoader contentLoader;
	
	private ArrayList<Channel> channelList;
	
	public ErisDataEditor() {
		this.resourceDir = getPath() + "resources/";
		
		this.logger = new ErisLogger(resourceDir + "$EditorLogs/");
		this.contentLoader = new ContentLoader(getPath(), logger);
		
		this.channelList = contentLoader.getContent();
	}
	
	private String getPath() {
		try {
			return new File(".").getCanonicalFile().toURI().toString();
		} catch (IOException e) { return null; }
	}
	
	
	
	private void main() {
		
		
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
