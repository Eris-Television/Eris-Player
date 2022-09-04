package erisPlayer.data;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

public class DownloadManager {
	
	private String directory;
	
	public DownloadManager(String localDir) {
		this.directory = localDir +"";
	}
	
	public void getNewChannelDownloads(Channel channel) {
		String videoUrl = "https://www.youtube.com/channel/" + channel.getChanalID();
		String outputUrl = channel.getTag() + "_%(upload_date)s_%(title)s.%(ext)s";
		
		
		
		YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
		request.setOption("ignore-errors");
		request.setOption("f", "best");
		request.setOption("dateafter", 20220830);
	}
	
	public String getDate(Channel channel) {
		return channel.getLastUpload().getUploadDate().toString();
	}
	
	
	
	
	// Test-Main
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
		DownloadManager run = new DownloadManager("D:\\");
		run.main();
	}
	
	
}
