package erisPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ErisLogger extends ErisDateTimer{
	
	private URI path;
	private ArrayList<String> log;
	public ArrayList<String> getLog() { return log; }
	
	public ErisLogger(URI path) {
		super();
		
		this.path = path;
		this.log = new ArrayList<>();
	}
	
	public void print(String message) {
		message = getDateTimeInBrackets() + message;
		System.out.println(message);
		log.add(message);
	}
	
	public void printSubline(String message) {
		message = getDateTimeInBrackets() +" _> "+ message;
		System.out.println(message);
		log.add(message);
	}
	
	public void printError(String error, Exception e) {
		error = getDateTimeInBrackets() + "ERROR " + error + " : " + e.getMessage();
		System.err.println(error);
		log.add(error);
	}
	
	public void printLog() {
		try {
			String filePath = Paths.get(path).toString();
			File file;
			if(System.getProperty("os.name").startsWith("Windows")) {
				file = new File(filePath +"\\"+ getDateTimeInBrackets() + "ErisPlayer.log");
			}else {
				file = new File(filePath +"/"+ getDateTimeInBrackets() + "ErisPlayer.log");
			} // TODO change to default System.seperator
			file.createNewFile();
			print("Printing Log-File");
			Files.write(file.toPath(), log);
		} catch (IOException e) {
			printError("Can't print Log", e);
		}
	}
	
	
}
