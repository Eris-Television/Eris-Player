package ErisPlayer.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    /** YouTube-Channel-ID */
    private final String chanalID; // TODO: refactor channelID
    private final String tag;

    private ArrayList<Video> videoList;
    private ArrayList<Video> unpublishedVideos;

    public Channel(String name, String chanalID, String tag) {
    	this.name = name;
        this.chanalID = chanalID;
        this.tag = tag;
        this.videoList = new ArrayList<>();
        this.unpublishedVideos = new ArrayList<>();
    }
    
    public Channel(String name, String chanalID, String tag, ArrayList<Video> videos) {
    	this(name, chanalID, tag);
        this.videoList = videos;
    }
    
    /* --- Getter && Setter --- */
    
    public String getName() {
        return name;
    }

    public String getChanalID() {
        return chanalID;
    }

    public String getTag() {
        return tag;
    }
    
    /* --- Video-Methods --- */

    public void addVideo(Video video) {
        videoList.add(video);
        unpublishedVideos.add(video);
    }
    
    public void editVideo(int videoIndex, Video video) {
        videoList.set(videoIndex, video);
    }

    public void removeVideo(int videoIndex) {
        videoList.remove(videoIndex);
    }

    public Video getLastUpload() {
        return videoList.get(videoList.size() - 1);
    }
    
    public Video getLastUnpublishedVideo() {
    	int unpublishedVideosSize = unpublishedVideos.size();
    	if(unpublishedVideosSize > 0) {
    		Video returnVideo = unpublishedVideos.get(unpublishedVideosSize -1);
    		unpublishedVideos.remove(unpublishedVideosSize -1);
    		return returnVideo;
    	}else {
    		return null;
    	}
    }
    
    /* --- VideoList-Methods --- */

    public ArrayList<Video> getVideoList() {
        return videoList;
    }

    public ArrayList<Video> getLastWeekUploads() {
        ArrayList<Video> returnList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Video current : videoList) {
            LocalDate upload = current.getUploadDate();
            Period period = Period.between(upload, now.toLocalDate());
            if (period.getDays() <= 7 && period.getMonths() == 0 && period.getYears() == 0) {
                returnList.add(current);
            }
        }

        return returnList;
    }
    
    
    /* --- Override-Methods --- */
    
    @Override
    public boolean equals(Object obj) {
    	if(!obj.getClass().isInstance(this)) { return false; }
    	Channel check = (Channel) obj;
    	return equals(check);
    }
    
    public boolean equals(Channel check) {
    	return (check.name.equals(this.name)
    			&& check.chanalID.equals(this.chanalID)
    			&& check.tag.equals(this.tag)
    			&& checkVideos(check.getVideoList()));
    }
    
    private boolean checkVideos(ArrayList<Video> checkList) {
    	if(videoList.size() != checkList.size()) { return false; }
    	boolean check = true;
    	
    	for(int i = 0; i < videoList.size(); i++) {
    		check = check && videoList.get(i).equals(checkList.get(i));
    	}
    	return check;
    }
    
    @Override
    public String toString() {
    	String string = "["+ tag +" : "+ chanalID +"] "+ name; 
    	return string;
    }
}
