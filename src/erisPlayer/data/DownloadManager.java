package erisPlayer.data;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

import erisPlayer.ErisLogger;

public class DownloadManager {
	
	private String directory;
	
	private ErisLogger logger;
	
	public DownloadManager(String resourceDir, ErisLogger logger) {
		this.directory = resourceDir +"Downloads/";
		this.logger = logger;
	}
	
	public void downloadNewVideos(Channel channel) {
		String videoUrl = "https://www.youtube.com/channel/" + channel.getChanalID();
		String output = channel.getTag() + "_%(upload_date)s_%(title)s.%(ext)s";
		
		YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
		request.setOption("ignore-errors");
		request.setOption("f", "best");
		request.setOption("dateafter", getDate(channel));
		request.setOption("output", output);
		
		
		try {
			// Make request and return response
			YoutubeDLResponse response = YoutubeDL.execute(request);
			System.out.println("request end");
			// Response
			String stdOut = response.getOut(); // Executable output
			System.out.println(stdOut);
		} catch (YoutubeDLException e) {
			logger.printError("DownloadManger, can't download : " + channel.getChanalID() + "[%s]" + channel.getChanalID(), e);
		}
		
	}
	
	public String getDate(Channel channel) {
		try {
			return channel.getLastUpload().getUploadDate().toString();
		}catch (IndexOutOfBoundsException e) {
			return "20160101";
		}
	}
	
	
	public void debug(Channel channel) {
		System.out.println("DownloadMangaer : Debug ...");
		
		String videoUrl = "https://www.youtube.com/channel/" + channel.getChanalID();
		String output = channel.getTag() + "_%(upload_date)s_%(title)s.%(ext)s";
		
		System.out.println(videoUrl);
		System.out.println(output);
		System.out.println(directory);
		System.out.println(getDate(channel));
	}
	
	/*/ Test-Main
	private void main() throws YoutubeDLException {
		// Video url to download
		String videoUrl = "https://www.youtube.com/channel/UC0RBJwg8ZfRM8TLGOKTpArA";

		// Build request
		YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
		request.setOption("ignore-errors");		// --ignore-errors
		request.setOption("f", "best");
		request.setOption("dateafter", 20220830);
		request.setOption("output", "HRZ_%(upload_date)s_%(title)s.%(ext)s");	// --output "%(id)s"
		
		
		// Make request and return response
		YoutubeDLResponse response = YoutubeDL.execute(request);
		System.out.println("request end");
		// Response
		String stdOut = response.getOut(); // Executable output
		System.out.println(stdOut);
	}
	
	public static void main(String[] args) throws YoutubeDLException {
		DownloadManager run = new DownloadManager("D:\\", new ErisLogger(""));
		run.main();
	}*/
	
	
}
