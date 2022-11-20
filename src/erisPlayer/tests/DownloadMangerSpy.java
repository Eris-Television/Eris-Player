package erisPlayer.tests;

import erisPlayer.ErisLogger;
import erisPlayer.data.Channel;
import erisPlayer.data.DownloadManager;

public class DownloadMangerSpy extends DownloadManager{

	public DownloadMangerSpy(String resourceDir, ErisLogger logger) {
		super(resourceDir, logger);
	}
	
	public String getCommandLine (Channel channel) {
		return super.getCommandLine(channel);
	}
	
	public String getDate(Channel channel) {
		return super.getDate(channel);
	}
	
}
