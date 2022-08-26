package erisPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ErisLogger {
	
	private String path;
	private ArrayList<String> log;
	public ArrayList<String> getLog() { return log; }
	
	private DateTimeFormatter formatter;
	private LocalDateTime now;
	
	public ErisLogger(String path) {
		this.path = path;
		this.log = new ArrayList<>();
		
		formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm-ss");
		now = LocalDateTime.now();
	}
	
	public void print(String message) {
		message = getDateTime() + message;
		System.out.println(message);
		log.add(message);
	}
	
	public void printSubline(String message) {
		message = getDateTime() +" _> "+ message;
		System.out.println(message);
		log.add(message);
	}
	
	public void printError(String error, Exception e) {
		error = getDateTime() + "ERROR " + error + " : " + e.getMessage();
		System.err.println(error);
		log.add(error);
	}
	
	public void printLog() {
		try {
			String filePath = Paths.get(new URI(path)).toString();
			File file;
			if(System.getProperty("os.name").startsWith("Windows")) {
				file = new File(filePath +"\\"+ getDateTime() + "ErisPlayer.log");
			}else {
				file = new File(filePath +"/"+ getDateTime() + "ErisPlayer.log");
			}
			file.createNewFile();
			print("Printing Log-File");
			Files.write(file.toPath(), log);
		} catch (IOException | URISyntaxException e) {
			printError("Can't print Log", e);
		}
	}
	
	
	// --- Internal ---
	
	private String getDateTime() {
		return "[" + formatter.format(now) + "] ";
	}
	
}
