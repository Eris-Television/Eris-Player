package ErisPlayer;

import java.net.URI;

import ErisPlayer.data.Channel;
import ErisPlayer.data.DownloadManager;

public class DownloadManagerSpy extends DownloadManager{

	public DownloadManagerSpy(URI downloadDir, ErisLogger logger) {
		super(downloadDir, logger);
	}
	
	public String getCommandLine (Channel channel) {
		return super.getCommandLine(channel);
	}
	
	public String getDate(Channel channel) {
		return super.getDate(channel);
	}
	
}
