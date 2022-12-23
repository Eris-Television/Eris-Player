package ErisPlayer;

import java.time.LocalDate;

import ErisPlayer.data.Channel;
import ErisPlayer.data.Video;

public class TestData {
	
	/* --- Test-Channels & Test-Videos --- */
	
	static final String CHANNEL_ERD_NAME = "Eris-Debug";
	static final String CHANNEL_ERD_ID 	 = "UCYGFAov8c5mIyKnoGu-JWng";
	static final String CHANNEL_ERD_TAG  = "ERD";
	
	public static Channel createChannelERD() { return new Channel(CHANNEL_ERD_NAME, CHANNEL_ERD_ID, CHANNEL_ERD_TAG); }
	
	static final String VIDEO_ERD_1_NAME 			= "TV DX - No Singnal #01";
	static final LocalDate VIDEO_ERD_1_UPLOADDATE 	= LocalDate.of(2022, 10, 9);
	static final LocalDate VIDEO_UPDATE_DATE 	= LocalDate.of(2022, 10, 10);
	static final int VIDEO_ERD_1_PLAYTIME 			= 13;
	
	public static Video createVideoERD1() { return new Video(VIDEO_ERD_1_NAME, VIDEO_ERD_1_UPLOADDATE, VIDEO_ERD_1_PLAYTIME); }
	public static Video createVideoUpdate() { return new Video(VIDEO_ERD_1_NAME, VIDEO_UPDATE_DATE, VIDEO_ERD_1_PLAYTIME); }

	private static final String VIDEO_ERD_2_NAME 			= "Count Down 10 sec #01";
	private static final LocalDate VIDEO_ERD_2_UPLOADDATE 	= LocalDate.of(2022, 10, 11);
	private static final int VIDEO_ERD_2_PLAYTIME 			= 10;
	
	public static Video createVideoERD2() { return new Video(VIDEO_ERD_2_NAME, VIDEO_ERD_2_UPLOADDATE, VIDEO_ERD_2_PLAYTIME); }
	
	private static final String VIDEO_ERD_3_NAME 			= "Eris Intro";
	private static final LocalDate VIDEO_ERD_3_UPLOADDATE 	= LocalDate.of(2022, 11, 21);
	private static final int VIDEO_ERD_3_PLAYTIME 			= 5;
	
	public static Video createVideoERD3() { return new Video(VIDEO_ERD_3_NAME, VIDEO_ERD_3_UPLOADDATE, VIDEO_ERD_3_PLAYTIME); }
	
	private static final String CHANNEL_ERT_NAME = "Eris-Television";
	private static final String CHANNEL_ERT_ID 	 = "UCJ4rRW11hiG4O8UavtusGuA";
	private static final String CHANNEL_ERT_TAG  = "ERT";
	
	public static Channel createChannelERT() { return new Channel(CHANNEL_ERT_NAME, CHANNEL_ERT_ID, CHANNEL_ERT_TAG); }
	
	/* --- Download Test-Data --- */
	
	public static final String defaultDate = "20150830";
	
	public static final String testDate = "20200723";
	public static final String downloadDir = PathHandler.uriToString(PathHandler.testDownloadDir());
	
	public static final String output = " --output \"" + downloadDir + CHANNEL_ERD_TAG + "_%(upload_date)s_%(duration)s_%(title)s.%(ext)s\" ";
	public static final String ytdlStart = "youtube-dl --ignore-errors -f bestvideo+bestaudio --merge-output-format mp4 --dateafter ";
	public static final String ytdlEnd = output + "https://www.youtube.com/channel/" + CHANNEL_ERD_ID;
	
	/* --- Scheduler Test-Data --- */
	
	private static final String DE = ErisScheduler.DEFAULT_ENTRY;
	private static final String TE = SchedulerSpy.TEST_ENTRY;
	
	public static final String[][] SCHEDULE_DATA = {
			{
				"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", 
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
				"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
				"18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"
			}, {
				DE, TE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, TE, DE
			}, {
				"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", 
				"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
				"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
				"18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"
			}
	};
	
	public static final String[][] SCHEDULE_EMPTY = {
			{
				DE, TE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, TE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE
			}, {
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE,
				DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, DE, TE, DE
			}
	};
	
	
}