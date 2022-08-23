package erisPlayer;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ErisPlayer {
	
	private boolean isDebug = true;
	
	private String currentPath;
	
	private ErisLogger logger;
	
	public ErisPlayer() {
		try {
			currentPath = new java.io.File(".").getCanonicalPath() +"\\";
		} catch (IOException e) {}
		logger = new ErisLogger(currentPath);
		logger.print("Opening ErisPlayer");
	}
	
	private void main() {
		init();
		
		
		
		close();
	}
	
	private void init() {
		
		if(isDebug) return;
		openWebsite();
	}
	
	private void close() {
		
		logger.printLog();
	}
	
	private void openWebsite() {
		if(isDebug) { return; }
		if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
				Desktop.getDesktop().browse(new File(currentPath + "html\\index.html").toURI());
			}catch (Exception e) {
				logger.printError("while opening Browser", e);
			}
		}else {
			logger.printError("Can't open Browser", null);
		}
	}
	
	
	public static void main(String[] args) {
		ErisPlayer run = new ErisPlayer();
		run.main();
	}
}
