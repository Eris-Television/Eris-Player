package erisPlayer.tests;

import java.net.URI;

import erisPlayer.ErisLogger;
import erisPlayer.data.Channel;
import erisPlayer.data.DownloadManager;

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
