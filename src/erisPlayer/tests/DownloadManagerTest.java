package erisPlayer.tests;

import java.io.File;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import erisPlayer.ErisDateTimer;
import erisPlayer.ErisLogger;
import erisPlayer.PathHandler;
import erisPlayer.data.Channel;
import erisPlayer.data.Video;

class DownloadManagerTest {
	
	/* --- CommandLine-Test --- */
	
	@Test
	void commandLineTests() {
		Channel testChannel = TestData.createChannelERD();
		DownloadManagerSpy downloadManager = new DownloadManagerSpy(PathHandler.testDownloadDir(), new ErisLogger(null));
		
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
		DownloadManagerSpy downloadManager = new DownloadManagerSpy(PathHandler.testDownloadDir(), new ErisLogger(null));
		PathHandler.emptyTestDownloadDir();
		
		downloadManager.downloadNewVideos(testChannel);
		
		File[] downloads = new File(PathHandler.testDownloadDir()).listFiles();
		assertEquals(3, downloads.length, "Incorrect amount of Files in testDownloadDir");
		assertEquals("ERD" + TestData.createVideoERD1().toFileName(), downloads[0].getName(), "Incorrect Video No. 1");
		assertEquals("ERD" + TestData.createVideoERD2().toFileName(), downloads[1].getName(), "Incorrect Video No. 2");
		assertEquals("ERD" + TestData.createVideoERD3().toFileName(), downloads[2].getName(), "Incorrect Video No. 3");
		
	}
	
	@Test
	void updateVideos() {
		Channel testChannel = TestData.createChannelERD();
		testChannel.addVideo(new Video("testVideo", LocalDate.of(2022, 10, 10), 10));
		DownloadManagerSpy downloadManager = new DownloadManagerSpy(PathHandler.testDownloadDir(), new ErisLogger(null));
		PathHandler.emptyTestDownloadDir();
		
		downloadManager.downloadNewVideos(testChannel);
		
		File[] downloads = new File(PathHandler.testDownloadDir()).listFiles();
		assertEquals(downloads.length, 2, "Incorrect amount of Files in testDownloadDir");
		assertEquals("ERD" + TestData.createVideoERD2().toFileName(), downloads[0].getName(), "Incorrect Video No. 2");
		assertEquals("ERD" + TestData.createVideoERD3().toFileName(), downloads[1].getName(), "Incorrect Video No. 3");
	}
	
}
