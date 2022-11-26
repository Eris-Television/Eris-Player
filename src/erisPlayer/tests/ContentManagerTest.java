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
	
	private Channel testChannel;
	
	private ErisLogger logger;
	private ContentManager contentManager;
	
	public ContentManagerTest() {
		testChannel = TestData.CHANNEL_ERD;
	}
	
	/* --- Test --- */
	
	@Test
	void testLoadContent() throws IOException {
		logger = new TestLogger(null);
		PathHandler.addTestContentData();
		
		contentManager = new ContentManager(PathHandler.testResourceDir(), logger);
		ArrayList<Channel> testContent = contentManager.getChannelList();
		
		checkLogForErrorDetaction();
		
		
		
		// TODO
		assertTrue(testChannel.equals(testContent.get(0)));
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
						assertEquals("ERROR While loading [NoN : NO NAME] : Channel does not match the standert format.", errorMessage);
						break;
					}case 2: {
						assertEquals("ERROR While loading No ID[NID : ] : Channel does not match the standert format.", errorMessage);
						break;
					}case 3: {
						assertEquals("ERROR While loading No[ : TAG] : Channel does not match the standert format.", errorMessage);
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
