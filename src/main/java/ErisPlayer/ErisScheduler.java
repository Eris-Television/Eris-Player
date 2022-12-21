package ErisPlayer;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Scanner;

import ErisPlayer.data.ContentManager;

public class ErisScheduler {
	
	private LocalDateTime now;
	private URI resourceDir;
	private String[][] schedule;
	
	private ErisLogger logger;
	private ContentManager contentManager;
	
	public ErisScheduler(URI resourceDir, ErisLogger logger) {
		this.resourceDir = resourceDir;
		this.logger = logger;
		this.contentManager = new ContentManager(resourceDir, logger);
		loadSchedule();
		
		now = LocalDateTime.now();
	}
	
	private void loadSchedule() {
		schedule = new String[7][48];
		logger.print("Scheduler: Loading Schedule ...");
		
		File scheduleFile;
		try {
			scheduleFile = new File(new URI(resourceDir + "Schedule.csv").getPath());
		} catch (URISyntaxException e1) {
			// TODO
			return;
		}
		System.out.println(scheduleFile.getAbsolutePath());
		
		try(Scanner scanner = new Scanner(scheduleFile)) {
			for(int i = 0; i < 48; i++) {
				String input = scanner.nextLine();
				for(int j = 0; j < 7; j++) {
					String entry = input.split(";")[j];
					if(entry.isBlank()) {
						schedule[i][j] = "default";
					}else {
						schedule[i][j] = entry;
					}
				}
			}
		}catch(Exception e) {
			// TODO
		}
		
		
	}
	
	public String getNextVideoPath() {
		contentManager.loadContent();
		
		int day = now.getDayOfWeek().getValue() -1;
		int hour= (now.getHour() *2) + (now.getMinute() /30);
		
		String scheduleEntry = schedule[day][hour];
		if(scheduleEntry.equals("default")) {
			return ""; // TODO: GET random Video
		}else {
			return resourceDir+"Not implemented yet!";
		}
	}
	
}
