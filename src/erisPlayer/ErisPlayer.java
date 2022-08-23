package erisPlayer;

import java.io.File;
import java.io.IOException;

public class ErisPlayer {
	
	private boolean isDebug = false;
	
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
		try {
			String comandLine = "cmd /c start firefox --private-window --kiosk=\"" + new File(currentPath + "html\\index.html").toURI() + "\"";
			Runtime.getRuntime().exec(comandLine).waitFor();
		}catch (Exception e) {
			logger.printError("while opening Browser", e);
		}
	}
	
	
	public static void main(String[] args) {
		ErisPlayer run = new ErisPlayer();
		run.main();
	}
}
