package erisPlayer;

import java.io.File;
import java.io.IOException;

public class ErisPlayer {
	
	private boolean isDebug = false;
	
	private String localDir;
	
	private ErisLogger logger;
	
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
			String comandLine = "cmd /c start firefox --private-window --kiosk=\"" + localDir + "html/index.html/" + "\"";
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
