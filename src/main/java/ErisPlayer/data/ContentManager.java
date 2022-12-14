package ErisPlayer.data;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import ErisPlayer.ErisDateTimer;
import ErisPlayer.ErisLogger;
import ErisPlayer.PathHandler;

public class ContentManager extends ChannelMethodes{
	
	private URI resourceDir;
	
	private ErisLogger logger;
	private ContentParser contentParser;
	private DownloadManager downloadManager;
	
	public ContentManager(URI resourceDir, ErisLogger logger) {
		this.resourceDir = resourceDir;
		this.logger = logger;
		this.contentParser = new ContentParser(resourceDir.resolve("content.xml"), logger);
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
    	downloadManager.downloadVideos(channel);
    	processVideos(channel);
    }
    
    public void processVideos(Channel channel) {
		try {
	    	for(File file : new File(resourceDir.resolve("Downloads/")).listFiles()) {
	    		String fileName = file.getName();
	    		
	    		String channelTag = fileName.split("_")[0];
	    		String dateString = fileName.split("_")[1];
	    		String playTime   = fileName.split("_")[2];
	    		String videoTitle = fileName.split("_")[3];
	    		videoTitle = videoTitle.substring(0, videoTitle.length() - (".mp4".length()));
	    		
	    		if(!channel.getTag().equals(channelTag)) { continue; }
	    		
	    		Video video = new Video(videoTitle, ErisDateTimer.toLocalDate(dateString), Integer.parseInt(playTime));
	    		channel.addVideo(video);
	    		
	    		File folder = new File(resourceDir.resolve(channelTag));
	    		if(!folder.exists()) { folder.mkdir(); }
	    		
	    		File newPath = new File(PathHandler.getCopyPath(resourceDir, channel, video));
	    		Files.move(file.toPath(), newPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			logger.printError("Can't process Videos", e);
		}
    }
    
    /* --- list-Outputs --- */

    public String listChanels() {
        System.out.println();
        logger.print("Content porvides " + channelList.size() + " Channels:");
        for (Channel current : channelList) {
            logger.printSubline("Channel : " + channelList.indexOf(current) + " : " + current.getName()
                    + " (ID:" + current.getChanalID() + ") with " + current.getVideoList().size() + " Videos.");
        }
        return "";
    }

    public String listVideos(int channelNumber) {
        System.out.println();
        logger.print("Channel porvides " + channelList.size() + " Videos:");
        for (Video current : channelList.get(channelNumber).getVideoList()) {
            logger.printSubline("Video : " + current.getName() + " [" + current.getUploadDate()
                    + "], timeCategory: " + current.getPlayTime());
        }
        return "";
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
    
}
