package erisPlayer.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;

import erisPlayer.ErisLogger;

public class ContentManager {
	
	private String resourceDir;
	
	private ErisLogger logger;
	
	private ArrayList<Channel> channelList;
	public ArrayList<Channel> getChannelList() { return channelList; }
	public void setChannelList(ArrayList<Channel> channelList) { this.channelList = channelList; }

	public ContentManager(String resourceDir, ErisLogger logger) {
		this.resourceDir = resourceDir;
		this.logger = logger;
		
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

    /* --- Add / Edit / Remove --- */

    public void addChannel(Channel channel) {
        channelList.add(channel);
    }

    public void editChannel(int chnnelIndex, Channel channel) {
        channelList.set(chnnelIndex, channel);
    }

    public void removeChannel(int chnnelIndex) {
        channelList.remove(chnnelIndex);
    }

    public void addVideo(int channelIndex, Video video) {
        channelList.get(channelIndex).addVideo(video);
    }

    public void editVideo(int channelIndex, int videoIndex, Video video) {
        channelList.get(channelIndex).editVideo(videoIndex, video);
    }

    public void removeVideo(int channelIndex, int videoIndex) {
        channelList.get(channelIndex).removeVideo(videoIndex);
    }

    /* --- Methods --- */

    public void updateChannels() {
        for (Channel channel : channelList) {
            updateChannel(channel);
        }
        saveContent();
    }

    public void updateChannel(Channel channel) {
    	channel.addUnpublishedVideos(getNewDownload(channel));
    }

    private ArrayList<Video> getNewDownload(Channel channel) {
        return new ArrayList<>();
    }

    public Video getLastUnpublishedVideo(Channel channel) {
        return channel.getLastUnpublishedVideo();
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
}
