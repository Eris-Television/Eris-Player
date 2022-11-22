package erisPlayer.tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import erisPlayer.ErisLogger;
import erisPlayer.PathHandler;
import erisPlayer.data.Channel;
import erisPlayer.data.Video;

class DownloadManagerTest {
	
	private ErisLogger logger;
	private DownloadMangerSpy downlaodManager;
	private Channel testChannel;
	
	private final String channelName = "Eris Debug";
	private final String channelID = "UCYGFAov8c5mIyKnoGu-JWng";
	private final String channelTag = "ERD";

	private final String defaultDate = "20150830";
	private final String testDate = "20200723";
	private final String downloadDir = PathHandler.uriToString(PathHandler.testDownloadDir());
	
	private final String ytdlStart = "youtube-dl --ignore-errors -f bestvideo+bestaudio --merge-output-format mp4 --dateafter ";
	private final String output = " --output \"" + downloadDir + channelTag + "_%(upload_date)s_%(duration)s_%(title)s.%(ext)s\" ";
	private final String ytdlEnd = output + "https://www.youtube.com/channel/" + channelID;
	
	
	/* --- Constructor: --- */
	
	public DownloadManagerTest() {
		this.logger = new TestLogger(null);
		this.downlaodManager = new DownloadMangerSpy(PathHandler.testDownloadDir(), logger);
	}
	
	/* --- CommandLine-Test --- */
	
	@Test
	void testCommandLine() {
		testChannel = new Channel(channelName, channelID, channelTag);
		
		/* --- Test with empty Channel --- */
		
		String date = downlaodManager.getDate(testChannel);
		assertEquals(defaultDate, date, "Incorrect date for empty channel");
		
		
		String assertCMD = ytdlStart + defaultDate + ytdlEnd;
		String cmd = downlaodManager.getCommandLine(testChannel);
		assertEquals(assertCMD, cmd, "Incorrect commandLine for empty channel");
		
		/* --- Test with Video --- */
		
		Video testVideo = new Video("Test", toLocalDate(testDate), "testFormat", -1);
		testChannel.addVideo(testVideo);
		
		date = downlaodManager.getDate(testChannel);
		assertEquals(testDate, date, "Incorrect date for channel with video");
		
		assertCMD = ytdlStart + testDate + ytdlEnd;
		cmd = downlaodManager.getCommandLine(testChannel);
		assertEquals(assertCMD, cmd, "Incorrect commandLine for channel with video");
		
	}
	
	private LocalDate toLocalDate(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4, 6));
		int day = Integer.valueOf(date.substring(6, 8));
		
		return LocalDate.of(year, month, day);
	}
	
	@Test
	void downloadVideos() throws IOException, InterruptedException {
		testChannel = new Channel(channelName, channelID, channelTag);
		
		emptyDownloadDir();
		
		downlaodManager.downloadNewVideos(testChannel);
		
		File[] downloads = new File(PathHandler.testDownloadDir()).listFiles();
		assertEquals(downloads.length, 3, "Incorrect amount of Files in testDownloadDir");
		assertEquals("ERD_20221009_13_TV DX - No Singnal #01.mp4", 	downloads[0].getName(), "Incorrect Video No. 1");
		assertEquals("ERD_20221011_10_Count Down 10 sec #01.mp4", 	downloads[1].getName(), "Incorrect Video No. 2");
		assertEquals("ERD_20221121_5_Eris Intro.mp4", 				downloads[2].getName(), "Incorrect Video No. 3");
		
	}
	
	@Test
	void updateVideos() {
		testChannel = new Channel(channelName, channelID, channelTag);
		testChannel.addVideo(new Video("testVideo", LocalDate.of(2022, 10, 10), "testFormat", 10));
		emptyDownloadDir();
		
		downlaodManager.downloadNewVideos(testChannel);
		
		File[] downloads = new File(PathHandler.testDownloadDir()).listFiles();
		assertEquals(downloads.length, 2, "Incorrect amount of Files in testDownloadDir");
		assertEquals("ERD_20221011_10_Count Down 10 sec #01.mp4", 	downloads[0].getName(), "Incorrect Video No. 2");
		assertEquals("ERD_20221121_5_Eris Intro.mp4", 				downloads[1].getName(), "Incorrect Video No. 3");
	}
	
	@After
	void emptyDownloadDir() {
		File downloadDir = new File(PathHandler.testDownloadDir());
		
		if(downloadDir.listFiles() != null) {
			for(File file : downloadDir.listFiles()) {
				file.delete();
			}
		}
	}
	
}
