package ErisPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import ErisPlayer.data.Channel;
import ErisPlayer.data.Video;

public class PathHandler {
	
	/* --- localDir --- */
	
	public static URI localDir() {
		try {
			return new File(".").getCanonicalFile().toURI();
		} catch (Exception e) { return null; }
	}
	
	/* --- logDirs --- */
	
	public static URI logDir() {
		try {
			return localDir().resolve("log/");
		} catch (Exception e) { return null; }
	}
	
	
	public static URI editorLogDir() {
		try {
			return localDir().resolve("EditorLogs/");
		} catch (Exception e) { return null; }
	}
	
	/* --- resourceDir --- */
	
	public static URI resourceDir() {
		try {
			return localDir().resolve("resources/");
		} catch (Exception e) { return null; }
	}
	
	public static URI downloadDir() {
		try {
			return resourceDir().resolve("Downloads/");
		} catch (Exception e) { return null; }
	}
	
	/* --- Files --- */
	
	public static final String CONTENT  = "content.xml"; 
	public static final String SCHEDULE = "schedule.csv";
	
	/* --- testlDir --- */
	
	public static URI testDir() {
		try {
			return localDir().resolve("tests/");
		} catch (Exception e) { return null; }
	}
	
	public static URI testResourceDir() {
		try {
			return testDir().resolve("resources/");
		} catch (Exception e) { return null; }
	}
	
	public static URI testDownloadDir() {
		try {
			return testResourceDir().resolve("Downloads/");
		} catch (Exception e) { return null; }
	}
	
	public static URI testDataDir() {
		try {
			return testDir().resolve("testData/");
		} catch (Exception e) { return null; }
	}
	
	/* --- Test-Data-Methods --- */
	
	public static void emptyTestResources() {
		File testResourceDir = new File(PathHandler.testResourceDir());
		deleteFile(testResourceDir);
		new File(testResourceDir()).mkdir();
	}
	
	public static URI testContentData() { // TODO: Reactor / use CONTENT in all
		try {
			return testResourceDir().resolve(CONTENT);
		} catch (Exception e) { return null; }
	}
	
	public static void addTestContentData() throws IOException {
		emptyTestResources();
		File source = new File(testDataDir().resolve("loadContent.xml"));
		File target = new File(testContentData());
		target.setWritable(true);
		Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static URI testSchedule() {
		try {
			return testResourceDir().resolve(SCHEDULE);
		} catch (Exception e) { return null; }
	}
	
	public static void addTestSchedule() throws IOException {
		emptyTestResources();
		File source = new File(testDataDir().resolve(SCHEDULE));
		File target = new File(testSchedule());
		target.setWritable(true);
		Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static void addTestScheduleContent() throws IOException {
		emptyTestResources();
		File source = new File(testDataDir().resolve("scheduleContent.xml"));
		File target = new File(testContentData());
		target.setWritable(true);
		Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/* -- generalMethodes ---*/
	
	private static boolean deleteFile(File file) {
		File[] files = file.listFiles();
		
		if(files != null) { 
			for(File currentFile : file.listFiles()) {
				deleteFile(currentFile);
			}
		}

		return file.delete();
	}
	
	public static String uriToString(URI path) {
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.startsWith("windows")) {
			return path.getPath().replace('/', '\\').substring(1);
		}else {
			return path.getPath() + "/";
		}
	}
	
	public static String getPath(URI resourceDir, Channel channel, Video video) {
		String videoPath = channel.getTag() 
							+"/"+ channel.getTag() 
							+"_"+ ErisDateTimer.toInt(video.getUploadDate()) 
							+"_"+ video.getPlayTime()
							+"_"+ video.getName()
							+".mp4";
		// TODO add more
		videoPath = videoPath.replace(" ", "%20");
		videoPath = videoPath.replace("#", "%23");
		
		return resourceDir.toString() +videoPath;
	}
	
}
