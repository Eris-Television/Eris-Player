package erisPlayer.tests;

import erisPlayer.PathHandler;

public class TestData {
	
	public static final String channelName = "Eris Debug";
	public static final String channelID = "UCYGFAov8c5mIyKnoGu-JWng";
	public static final String channelTag = "ERD";

	public static final String defaultDate = "20150830";
	
	public static final String testDate = "20200723";
	public static final String downloadDir = PathHandler.uriToString(PathHandler.testDownloadDir());
	
	public static final String output = " --output \"" + downloadDir + channelTag + "_%(upload_date)s_%(duration)s_%(title)s.%(ext)s\" ";
	public static final String ytdlStart = "youtube-dl --ignore-errors -f bestvideo+bestaudio --merge-output-format mp4 --dateafter ";
	public static final String ytdlEnd = output + "https://www.youtube.com/channel/" + channelID;
	
}
