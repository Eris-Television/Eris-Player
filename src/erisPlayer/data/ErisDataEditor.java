package erisPlayer.data;

import java.io.File;
import java.util.Scanner;

import erisPlayer.ErisLogger;

public class ErisDataEditor {
	
	private String resourceDir;
	
	private ErisLogger logger;
	private ContentManager contentManager;
	
	private Scanner scanner;
	
	public ErisDataEditor() {
		this.resourceDir = getPath() + "resources/";
		
		this.logger = new ErisLogger(resourceDir + "EditorLogs/");
		this.contentManager = new ContentManager(resourceDir, logger);
		
		this.scanner = new Scanner(System.in);
	}
	
	private String getPath() {
		try {
			return new File(".").getCanonicalFile().toURI().toString();
		} catch (Exception e) { return null; }
	}
	
	
	
	private void main() {
		if(contentManager.debugDownlaods()) return;
		
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
				return;
			}
		}
	}
	
	/* --- Sub-Menu --- */
	
	private void addAction() { 
		while(true){
			listAddActions();
			switch (getAction(0, 1)) {
			case 0: 
				return;
			case 1: 
				addChannel();
				break;
			case 2: 
				contentManager.listChanels();
				break;
			default:
				return;
			}
		}
	}
	
	private void editAction() {
		while(true){
			listEditActions();
			switch (getAction(0, 3)) {
			case 0: 
				return;
			case 1: 
				editChannel();
				break;
			case 2: 
				contentManager.listChanels();
				break;
			case 3:
				videoAction();
				break;
			default:
				return;
			}
		}
	}
	
	private void videoAction() {
		logger.printSubline("Select Channel : ");
		int channelID = getAction(0, contentManager.getChannelList().size() -1);
		while(true){
			listVideoActions();
			switch (getAction(0, 3)) {
			case 0: 
				return;
			case 1: 
				editVideo(channelID);
				break;
			case 2: 
				contentManager.listVideos(channelID);
				break;
			case 3:
				refreshVideo(channelID);
				break;
			case 4:
				removeVideo(channelID);
				break;
			default:
				return;
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
		String channelName, channelID, channelTag;
		
		System.out.println("Enter Channel-Name : ");
		channelName = getNext();
		System.out.println("Enter Channel-ID : ");
		channelID = getNext();
		System.out.println("Enter Channel-Tag : ");
		channelTag = getNext();
		
		Channel newChannel = new Channel(channelName, channelID, channelTag);
		contentManager.addChannel(newChannel);
		logger.print("Created Channel : " + channelName);
	}
	
	private void editChannel() {
		int lastChannelIndex = contentManager.getChannelList().size() -1;
		System.out.println("Enter Channel for edit (0 ... " + lastChannelIndex +") :");
		int selectedChannel = getAction(0, lastChannelIndex);

		
		String channelName, channelID, channelTag;
		
		System.out.println("Enter Channel-Name : ");
		channelName = getNext();
		System.out.println("Enter Channel-ID : ");
		channelID = getNext();
		System.out.println("Enter Channel-Tag : ");
		channelTag = getNext();
		
		Channel newChannel = new Channel(channelName, channelID, channelTag);
		contentManager.editChannel(lastChannelIndex, newChannel);
		
		logger.print("Deleted Channel : " + selectedChannel);
	}
	
	private void editVideo(int channelID) {
		System.out.println("Comeing sone!");
	}
	
	private void refreshVideo(int channelID) {
		contentManager.updateChannel(contentManager.getChannelList().get(channelID));
		contentManager.saveContent();
	}
	
	private void removeVideo(int channelID) {
		System.out.println("Select Video:");
		int videoID = getAction(0, contentManager.getChannelList().get(channelID).getVideoList().size() -1);
		contentManager.removeVideo(channelID, videoID);
	}
	
	private void removeChannel() {
		int lastChannelIndex = contentManager.getChannelList().size() -1;
		System.out.println("Enter Channel for deletion (0 ... " + lastChannelIndex +") :");
		int deleteChannel = getAction(0, lastChannelIndex);
		contentManager.removeChannel(lastChannelIndex);
		logger.print("Deleted Channel : " + deleteChannel);
	}
	
	
	/* --- List-Actions --- */
	
	private int getAction(int min, int max) {
		boolean validInput = false;
		int input = -1;
		do {
			try{
				if(scanner.hasNext()) {
					input = scanner.nextInt();
					if(min <= input && input <= max) {
						validInput = true;
					}else {
						System.out.println("Please enter input with: ");
						System.out.println("Min: " + min);
						System.out.println("Max: " + max);
					}
				}
			}catch(Exception e) {
				System.err.println("Error: Pleas enter a Number! " + e);
				scanner = new Scanner(System.in);
			}
		}while(!validInput);
		return input;
	}
	
	private String getNext() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
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
	}
	
	private void listEditActions() {
		System.out.println("\n ---- Edit Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Edit existing Channel");
		System.out.println(" _> 2 : List all Channels");
		System.out.println(" _> 3 : Edit Videos");
	}
	
	private void listVideoActions() {
		System.out.println("\n ---- Edit Videos : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Edit existing Video");
		System.out.println(" _> 2 : List all Videos");
		System.out.println(" _> 3 : refresh Videos");
		System.out.println(" _> 4 : Remove Video");
	}
	
	private void listRemoveActions() {
		System.out.println("\n ---- Remove Channel : ----");
		System.out.println(" _> 0 : Exit");
		System.out.println(" _> 1 : Enter Channel-Number for deletion");
		System.out.println(" _> 2 : List all Channels");
	}
	
	/* --- Public-Main & Close --- */
	
	private void close() {
		contentManager.saveContent();
		logger.printLog();
		scanner.close();
	}
	
	public static void main(String[] args) {
		ErisDataEditor run = new ErisDataEditor();
		run.main();
		run.close();
	}
	
}
