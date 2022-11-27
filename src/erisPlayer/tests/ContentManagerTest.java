package erisPlayer.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import erisPlayer.ErisLogger;
import erisPlayer.PathHandler;
import erisPlayer.data.Channel;
import erisPlayer.data.ContentManager;

class ContentManagerTest {
	
	private ErisLogger logger;
	private ContentManager contentManager;
	
	/* --- Test --- */
	
	@Test
	void loadContentTests() throws IOException {
		PathHandler.addTestContentData();
		
		logger = new ErisLogger(null);
		contentManager = new ContentManager(PathHandler.testResourceDir(), logger);
		
		checkLogForErrorDetaction();
		
		ArrayList<Channel> testContent = contentManager.getChannelList();
		assertEquals(2, testContent.size(), "Unexpected number of channels.");
		
		Channel expectedChannel = testContent.get(0);
		Channel testChannel = TestData.createChannelERD();
		testChannel.addVideo(TestData.createVideoERD1());
		testChannel.addVideo(TestData.createVideoERD2());
		testChannel.addVideo(TestData.createVideoERD3());
		
		assertEquals(testChannel, expectedChannel, "Wrong channel, expect : " + expectedChannel.getName());
		
		expectedChannel = testContent.get(1);
		testChannel = TestData.createChannelERT();
		
		assertEquals(testChannel, expectedChannel, "Wrong channel, expect : " + expectedChannel.getName());
	}
	
	private void checkLogForErrorDetaction() {
		ArrayList<String> log = logger.getLog();
		
		int errors = 0;
		for(String message : log) {
			if(message.contains("ERROR")) {
				String errorMessage = message.substring(22);
				
				switch (errors) {
					case 0: {
						assertEquals("ERROR While loading [ : ] : Channel does not match the standert format.", errorMessage);
						break;
					}case 1: {
						assertEquals("ERROR While loading [NON : No Name] : Channel does not match the standert format.", errorMessage);
						break;
					}case 2: {
						assertEquals("ERROR While loading No ID[NID : ] : Channel does not match the standert format.", errorMessage);
						break;
					}case 3: {
						assertEquals("ERROR While loading No[ : Tag] : Channel does not match the standert format.", errorMessage);
						break;
					}
				}
				errors++;
			}
		}
		assertEquals(4, errors, "Incorrect number of ERRORs.");
	}
	
	@Test
	void saveContentTests() throws FileNotFoundException {
		initializeTestContent();
		
		contentManager.saveContent();
		contentManager = null;
		
		File expectedFile = new File(PathHandler.testDir().resolve("testData/saveContent.xml"));
		File testFile = new File(PathHandler.testContentData());
		
		assertTrue(compareFiles( expectedFile, testFile), "Files are not identical.");
	}
	
	private boolean compareFiles(File expectedFile, File testFile) throws FileNotFoundException {
		ArrayList<String> expectedContent = readFile(expectedFile);
		ArrayList<String> testContent = readFile(testFile);
		
		if(expectedContent.size() != testContent.size()) {
			logger.printError("Files have different length.", new Exception("Difference : "+ (testContent.size() - (expectedContent.size()-0))));
			return false;
		}
		
		int i = 0;
		for(String expected : expectedContent) {
			assertEquals(expected, testContent.get(i), "The saved content differs from the expected content in line : " + i);
			i++;
		}
		
		return true;
	}
	
	private ArrayList<String> readFile(File file) throws FileNotFoundException {
		ArrayList<String> fileContent = new ArrayList<>();
		try (Scanner fileScanner = new Scanner(file)) {
			while(fileScanner.hasNextLine()) {
				fileContent.add(fileScanner.nextLine());
			}
		}
		return fileContent;
	}
	
	
	@Test
	void removeAddTests() {
		initializeTestContent();
		
		Channel channelERD = TestData.createChannelERD();
		channelERD.addVideo(TestData.createVideoERD1());
		channelERD.addVideo(TestData.createVideoERD2());
		channelERD.addVideo(TestData.createVideoERD3());
		
		assertEquals(channelERD, contentManager.getChannelList().get(0));
		
		Channel channelERT = TestData.createChannelERT();
		
		assertEquals(channelERT, contentManager.getChannelList().get(1));
		
		
		// TODO
	}
	
	// TODO Test Download
	
	// TODO Make TestClass for testing Data Classes
	
	
	private void initializeTestContent() {
		PathHandler.removeTestContentData();
		
		logger = new ErisLogger(null);
		contentManager = new ContentManager(PathHandler.testResourceDir(), logger);
		
		contentManager.addChannel(TestData.createChannelERD());
		contentManager.addVideo(0, TestData.createVideoERD1());
		contentManager.addVideo(0, TestData.createVideoERD2());
		contentManager.addVideo(0, TestData.createVideoERD3());
		
		contentManager.addChannel(TestData.createChannelERT());
	}
	
}
