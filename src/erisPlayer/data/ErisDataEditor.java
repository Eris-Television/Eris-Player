package erisPlayer.data;

import java.io.File;
import java.util.Scanner;

import erisPlayer.ErisLogger;

public class ErisDataEditor {
	
	private String resourceDir;
	
	private ErisLogger logger;
	private ContentManager contentManager;
	
	
	public ErisDataEditor() {
		this.resourceDir = getPath() + "resources/";
		
		this.logger = new ErisLogger(resourceDir + "EditorLogs/");
		this.contentManager = new ContentManager(resourceDir, logger);
	}
	
	private String getPath() {
		try {
			return new File(".").getCanonicalFile().toURI().toString();
		} catch (Exception e) { return null; }
	}
	
	
	
	private void main() {
		while(true) {
			listMainActions();
			switch(getAction(0, 5)) {
			case 0:
				return;
			case 1:
				addAction();
				break;
			case 2:
				editAction();
				break;
			case 3: 
				removeAction();
				break;
			case 4:
				contentManager.listChanels();
				break;
			case 5:
				contentManager.listContent();
				break;
			default:
				break;
			}
		}
	}
	
	private void addAction() { 
		while(true){
			listAddActions();
			switch (getAction(0, 2)) {
			case 0: 
				return;
			case 1: 
				addChannel();
				break;
			case 2: 
				contentManager.listChanels();
				break;
			default:
				break;
			}
		}
	}
	
	private void editAction() {
		while(true){
			listEditActions();
			switch (getAction(0, 2)) {
			case 0: 
				return;
			case 1: 
				editChannel();
				break;
			case 2: 
				contentManager.listChanels();
				break;
			default:
				break;
			}
		}
	}
	
	private void removeAction() { 
		while(true){
			listRemoveActions();
			switch (getAction(0, 2)) {
			case 0: 
				return;
			case 1: 
				removeChannel();
				break;
			case 2: 
				contentManager.listChanels();
				break;
			default:
				break;
			}
		}
	}
	
	/* --- Menu-Actions --- */
	
	private void addChannel() {
		
	}
	
	private void editChannel() {
		
	}
	
	private void removeChannel() {
		
	}
	
	
	/* --- List-Actions --- */
	
	@SuppressWarnings("resource")
	private int getAction(int min, int max) {
		boolean no_mistakes = false;
		int input_int = -1;
		do {
			try {
				Scanner scanner = new Scanner(System.in);
				input_int = scanner.nextInt();
				if(input_int >= min && input_int <= max) {
					no_mistakes = true;
				}else {
					System.out.println("Please enter input with: ");
					System.out.println("Min: " + min);
					System.out.println("Max: " + max);
				}
			}catch(Exception e) {
				System.out.println("Error: Pleas enter a Number!");
			}
		}while(!no_mistakes);
		return input_int;
	}
	
	private void listMainActions() {
		System.out.println("\n ---- Eris-Data-Editor : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Add new Channel");
		System.out.println(" _> 2 : Edit existing Channel");
		System.out.println(" _> 3 : Remove existing Channel");
		System.out.println(" _> 4 : List all Channels");
		System.out.println(" _> 5 : List all Content");
	}
	
	private void listAddActions() {
		System.out.println("\n --- Add new Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Add new Channel");
		System.out.println(" _> 2 : List all Channels");
	}
	
	private void listEditActions() {
		System.out.println("\n ---- Edit Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Edit existing Channel");
		System.out.println(" _> 2 : List all Channels");
	}
	
	private void listRemoveActions() {
		System.out.println("\n ---- Remove Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Enter Channel-Number for deletion");
		System.out.println(" _> 2 : List all Channels");
	}
	
	/* --- Public-Main && Close --- */
	
	private void close() {
		contentManager.saveContent();
		logger.printLog();
	}
	
	public static void main(String[] args) {
		ErisDataEditor run = new ErisDataEditor();
		run.main();
		run.close();
	}
	
}
