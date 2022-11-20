package erisPlayer.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import erisPlayer.ErisLogger;
import erisPlayer.data.Channel;

class DownloadManagerTest {
	
	private final String directory;
	private ErisLogger logger;
	private DownloadMangerSpy downlaodManager;
	private Channel testChannel = new Channel("Eris Debug", "UCYGFAov8c5mIyKnoGu-JWng", "ERD");
	
	/* --- Constructor: --- */
	
	public DownloadManagerTest() {
		directory = "todo";
		this.logger = new TestLogger(null);
		this.downlaodManager = new DownloadMangerSpy(directory, logger);
	}
	
	@BeforeAll
	@Test
	void testCommandLine() {
		
	}
	
	
	/*
	 * TODO: Implement:
	 * 
	 * 0. Implement CommandLine-Test
	 * 
	 * 1. Test Download all Videos (No Videos already downloaded)
	 * 2. Add video and download all Videos (download new videos / update Videos)
	 * 
	 */
	
	
	@Test
	void downLoadVideos() {
		downlaodManager.downloadNewVideos(testChannel);
		fail("Not yet implemented");
	}
	
	
	@Test
	void updateVideos() {
		downlaodManager.downloadNewVideos(testChannel);
		fail("Not yet implemented");
	}
}
