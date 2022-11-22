package erisPlayer.data;

import java.time.LocalDate;

public class Video {

    private final String name;
    private final LocalDate uploadDate;
    private final String format;
    private final int playTime;

    public Video(String name, LocalDate uploadDate, String format, int playTime) {
        this.name = name;
        this.uploadDate = uploadDate;
        this.format = format;
        this.playTime = playTime;
    }

    public String getName() {
        return name;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public String getFormat() {
        return format;
    }

    public int getPlayTime() {
        return playTime;
    }
    
    @Override
    public String toString() {
    	return "["+ uploadDate +"]_"+ name +"_"+ format;
    }
}
