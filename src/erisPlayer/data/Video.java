package erisPlayer.data;

import java.time.LocalDate;

public class Video {

    private final String name;
    private final LocalDate uploadDate;
    private final int playTime;

    public Video(String name, LocalDate uploadDate, int playTime) {
        this.name = name;
        this.uploadDate = uploadDate;
        this.playTime = playTime;
    }

    public String getName() {
        return name;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public int getPlayTime() {
        return playTime;
    }
    
    @Override
    public String toString() {
    	return "["+ uploadDate +"] "+ name;
    }
    
    @Override
    public boolean equals(Object object) {
    	if(object.getClass() != Video.class) { return false; }
    	
    	Video check = (Video) object;
    	if(check.getName().equals(name)
    			&& check.getUploadDate().isEqual(uploadDate)
    			&& check.getPlayTime() == playTime) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
