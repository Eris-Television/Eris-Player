package erisPlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
		this.path = path + "log\\";
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
		message = " _> " + getDateTime() + message;
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
			File file = new File(path + getDateTime() + "ErisPlayer.log");
			file.createNewFile();
			print("Printing Log-File");
			Files.write(file.toPath(), log);
		} catch (IOException e) {
			printError("Can't print Log", e);
		}
	}
	
	
	// --- Internal ---
	
	private String getDateTime() {
		return "[" + formatter.format(now) + "] ";
	}
	
}
