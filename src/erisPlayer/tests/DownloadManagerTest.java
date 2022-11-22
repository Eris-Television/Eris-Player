package erisPlayer.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import erisPlayer.ErisLogger;
import erisPlayer.PathHandler;
import erisPlayer.data.Channel;
import erisPlayer.data.TimeCategory;
import erisPlayer.data.Video;

class DownloadManagerTest {
	
	private final String directory;
	private ErisLogger logger;
	private DownloadMangerSpy downlaodManager;
	private Channel testChannel;
	
	private final String channelName = "Eris Debug";
	private final String channelID = "UCYGFAov8c5mIyKnoGu-JWng";
	private final String channelTag = "ERD";

	private final String defaultDate = "20150830";
	private final String testDate = "20200723";
	private final String downloadPath = "$PATH/"; // TODO
	
	private final String ytdlStart = "youtube-dl --ignore-errors -f bestvideo+bestaudio --merge-output-format mp4 --dateafter ";
	private final String output = " --output " + downloadPath + channelTag + "_%(upload_date)s_%(title)s.%(ext)s ";
	private final String ytdlEnd = output + "https://www.youtube.com/channel/" + channelID;
	
	
	/* --- Constructor: --- */
	
	public DownloadManagerTest() {
		directory = "todo"; // TODO
		this.logger = new TestLogger(null);
		this.downlaodManager = new DownloadMangerSpy(PathHandler.testDownloadDir(), logger);
	}
	
	@Test
	void testCommandLine() {
		testChannel = new Channel(channelName, channelID, channelTag);
		
		/* --- Test with empty Channel --- */
		
		String date = downlaodManager.getDate(testChannel);
		assertEquals(defaultDate, date);
		
		
		String assertCMD = ytdlStart + defaultDate + ytdlEnd;
		String cmd = downlaodManager.getCommandLine(testChannel);
		assertEquals(assertCMD, cmd);
		
		/* --- Test with Video --- */
		
		Video testVideo = new Video("Test", toLocalDate(testDate), "testFormat", TimeCategory.MEDIUM);
		testChannel.addVideo(testVideo);
		
		date = downlaodManager.getDate(testChannel);
		assertEquals(testDate, date);
		
		assertCMD = ytdlStart + testDate + ytdlEnd;
		cmd = downlaodManager.getCommandLine(testChannel);
		assertEquals(assertCMD, cmd);
		
	}
	
	private LocalDate toLocalDate(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4, 6));
		int day = Integer.valueOf(date.substring(6, 8));
		
		return LocalDate.of(year, month, day);
	}
	
	
	/*
	 * TODO: Implement:
	 * 
	 * 1. Test Download all Videos (No Videos already downloaded)
	 * 2. Add video and download all Videos (download new videos / update Videos)
	 * 
	 */
	
	
	@Test
	void downLoadVideos() {
		testChannel = new Channel(channelName, channelID, channelTag);
		
		
		
		fail("Not yet implemented");
		downlaodManager.downloadNewVideos(testChannel);
	}
	
	
	@Test
	void updateVideos() {
		testChannel = new Channel(channelName, channelID, channelTag);
		
		fail("Not yet implemented");
		downlaodManager.downloadNewVideos(testChannel);
	}
	
}
