package erisPlayer.data;

import java.io.File;
import java.net.URI;

import erisPlayer.ErisDateTimer;
import erisPlayer.ErisLogger;

public class ContentManager extends ChannelMethodes{
	
	private URI resourceDir;
	
	private ErisLogger logger;
	private ContentParser contentParser;
	private DownloadManager downloadManager;
	
	public ContentManager(URI resourceDir, ErisLogger logger) {
		this.resourceDir = resourceDir;
		this.logger = logger;
		this.contentParser = new ContentParser(resourceDir.resolve("channels.xml"), logger);
		this.downloadManager = new DownloadManager(resourceDir.resolve("Downloads/"), logger);
		
		loadContent();
		logger.print("ContentManager started ...");
	}
	
	/* --- Load & Save --- */
	
	public void loadContent() {
		logger.print("ContentManager is loading Content ...");
		channelList = contentParser.readContent();
	}
	
    public void saveContent() {
        logger.print("ContentManager is saveing Content ...");
        contentParser.writeContent(channelList);
    }

    /* --- Update Channels --- */

    public void updateChannels() {
        for (Channel channel : channelList) {
            updateChannel(channel);
        }
        saveContent();
    }

    public void updateChannel(Channel channel) {
    	downloadManager.downloadNewVideos(channel);
    	processVideos(channel);
    }
    
    public void processVideos(Channel channel) {
		try {
	    	for(File file : new File(new URI(resourceDir + "Downloads/").getPath()).listFiles()) {
	    		String channelTag = file.getName().split("_")[0];
	    		String dateString = file.getName().split("_")[1];
	    		String videoTitle = file.getName().split("_")[3].split(".")[0]; // TODO handle Video time
	    		
	    		if(channel.getTag().equals(channelTag)) {
	    			channel.addVideo(new Video(videoTitle, ErisDateTimer.toLocalDate(dateString), -1));
	    		}
	    		
	    		System.out.println(file);
	    		System.out.println(file.getName());
		    	System.out.println(file.getAbsolutePath());
	    	}
		} catch (Exception e) {
			logger.printError("Can't process Videos", e);
		}
    }
    
    /* --- list-Outputs --- */

    public void listChanels() {
        System.out.println();
        logger.print("Content porvides " + channelList.size() + " Channels:");
        for (Channel current : channelList) {
            logger.printSubline("Channel : " + channelList.indexOf(current) + " : " + current.getName()
                    + " (ID:" + current.getChanalID() + ") with " + current.getVideoList().size() + " Videos.");
        }
    }

    public void listVideos(int channelNumber) {
        System.out.println();
        logger.print("Channel porvides " + channelList.size() + " Videos:");
        for (Video current : channelList.get(channelNumber).getVideoList()) {
            logger.printSubline("Video : " + current.getName() + " [" + current.getUploadDate()
                    + "], timeCategory: " + current.getPlayTime());
        }
    }

    public void listContent() {
        System.out.println();
        logger.print("Content porvides " + channelList.size() + " Channels:");
        for (Channel currentChannel : channelList) {
            logger.printSubline("Channel : " + currentChannel.getName() + " (ID:" + currentChannel.getChanalID()
                    + ") with " + currentChannel.getVideoList().size() + " Videos.");
            for (Video currentVideo : currentChannel.getVideoList()) {
                logger.printSubline("Video : " + currentVideo.getName() + " [" + currentVideo.getUploadDate() + "]");
            }
        }
    }
    
    public boolean debugDownlaods() {
    	downloadManager.debug(channelList.get(1));
    	processVideos(new Channel("Debug", "DEBUG", "HRZ"));
    	return true;
    }
}
