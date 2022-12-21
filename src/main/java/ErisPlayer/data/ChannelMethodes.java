package ErisPlayer.data;

import java.util.ArrayList;

public class ChannelMethodes {
	
	protected ArrayList<Channel> channelList;
	
	/* --- Channel: Add / Edit / Remove --- */

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
    
    
    /* --- Channel-Methods --- */
    
    public ArrayList<Channel> getChannelList() { return channelList; }
    
    public void setChannelList(ArrayList<Channel> channelList) { this.channelList = channelList; }
    
    public Video getLastUnpublishedVideo(Channel channel) {
        return channel.getLastUnpublishedVideo();
    }
    
}
