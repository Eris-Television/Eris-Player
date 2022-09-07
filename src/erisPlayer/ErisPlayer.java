package erisPlayer;

import java.io.File;
import java.io.IOException;

import erisPlayer.data.ContentManager;

public class ErisPlayer {
	
	private boolean isDebug = false;
	
	private String localDir;
	
	private ErisLogger logger;
	private ContentManager contentManager;
	private ErisScheduler scheduler;
	private ErisSocketServer socketServer;
	
	public ErisPlayer() {
		this.localDir = getPath();
		this.logger = new ErisLogger(localDir + "log/");
		this.logger.print("Opening ErisPlayer");
	}
	
	private String getPath() {
		try {
			return new File(".").getCanonicalFile().toURI().toString();
		} catch (IOException e) { return null; }
	}
	
	private void main() {
		init();
		
		openContentManager();
		openScheduler();
		startSocketServer();
		
		close();
	}
	
	 /* --- initialize --- */
	
	private void init() {
		
		if(isDebug) return;
		openWebsite();
	}
	
	private void openContentManager() {
		this.contentManager = new ContentManager(localDir + "resources/", logger);
		contentManager.updateChannels();
	}
	
	private void openScheduler() {
		this.scheduler = new ErisScheduler(localDir, logger);
	}
	
	private void startSocketServer() {
		this.socketServer = new ErisSocketServer(logger);
		socketServer.start();
	}
	
	
	private void openWebsite() {
		try {
			String comandLine = "cmd /c start firefox --private-window --kiosk=\"" + localDir + "html/index.html/" + "\"";
			Runtime.getRuntime().exec(comandLine).waitFor();
		}catch (Exception e) {
			logger.printError("while opening Browser", e);
		}
	}
	
	/* --- close --- */
	
	private void close() {
		socketServer.close();
		logger.printLog();
	}
	
	/* --- Public Start-Method --- */
	
	public static void main(String[] args) {
		ErisPlayer run = new ErisPlayer();
		run.main();
	}
}
