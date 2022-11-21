package erisPlayer;

import java.io.File;
import java.io.IOException;

import erisPlayer.data.ContentManager;

public class ErisPlayer {
	
	private boolean isDebug = false;
	
	private ErisLogger logger;
	private ContentManager contentManager;
	private ErisScheduler scheduler;
	private ErisSocketServer socketServer;
	
	public ErisPlayer() {
		this.logger = new ErisLogger(PathHandler.logDir());
		this.logger.print("Opening ErisPlayer");
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
		this.contentManager = new ContentManager(PathHandler.resourceDir().toString(), logger);
		contentManager.updateChannels();
	}
	
	private void openScheduler() {
		this.scheduler = new ErisScheduler(PathHandler.localDir().toString(), logger);
	}
	
	private void startSocketServer() {
		this.socketServer = new ErisSocketServer(logger);
		socketServer.start();
	}
	
	
	private void openWebsite() {
		try {
			String comandLine = "cmd /c start firefox --private-window --kiosk=\"" + PathHandler.localDir().toString() + "html/index.html/" + "\"";
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
