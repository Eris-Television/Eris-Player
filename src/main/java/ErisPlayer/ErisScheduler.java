package ErisPlayer;

import java.io.File;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import ErisPlayer.data.Channel;
import ErisPlayer.data.ContentManager;
import ErisPlayer.data.Video;

public class ErisScheduler {

	private final String SD = "[Scheduler]";

	private LocalDateTime now;
	private URI resourceDir;
	protected String[][] schedule;
	public static final String DEFAULT_ENTRY = "default";

	private ErisLogger logger;
	private ContentManager contentManager;

	public ErisScheduler(URI resourceDir, ErisLogger logger) {
		this.resourceDir = resourceDir;
		this.logger = logger;
		this.contentManager = new ContentManager(resourceDir, logger);
		loadSchedule();

		now = LocalDateTime.now();

		singleton = this;
	}

	/* --- Singleton for ErisServer --- */

	private static ErisScheduler singleton;

	public static ErisScheduler get() {
		return singleton;
	}

	public ErisLogger getLogger() {
		return logger;
	}

	/* --- Load Schedule --- */
	
	/*
	 * 	ENTRY-FORMAT: "TAG : FORMAT"
	 */

	private void loadSchedule() {
		schedule = new String[7][48];
		logger.print(SD + " : Loading Schedule ...");

		File scheduleFile = new File(resourceDir.resolve(PathHandler.SCHEDULE));
		System.out.println(scheduleFile.getAbsolutePath());

		if (scheduleFile.exists()) {
			processSchedule(scheduleFile);
		} else {
			createEmptySchedule();
		}
	}

	private void processSchedule(File scheduleFile) {
		try (Scanner scanner = new Scanner(scheduleFile)) {
			for (int i = 0; i < 48; i++) {
				String input = scanner.nextLine();
				for (int j = 0; j < 7; j++) {
					String entry = input.split(";")[j];
					if (entry.isBlank()) {
						schedule[j][i] = (DEFAULT_ENTRY);
					} else {
						schedule[j][i] = (entry);
					}
				}
			}
		} catch (Exception e) {
			logger.printError(SD + " : While processing the schedule file", e);
		}
	}

	private void createEmptySchedule() {
		for (int i = 0; i < 48; i++) {
			for (int j = 0; j < 7; j++) {
				schedule[j][i] = DEFAULT_ENTRY;
			}
		}
	}

	/* --- Get Next Video --- */

	public String getNextVideoPath() {
		contentManager.loadContent();

		int day = now.getDayOfWeek().getValue() - 1;
		int hour = (now.getHour() * 2) + (now.getMinute() / 30);

		String scheduleEntry = schedule[day][hour];
		if (scheduleEntry.equals(DEFAULT_ENTRY)) {
			return getRandomVideo();
		} else {
			return getScheduledVideo(scheduleEntry);
		}
	}
	
	protected String getRandomVideo() {
		ArrayList<Channel> channeList = contentManager.getChannelList();
		int randomChannelIndex = (int) (Math.random() * channeList.size());
		Channel channel = channeList.get(randomChannelIndex);
		
		ArrayList<Video> videoList = channel.getVideoList();
		int randomVideoIndex = (int) (Math.random() * videoList.size());
		Video video = videoList.get(randomVideoIndex);
		
		return getPath(channel, video);
	}
	
	protected String getScheduledVideo(String entry) {
//		String tag = entry.substring(0, 2);
//		String format = entry.substring(5);
		
		return getRandomVideo();
	}
	
	
	protected String getPath(Channel channel, Video video) {
		String videoPath = channel.getTag() 
							+"_"+ ErisDateTimer.toInt(video.getUploadDate()) 
							+"_"+ video.getPlayTime()
							+"_"+ video.getName()
							+".mp4";
		URI globalPath = PathHandler.resourceDir().resolve(videoPath);
		return globalPath.getPath();
	}
}
