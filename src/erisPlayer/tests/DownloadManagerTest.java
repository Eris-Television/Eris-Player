package erisPlayer.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import erisPlayer.ErisLogger;
import erisPlayer.data.Channel;
import erisPlayer.data.TimeCategory;
import erisPlayer.data.Video;

class DownloadManagerTest {
	
	private final String directory;
	private ErisLogger logger;
	private DownloadMangerSpy downlaodManager;
	private Channel testChannel;
	
	/* --- Constructor: --- */
	
	public DownloadManagerTest() {
		directory = "todo";
		this.logger = new TestLogger(null);
		this.downlaodManager = new DownloadMangerSpy(directory, logger);
	}
	
	@Test
	void testCommandLine() {
		testChannel = new Channel("Eris Debug", "UCYGFAov8c5mIyKnoGu-JWng", "ERD");
		
		String date = downlaodManager.getDate(testChannel);
		assertEquals(date, "20150830");
		System.out.println(date);
		
		String cmd = downlaodManager.getDate(testChannel);
		System.out.println(cmd);
		
		Video testVideo = new Video("Test", LocalDate.of(2020, 07, 23), "testFormat", TimeCategory.MEDIUM);
		testChannel.addVideo(testVideo);
		
		date = downlaodManager.getDate(testChannel);
		System.out.println(date);
		
		cmd = downlaodManager.getDate(testChannel);
		System.out.println(cmd);
		
		
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
		testChannel = new Channel("Eris Debug", "UCYGFAov8c5mIyKnoGu-JWng", "ERD");
		
		downlaodManager.downloadNewVideos(testChannel);
		fail("Not yet implemented");
	}
	
	
	@Test
	void updateVideos() {
		testChannel = new Channel("Eris Debug", "UCYGFAov8c5mIyKnoGu-JWng", "ERD");
		downlaodManager.downloadNewVideos(testChannel);
		fail("Not yet implemented");
	}
}
