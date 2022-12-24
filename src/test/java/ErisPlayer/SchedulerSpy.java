package ErisPlayer;

import java.net.URI;

import ErisPlayer.data.Channel;
import ErisPlayer.data.Video;

public class SchedulerSpy extends ErisScheduler{
	
	public static final String TEST_ENTRY = "ERD : #01";
	
	public SchedulerSpy(URI resourceDir, ErisLogger logger) {
		super(resourceDir, logger);
	}

	public String[][] getSchedule() { return schedule; }
	
	public String getRandomVideo() {
		return super.getRandomVideo();
	}
	
	public String getScheduledVideo(String entry) {
		return super.getScheduledVideo(entry);
	}
	
	public String getPath(Channel channel, Video video) {
		return super.getPath(channel, video);
	}
	
}
