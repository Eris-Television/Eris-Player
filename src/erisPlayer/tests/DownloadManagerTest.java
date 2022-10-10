package erisPlayer.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import erisPlayer.ErisLogger;
import erisPlayer.data.Channel;

class DownloadManagerTest {
	
	private final String directory;
	private ErisLogger logger;
	
	private Channel testChannel = new Channel("Eris Debug", "UCYGFAov8c5mIyKnoGu-JWng", "ERD");
	
	/* --- Constructor: --- */
	
	public DownloadManagerTest(ErisLogger logger) {
		directory = "todo";
		logger = this.logger;
	}
	
	
	/*
	 * TODO: Implement:
	 * 
	 * 1. Test Download all Videos (No Videos already downloaded)
	 * 2. Add video and download all Videos (download new videos / update Videos)
	 * 
	 * 3. Adding TestLogger (Dummy not functional) ?
	 */
	
	
	@Test
	void downLoadVideos() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void updateVideos() {
		fail("Not yet implemented");
	}
}
