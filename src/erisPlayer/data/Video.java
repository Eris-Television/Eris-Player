package erisPlayer.data;

import java.time.LocalDate;

public class Video {

    private final String name;
    private final LocalDate uploadDate;
    private final String format;
    private final TimeCategory timeCategory;

    public Video(String name, LocalDate uploadDate, String format, TimeCategory timeCategory) {
        this.name = name;
        this.uploadDate = uploadDate;
        this.format = format;
        this.timeCategory = timeCategory;
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

    public TimeCategory getTimeCategory() {
        return timeCategory;
    }
    
    @Override
    public String toString() {
    	return "["+ uploadDate +"]_"+ name +"_"+ format +"_"+ timeCategory.toString();
    }
}
