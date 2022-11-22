package erisPlayer;

import java.io.File;
import java.net.URI;

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
			return resourceDir().resolve("Download/");
		} catch (Exception e) { return null; }
	}
	
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
			return testResourceDir().resolve("Download/");
		} catch (Exception e) { return null; }
	}
	
	public static void emptyDownloadDir() {
		File downloadDir = new File(PathHandler.testDownloadDir());
		
		if(downloadDir.listFiles() != null) {
			for(File file : downloadDir.listFiles()) {
				file.delete();
			}
		}
	}
	
	/* -- generalMethodes ---*/
	
	public static String uriToString(URI path) {
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.startsWith("windows")) {
			return path.getPath().replace('/', '\\').substring(1);
		}else {
			return path.getPath() + "/";
		}
	}
	
}
