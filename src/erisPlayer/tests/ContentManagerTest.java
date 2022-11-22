package erisPlayer.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
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
		testChannel = new Channel(TestData.channelName, TestData.channelID, TestData.channelTag);
		
		this.logger = new TestLogger(null);
		this.cm = new ContentManager(PathHandler.testDownloadDir(), logger);
	}
	
	/* --- Test --- */
	
	@Before
	@Test
	void testLoadContent() {
		cm.loadContent();
		ArrayList<Channel> cmChannels = cm.getChannelList();
		
		assertTrue(cmChannels.size() == 1);
		assertTrue(testChannel.equals(cmChannels.get(0)));
	}
	
	@Test
	void testRemoveAdd() {
		cm.removeChannel(0);
		assertTrue(cm.getChannelList().isEmpty());
		cm.addChannel(testChannel);
		assertTrue(cm.getChannelList().size() == 1);
	}
	
	// TODO Add Channel videoLists
	// TODO Test Download
	// TODO Implementing use of TestLogger
	
	@After
	@Test
	void testSave() {
		cm.saveContent();
		assertTrue(true);
	}

}
