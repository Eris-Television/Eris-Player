package erisPlayer.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import erisPlayer.ErisLogger;
import erisPlayer.PathHandler;

public class DownloadManager {
	
	private String directory;
	
	private ErisLogger logger;
	
	/* --- Static YouTube-DL parameters --- */
	private final String ignoreErrors = "--ignore-errors ";
	private final String format = "-f bestvideo+bestaudio --merge-output-format mp4 ";
	private final String staticParameters = ignoreErrors + format;
	private final String youtubeURL = "https://www.youtube.com/channel/";
	
	/* --- Constructor --- */
	
	public DownloadManager(URI downloadDir, ErisLogger logger) {
		this.directory = PathHandler.uriToString(downloadDir.resolve("Downloads/"));
		this.logger = logger;
	}
	
	/* --- Download functions --- */
	
	public void downloadNewVideos(Channel channel) {
		String comandLine = "cmd /c " + getCommandLine(channel);
		
		try {
			Process process = Runtime.getRuntime().exec(comandLine);
			
			BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new 
				     InputStreamReader(process.getErrorStream()));
			
			String s = null;
			while ((s = stdInput.readLine()) != null) {
//			    System.out.println(s);
			}

			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}
			
			process.waitFor();
		}catch (IOException | InterruptedException e) {
			System.out.println("Error");
			logger.printError("While downlaoding Videos from : " + channel.getTag(), e);
			return;
		}
		
		logger.printSubline("Updated Videos from : [" + channel.getTag() +"] : "+ channel.getName());
	}
	
	protected String getCommandLine(Channel channel) {
		String downloadAfter = "--dateafter " + getDate(channel);
		String output = " --output \"" +directory+ channel.getTag() + "_%(upload_date)s_%(duration)s_%(title)s.%(ext)s\" ";
		
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

}
