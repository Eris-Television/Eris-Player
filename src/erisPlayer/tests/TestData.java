package erisPlayer.tests;

import java.time.LocalDate;

import erisPlayer.PathHandler;
import erisPlayer.data.Channel;
import erisPlayer.data.Video;

public class TestData {
	
	/* --- Test-Channels & Test-Videos --- */
	
	private static final String CHANNEL_ERD_NAME = "Eris-Debug";
	private static final String CHANNEL_ERD_ID 	 = "UCYGFAov8c5mIyKnoGu-JWng";
	private static final String CHANNEL_ERD_TAG  = "ERD";
	
	public static final Channel CHANNEL_1 = new Channel(CHANNEL_ERD_NAME, CHANNEL_ERD_ID, CHANNEL_ERD_TAG);
	
	private static final String VIDEO_ERD_1_NAME 			= "TV DX - No Singnal #01";
	private static final LocalDate VIDEO_ERD_1_UPLOADDATE 	= LocalDate.of(2022, 10, 10);
	private static final int VIDEO_ERD_1_PLAYTIME 			= 13;
	
	public static final Video VIDEO_ERD_1 = new Video(VIDEO_ERD_1_NAME, VIDEO_ERD_1_UPLOADDATE, VIDEO_ERD_1_PLAYTIME);

	private static final String VIDEO_ERD_2_NAME 			= "Count Down 10 sec #01";
	private static final LocalDate VIDEO_ERD_2_UPLOADDATE 	= LocalDate.of(2022, 10, 11);
	private static final int VIDEO_ERD_2_PLAYTIME 			= 10;
	
	public static final Video VIDEO_ERD_2 = new Video(VIDEO_ERD_2_NAME, VIDEO_ERD_2_UPLOADDATE, VIDEO_ERD_2_PLAYTIME);
	
	private static final String VIDEO_ERD_3_NAME 			= "Eris Intro";
	private static final LocalDate VIDEO_ERD_3_UPLOADDATE 	= LocalDate.of(2022, 11, 22);
	private static final int VIDEO_ERD_3_PLAYTIME 			= 5;
	
	public static final Video VIDEO_ERD_3 = new Video(VIDEO_ERD_3_NAME, VIDEO_ERD_3_UPLOADDATE, VIDEO_ERD_3_PLAYTIME);
	
	private static final String CHANNEL_ERT_NAME = "Eris-Television";
	private static final String CHANNEL_ERT_ID 	 = "UCJ4rRW11hiG4O8UavtusGuA";
	private static final String CHANNEL_ERT_TAG  = "ERR";
	
	public static final Channel Channel_ERT = new Channel(CHANNEL_ERT_NAME, CHANNEL_ERT_ID, CHANNEL_ERT_TAG);
	
	/* --- Download Test-Data --- */
	
	public static final String defaultDate = "20150830";
	
	public static final String testDate = "20200723";
	public static final String downloadDir = PathHandler.uriToString(PathHandler.testDownloadDir());
	
	public static final String output = " --output \"" + downloadDir + CHANNEL_ERD_TAG + "_%(upload_date)s_%(duration)s_%(title)s.%(ext)s\" ";
	public static final String ytdlStart = "youtube-dl --ignore-errors -f bestvideo+bestaudio --merge-output-format mp4 --dateafter ";
	public static final String ytdlEnd = output + "https://www.youtube.com/channel/" + CHANNEL_ERD_ID;
	
}