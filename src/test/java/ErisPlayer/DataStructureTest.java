package ErisPlayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ErisPlayer.data.Channel;
import ErisPlayer.data.Video;

class DataStructureTest {
	
	/* --- Videos --- */
	
	@Test
	void testVideoEquals() {
		Video testVideo_1 = TestData.createVideoERD1();
		Video testVideo_2 = new Video(TestData.VIDEO_ERD_1_NAME, TestData.VIDEO_ERD_1_UPLOADDATE, TestData.VIDEO_ERD_1_PLAYTIME);
		Video testVideo_false = TestData.createVideoERD2();
		
		assertTrue(testVideo_1.equals(testVideo_2), "Videos with equal data are not equal");
		assertEquals(testVideo_1, testVideo_2, "Videos with equal data are not equal");
		
		assertFalse(testVideo_1.equals(testVideo_false), "Videos with differnt data are equal");
	}
	
	/* --- Channel --- */
	
	@Test
	void testChannelEquals() {
		Channel testChannel_1 = TestData.createChannelERD();
		Channel testChannel_2 = new Channel(TestData.CHANNEL_ERD_NAME, TestData.CHANNEL_ERD_ID, TestData.CHANNEL_ERD_TAG);
		Channel testChannel_false = TestData.createChannelERT();
		
		assertTrue(testChannel_1.equals(testChannel_2), "Channels with equal data are not equal");
		assertEquals(testChannel_1, testChannel_2, "Channels with equal data are not equal");
		testChannel_2.addVideo(TestData.createVideoERD1());
		
		assertFalse(testChannel_1.equals(testChannel_2), "Channel with differnt data are equal");
		assertFalse(testChannel_1.equals(testChannel_false), "Channel with differnt data are equal");
	}
	
	// TODO Add & Remove ... (maybe)
}
