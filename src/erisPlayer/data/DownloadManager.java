package erisPlayer.data;

import java.io.IOException;

// import org.mp4parser.IsoFile;

import erisPlayer.ErisLogger;

public class DownloadManager {
	
	private String directory;
	
	private ErisLogger logger;
	
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
	
	@SuppressWarnings("unused") // TODO
	protected String getCommandLine(Channel channel) {
		
		String command = "youtube-dl ";
		
		String ignoreErrors = "--ignore-errors";
		
		String format = "-f bestvideo+bestaudio --merge-output-format mp4";
		
		String downloadAfter = "--dateafter " + getDate(channel);
		
		String output = "--output " +"$PATH/"+ channel.getTag() + "_%(upload_date)s_%(title)s.%(ext)s";
		
		String videoUrl = "https://www.youtube.com/channel/" + channel.getChanalID();
		return null;
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
