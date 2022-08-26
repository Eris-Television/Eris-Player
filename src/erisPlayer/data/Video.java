package erisPlayer.data;

import java.util.Date;

public class Video {
	
	private final String name;
	private final Date uploadDate;
	private final String format;
	private final TimeCategory timeCategory;
	
	public Video(String name, Date uploadDate, String format, TimeCategory timeCategory) {
		this.name = name;
		this.uploadDate = uploadDate;
		this.format = format;
		this.timeCategory = timeCategory;
	}

	public String getName() {
		return name;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public String getFormat() {
		return format;
	}

	public TimeCategory getTimeCategory() {
		return timeCategory;
	}
	
	
}
