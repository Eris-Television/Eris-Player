package erisPlayer.data;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

public class DownloadManager {
	
	
	
	
	
	// Test-Main
	
	private void main() throws YoutubeDLException {
		// Video url to download
		String videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

		// Destination directory
		String directory = "D:\\";

		// Build request
		YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
		request.setOption("ignore-errors");		// --ignore-errors
		request.setOption("output", "%(id)s");	// --output "%(id)s"
		request.setOption("retries", 10);		// --retries 10

		// Make request and return response
		YoutubeDLResponse response = YoutubeDL.execute(request);

		// Response
		String stdOut = response.getOut(); // Executable output
		System.out.println(stdOut);
	}
	
	public static void main(String[] args) throws YoutubeDLException {
		DownloadManager run = new DownloadManager();
		run.main();
	}
	
	
}
