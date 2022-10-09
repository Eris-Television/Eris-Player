package erisPlayer.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import erisPlayer.ErisLogger;

public class ContentManager extends ChannelMethodes{
	
	private String resourceDir;
	
	private ErisLogger logger;
	private DownloadManager downloadManager;
	
	public ContentManager(String resourceDir, ErisLogger logger) {
		this.resourceDir = resourceDir;
		this.logger = logger;
		this.downloadManager = new DownloadManager(resourceDir, logger);
		
		loadContent();
		listContent();
		
		logger.print("ContentManager started ...");
	}
	
	/* --- Load & Save --- */
	
	@SuppressWarnings("unchecked")
	public void loadContent() {
		String path = resourceDir + "channels.eris";
		logger.print("ContentManager is loading Content ...");
		
		try(FileInputStream fis = new FileInputStream(Paths.get(new URI(path)).toFile());
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			channelList = (ArrayList<Channel>) ois.readObject();
		} catch (Exception e) {
			logger.printError("Can't read Channels from File!", e);
			channelList = new ArrayList<>();
		}
	}
	
    public void saveContent() {
        String path = resourceDir + "channels.eris";
        logger.print("ContentManager is saveing Content ...");

        try (FileOutputStream fos = new FileOutputStream(Paths.get(new URI(path)).toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(channelList);
        }
        catch (Exception e) {
            logger.printError("Can't write Channels to File!", e);
        }
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
	    		String videoTitle = file.getName().split("_")[3].split(".")[0];
	    		
	    		if(channel.getTag().equals(channelTag)) {
	    			channel.addVideo(new Video(videoTitle, getDate(dateString), getFormat(channel, videoTitle), getTimeCategory(file)));
	    		}
	    		
	    		System.out.println(file);
	    		System.out.println(file.getName());
		    	System.out.println(file.getAbsolutePath());
	    	}
		} catch (Exception e) {
			logger.printError("Can't process Videos", e);
		}
    }
    
    private LocalDate getDate(String dateString) {
    	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuuMMdd");
    	return LocalDate.parse(dateString, dateTimeFormatter);
    }
    
    private String getFormat(Channel channel, String videoTitle) {
    	return "default";
    }
    
    private TimeCategory getTimeCategory(File file) {
    	return null;
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
                    + "], timeCategory: " + current.getTimeCategory());
        }
    }

    public void listContent() {
        System.out.println();
        logger.print("Content porvides " + channelList.size() + " Channels:");
        for (Channel currentChannel : channelList) {
            logger.printSubline("Channel : " + currentChannel.getName() + " (ID:" + currentChannel.getChanalID()
                    + ") with " + currentChannel.getVideoList().size() + " Videos.");
            for (Video currentVideo : currentChannel.getVideoList()) {
                logger.printSubline("Video : " + currentVideo.getName() + " [" + currentVideo.getUploadDate()
                        + "], timeCategory: " + currentVideo.getTimeCategory());
            }
        }
    }
    
    public boolean debugDownlaods() {
    	downloadManager.debug(channelList.get(1));
    	processVideos(new Channel("Debug", "DEBUG", "HRZ"));
    	return true;
    }
}
