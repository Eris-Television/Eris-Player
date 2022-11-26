package erisPlayer.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

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
	void testLoadContent() throws IOException {
		PathHandler.addTestContentData();
		
		logger = new TestLogger(null);
		contentManager = new ContentManager(PathHandler.testResourceDir(), logger);
		
		checkLogForErrorDetaction();
		
		ArrayList<Channel> testContent = contentManager.getChannelList();
		assertEquals(2, testContent.size(), "Unexpected number of channels.");
		
		Channel checkChannel = testContent.get(0);
		Channel testChannel = TestData.CHANNEL_ERD;
		testChannel.addVideo(TestData.VIDEO_ERD_1);
		testChannel.addVideo(TestData.VIDEO_ERD_2);
		testChannel.addVideo(TestData.VIDEO_ERD_3);
		
		assertEquals(testChannel, checkChannel, "Wrong channel, expect : " + checkChannel.getName());
		
		checkChannel = testContent.get(1);
		testChannel = TestData.CHANNEL_ERT;
		
		assertEquals(testChannel, checkChannel, "Wrong channel, expect : " + checkChannel.getName());
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
	
	/*
	@Test
	void testSaveContent() {
		PathHandler.removeTestContentData();
		// TODO
		fail();
	}
	
	@Test
	void testRemoveAdd() {
		// TODO
		fail();
	}
	
	// TODO Add Channel videoLists
	// TODO Test Download
	// TODO Implementing use of TestLogger
	
	// TODO Make TestClass for testing Data Classes
	
	*/
	
}
