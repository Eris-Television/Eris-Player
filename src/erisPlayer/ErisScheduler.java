package erisPlayer;

public class ErisScheduler {
	
	private String localDir;
	private String videoDir;
	
	public ErisScheduler(String localDir) {
		this.localDir = localDir;
		this.videoDir = localDir +"videos/";
	}
	
	public String getNextVideoPath() {
		return videoDir+"Not implemented yet!";
	}
	
}
