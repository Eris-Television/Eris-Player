package ErisPlayer;

import java.io.File;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ErisPlayer.data.Channel;
import ErisPlayer.data.Video;

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
	void downloadVideoTests() {
		Channel testChannel = TestData.createChannelERD();
		DownloadManagerSpy downloadManager = new DownloadManagerSpy(PathHandler.testDownloadDir(), new ErisLogger(null));
		PathHandler.emptyTestResources();
		
		downloadManager.downloadVideos(testChannel);
		checkDownloads();
	}
	
	public static void checkDownloads() {
		// TODO FIX
		
		File[] downloads = new File(PathHandler.testResourceDir().resolve("ERD/")).listFiles(); // TODO Check Change was : .testDownloadDir()
		assertEquals(3, downloads.length, "Incorrect amount of Files in testDownloadDir");
		assertEquals("ERD" + TestData.createVideoERD1().toFileName(), downloads[0].getName(), "Incorrect Video No. 1");
		assertEquals("ERD" + TestData.createVideoERD2().toFileName(), downloads[1].getName(), "Incorrect Video No. 2");
		assertEquals("ERD" + TestData.createVideoERD3().toFileName(), downloads[2].getName(), "Incorrect Video No. 3");
	}
	
	@Test
	void updateVideoTests() {
		Channel testChannel = TestData.createChannelERD();
		testChannel.addVideo(TestData.createVideoUpdate());
		DownloadManagerSpy downloadManager = new DownloadManagerSpy(PathHandler.testDownloadDir(), new ErisLogger(null));
		PathHandler.emptyTestResources();
		
		downloadManager.downloadVideos(testChannel);
		checkUpdates();
	}
	
	public static void checkUpdates() {
		File[] downloads = new File(PathHandler.testResourceDir().resolve("ERD/")).listFiles();
		assertEquals(downloads.length, 2, "Incorrect amount of Files in testDownloadDir");
		assertEquals("ERD" + TestData.createVideoERD2().toFileName(), downloads[0].getName(), "Incorrect Video No. 2");
		assertEquals("ERD" + TestData.createVideoERD3().toFileName(), downloads[1].getName(), "Incorrect Video No. 3");
	}
}
