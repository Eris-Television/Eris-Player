package erisPlayer.data;

import java.util.ArrayList;

public class Channel {
	
	private final String name;
	private final String chanalID;
	private final String tag;
	
	private ArrayList<Video> videoList;
	
	public Channel(String name, String chanalID, String tag) {
		this.name = name;
		this.chanalID = chanalID;
		this.tag = tag;
		this.videoList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getChanalID() {
		return chanalID;
	}

	public String getTag() {
		return tag;
	}
	
	public void addVideo(Video video) {
		videoList.add(video);
	}
	
	public ArrayList<Video> getVideoList() {
		return videoList;
	}
	
}
