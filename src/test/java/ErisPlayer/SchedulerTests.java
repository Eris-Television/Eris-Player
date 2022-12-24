package ErisPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

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
	void getRandomVideoTest() {
		// TODO
	}
	
	@Test
	void getScheduledVideoTest() {
		// TODO
	}
	
	@Test
	void getPathTest() {
		// TODO
	}
}
