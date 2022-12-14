package ErisPlayer.data.editor;

import java.util.Scanner;

import ErisPlayer.ErisLogger;
import ErisPlayer.PathHandler;
import ErisPlayer.data.Channel;
import ErisPlayer.data.ContentManager;

public class ErisDataEditor {
	
	private ErisLogger logger;
	private ContentManager contentManager;
	
	private Scanner scanner;
	
	public ErisDataEditor() {
		this.logger = new ErisLogger(PathHandler.editorLogDir());
		this.contentManager = new ContentManager(PathHandler.resourceDir(), logger);
		
		this.scanner = new Scanner(System.in);
	}
	
	private void main() {
		while(true) {
			EDEprinter.listMainActions();
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
			EDEprinter.listAddActions();
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
			EDEprinter.listEditActions();
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
			EDEprinter.listVideoActions();
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
			EDEprinter.listRemoveActions();
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
		
		logger.print("Edit Channel : " + selectedChannel);
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
