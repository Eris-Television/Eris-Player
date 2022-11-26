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
	private ContentManager cm;
	
	public ContentManagerTest() {
		testChannel = TestData.CHANNEL_1;
		
		this.logger = new TestLogger(null);
	}
	
	/* --- Test --- */
	
	@Test
	void testLoadContent() throws IOException {
		PathHandler.addTestContentData();
		
		cm = new ContentManager(PathHandler.testResourceDir(), logger);
		ArrayList<Channel> testContent = cm.getChannelList();
		System.out.println(testContent.size());
		
		assertTrue(testChannel.equals(testContent.get(0)));
		// TODO check errors!
		
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
