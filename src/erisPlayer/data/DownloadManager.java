package erisPlayer.data;

import java.io.IOException;
import erisPlayer.ErisLogger;

public class DownloadManager {
	
	private String directory;
	
	private ErisLogger logger;
	
	/* --- Static YouTube-DL parameters --- */
	private final String ignoreErrors = "--ignore-errors ";
	private final String format = "-f bestvideo+bestaudio --merge-output-format mp4 ";
	
	private final String staticParameters = ignoreErrors + format;
	
	private final String youtubeURL = "https://www.youtube.com/channel/";
	
	
	/* --- Constructor --- */
	
	public DownloadManager(String resourceDir, ErisLogger logger) {
		this.directory = resourceDir +"Downloads/";
		// TODO: downloadDir : to Windows / linux path
		this.logger = logger;
	}
	
	public void downloadNewVideos(Channel channel) {
		String comandLine = "cmd /c " + getCommandLine(channel);
		
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(comandLine);
		}catch (IOException e) {
			logger.printError("While downlaoding Videos from : " + channel.getTag(), e);
			return;
		}
		
		logger.printSubline("Donwloaded new Video from : [" + channel.getTag() +"] : "+ channel.getName());
	}
	
	protected String getCommandLine(Channel channel) {
		String downloadAfter = "--dateafter " + getDate(channel);
		String output = " --output " +"$PATH/"+ channel.getTag() + "_%(upload_date)s_%(title)s.%(ext)s ";
		
		String commandLine = "youtube-dl " + staticParameters 
							+ downloadAfter + output 
							+ youtubeURL + channel.getChanalID();
		
		return commandLine;
	}
	
	protected String getDate(Channel channel) {
		try {
			return channel.getLastUpload().getUploadDate().toString().replace("-", "");
		}catch (IndexOutOfBoundsException e) {
			return "20150830";
		}
	}

	public void debug(Channel channel) { // TODO: Remove after not longer used for Debugging
		System.out.println("DownloadMangaer : Debug ...");
		
		String videoUrl = "https://www.youtube.com/channel/" + channel.getChanalID();
		String output = channel.getTag() + "_%(upload_date)s_%(title)s.%(ext)s";
		
		System.out.println(videoUrl);
		System.out.println(output);
		System.out.println(directory);
		System.out.println(getDate(channel));
	}
	
}
