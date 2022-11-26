package erisPlayer.tests;

import java.io.File;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import erisPlayer.ErisDateTimer;
import erisPlayer.PathHandler;
import erisPlayer.data.Channel;
import erisPlayer.data.Video;

class DownloadManagerTest {
	
	/* --- CommandLine-Test --- */
	
	@Test
	void commandLineTests() {
		Channel testChannel = TestData.createChannelERD();
		System.out.println(testChannel.hashCode());
		DownloadManagerSpy downloadManager = new DownloadManagerSpy(PathHandler.testDownloadDir(), new TestLogger(null));
		
		/* --- Test with empty Channel --- */
		
		String date = downloadManager.getDate(testChannel);
		assertEquals(TestData.defaultDate, date, "Incorrect date for empty channel");
		
		
		String assertCMD = TestData.ytdlStart + TestData.defaultDate + TestData.ytdlEnd;
		String cmd = downloadManager.getCommandLine(testChannel);
		assertEquals(assertCMD, cmd, "Incorrect commandLine for empty channel");
		
		/* --- Test with Video --- */
		
		Video testVideo = new Video("Test", ErisDateTimer.toLocalDate(TestData.testDate), -1);
		testChannel.addVideo(testVideo);
		
		date = downloadManager.getDate(testChannel);
		assertEquals(TestData.testDate, date, "Incorrect date for channel with video");
		
		assertCMD = TestData.ytdlStart + TestData.testDate + TestData.ytdlEnd;
		cmd = downloadManager.getCommandLine(testChannel);
		assertEquals(assertCMD, cmd, "Incorrect commandLine for channel with video");
	}
	
	@Test
	void downloadVideos() {
		Channel testChannel = TestData.createChannelERD();
		System.out.println(testChannel.hashCode());
		DownloadManagerSpy downloadManager = new DownloadManagerSpy(PathHandler.testDownloadDir(), new TestLogger(null));
		PathHandler.emptyTestDownloadDir();
		
		downloadManager.downloadNewVideos(testChannel);
		
		File[] downloads = new File(PathHandler.testDownloadDir()).listFiles();
		assertEquals(downloads.length, 3, "Incorrect amount of Files in testDownloadDir");
		assertEquals("ERD_20221009_13_TV DX - No Singnal #01.mp4", 	downloads[0].getName(), "Incorrect Video No. 1");
		assertEquals("ERD_20221011_10_Count Down 10 sec #01.mp4", 	downloads[1].getName(), "Incorrect Video No. 2");
		assertEquals("ERD_20221121_5_Eris Intro.mp4", 				downloads[2].getName(), "Incorrect Video No. 3");
		
	}
	
	@Test
	void updateVideos() {
		Channel testChannel = TestData.createChannelERD();
		System.out.println(testChannel.hashCode());
		testChannel.addVideo(new Video("testVideo", LocalDate.of(2022, 10, 10), 10));
		DownloadManagerSpy downloadManager = new DownloadManagerSpy(PathHandler.testDownloadDir(), new TestLogger(null));
		PathHandler.emptyTestDownloadDir();
		
		downloadManager.downloadNewVideos(testChannel);
		
		File[] downloads = new File(PathHandler.testDownloadDir()).listFiles();
		assertEquals(downloads.length, 2, "Incorrect amount of Files in testDownloadDir");
		assertEquals("ERD_20221011_10_Count Down 10 sec #01.mp4", 	downloads[0].getName(), "Incorrect Video No. 2");
		assertEquals("ERD_20221121_5_Eris Intro.mp4", 				downloads[1].getName(), "Incorrect Video No. 3");
	}
	
}
