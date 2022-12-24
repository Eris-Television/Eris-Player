package ErisPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import ErisPlayer.data.Channel;
import ErisPlayer.data.Video;

class SchedulerTests {
	
	@Test
	void emptyScheduleTest() throws IOException {
		PathHandler.emptyTestResources();
		SchedulerSpy scheduler = new SchedulerSpy(PathHandler.testResourceDir(), new ErisLogger(null));
		
		String[][] assertData = TestData.SCHEDULE_EMPTY;
		String[][] testData = scheduler.getSchedule();
		
		compareSchedule(assertData, testData);
	}

	@Test
	void loadScheduleTest() throws IOException {
		PathHandler.addTestSchedule();
		SchedulerSpy scheduler = new SchedulerSpy(PathHandler.testResourceDir(), new ErisLogger(null));
		
		String[][] assertData = TestData.SCHEDULE_DATA;
		String[][] testData = scheduler.getSchedule();
		
		compareSchedule(assertData, testData);
	}
	
	private void compareSchedule(String[][] assertData, String[][] testData) {
		assertEquals(assertData.length, testData.length, "The schedule does not contain the correct number of entries : " + assertData.length);
		assertEquals(assertData[0].length, testData[0].length, "The schedule does not contain the correct number of entries : " + assertData[0].length);

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 48; j++) {
				String assertString = assertData[i][j];
				String testString = testData[i][j];
				
				assertEquals(assertString, testString, "The schedule does not contain the correct content at : ["+ i +"]["+ j +"]");
			}
		}
	}
	
	@Test
	void getScheduledVideoTest() {
		// TODO
	}
	
	@Test
	void getRandomVideoTest() throws IOException {
		PathHandler.addTestScheduleContent();
		SchedulerSpy scheduler = new SchedulerSpy(PathHandler.testResourceDir(), new ErisLogger(null));
		
		Channel testChannel = TestData.createChannelERD();
		Video testVideo_1 = TestData.createVideoERD1();
		Video testVideo_2 = TestData.createVideoERD2();
		
		String assertPath_1 = scheduler.getPath(testChannel, testVideo_1);
		String assertPath_2 = scheduler.getPath(testChannel, testVideo_2);
		
		for(int i = 0; i < 10; i++) {
			String testPath = scheduler.getRandomVideo();
			
			if(testPath.equals(assertPath_1) || testPath.equals(assertPath_2)) {
				fail("Incorrect Random-Video : "+ testPath);
			}
		}
	}
	
	@Test
	void getPathTest() { // TODO Move to PathHandler-Tests
		SchedulerSpy scheduler = new SchedulerSpy(PathHandler.testResourceDir(), new ErisLogger(null));
		
		Channel testChannel = TestData.createChannelERD();
		Video testVideo = TestData.createVideoERD1();
		
		String testPath = scheduler.getPath(testChannel, testVideo);
		String assertPath = TestData.VIDEO_PATH;
		
		assertEquals(assertPath, testPath, "Incorrect video path");
	}
}
