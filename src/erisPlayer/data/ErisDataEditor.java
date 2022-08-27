package erisPlayer.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import erisPlayer.ErisLogger;

public class ErisDataEditor {
	
	private String resourceDir;
	
	private ErisLogger logger;
	private ContentManager contentManager;
	
	private ArrayList<Channel> channelList;
	
	public ErisDataEditor() {
		this.resourceDir = getPath() + "resources/";
		
		this.logger = new ErisLogger(resourceDir + "EditorLogs/");
		this.contentManager = new ContentManager(resourceDir, logger);
		
		this.channelList = contentManager.getChannelList();
	}
	
	private String getPath() {
		try {
			return new File(".").getCanonicalFile().toURI().toString();
		} catch (Exception e) { return null; }
	}
	
	
	
	private void main() {
		while(true) {
			listMainActions();
			switch(getAction(1, 5)) {
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
			case 5:
				contentManager.listContent();
			default:
				break;
			}
		}
	}
	
	private void addAction() { 
		while(true){
			listAddActions();
			channelList.size();
		}
	}
	
	private void editAction() {
		while(true){
			listEditActions();
		}
	}
	
	private void removeAction() { 
		while(true){
			listRemoveActions();
		}
	}
	
	
	/* --- List-Actions --- */
	
	private int getAction(int lowerEnd, int upperEnd) {
		int returnInt = -1;
		Scanner scanner = new Scanner(System.in);
		while(!(returnInt < lowerEnd && returnInt > upperEnd)) {
			try {
				returnInt = (int)scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Please enter an Integer with in 0 and 3!");
			}
		}
		scanner.close();
		return returnInt;
	}
	
	private void listMainActions() {
		System.out.println(" ---- Eris-Data-Editor : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Add new Channel");
		System.out.println(" _> 2 : Edit existing Channel");
		System.out.println(" _> 3 : Remove existing Channel");
		System.out.println(" _> 4 : List all Channels");
		System.out.println(" _> 5 : List all Content");
	}
	
	private void listAddActions() {
		System.out.println(" --- Add new Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Add new Channel");
		System.out.println(" _> 2 : Edit existing Channel");
		System.out.println(" _> 3 : Remove existing Channel");
	}
	
	private void listEditActions() {
		System.out.println(" ---- Edit Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Add new Channel");
		System.out.println(" _> 2 : Edit existing Channel");
		System.out.println(" _> 3 : Remove existing Channel");
	}
	
	private void listRemoveActions() {
		System.out.println(" ---- Remove Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : List all Channels");
		System.out.println(" _> 2 : Enter Channel-Number for deletion");
	}
	
	/* --- Close && Public-Main --- */
	
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
