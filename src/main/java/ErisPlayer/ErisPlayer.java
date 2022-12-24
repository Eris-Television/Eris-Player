package ErisPlayer;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

import ErisPlayer.data.ContentManager;

public class ErisPlayer {
	
	private static boolean close = false;
	public static void closePlayer() { close = true; }
	
	private ErisLogger logger;
	private ContentManager contentManager;
	private ErisScheduler scheduler;
	
	public ErisPlayer() {
		this.logger = new ErisLogger(PathHandler.logDir());
		this.logger.print("Opening ErisPlayer");
	}
	
	private void main() {
		
		openContentManager();
		openScheduler();
		startSocketServer();
		openWebsite();
		
		while(!close);

		close();
	}
	
	 /* --- initialize --- */
	
	private void openContentManager() {
		this.contentManager = new ContentManager(PathHandler.resourceDir(), logger);
		contentManager.updateChannels();
	}
	
	private void openScheduler() {
		scheduler = new ErisScheduler(PathHandler.resourceDir(), logger, contentManager);
		scheduler.toString(); // TODO
	}
	
	private void startSocketServer() {
		Server server = new Server("127.0.0.1", 8080, "/", ErisServer.class);
		try {
			server.start();
		} catch (DeploymentException e) {
			logger.printError("While starting server", e);
		}
	}
	
	
	private void openWebsite() {
		try {
			String comandLine = "cmd /c start firefox --private-window \"" + PathHandler.localDir().toString() + "html/index.html" + "\"";
			Runtime.getRuntime().exec(comandLine).waitFor();
		}catch (Exception e) {
			logger.printError("while opening Browser", e);
		}
	}
	
	/* --- close --- */
	
	private void close() {
		logger.printLog();
	}
	
	/* --- Public Start-Method --- */
	
	public static void main(String[] args) {
		ErisPlayer run = new ErisPlayer();
		run.main();
	}
}
