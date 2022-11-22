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
			return new File(localDir() + "log/").toURI();
		} catch (Exception e) { return null; }
	}
	
	
	public static URI editorLogDir() {
		try {
			return new File(localDir() + "EditorLogs/").toURI();
		} catch (Exception e) { return null; }
	}
	
	/* --- resourceDir --- */
	
	public static URI resourceDir() {
		try {
			return new File(localDir() + "resources/").toURI();
		} catch (Exception e) { return null; }
	}
	
	public static URI downloadDir() {
		try {
			return new File(resourceDir() + "Download/").toURI();
		} catch (Exception e) { return null; }
	}
	
	/* --- testlDir --- */
	
	public static URI testDir() {
		try {
			return new File(localDir() + "tests/").toURI();
		} catch (Exception e) { return null; }
	}
	
	public static URI testResourceDir() {
		try {
			return new File(testDir() + "resources/").toURI();
		} catch (Exception e) { return null; }
	}
	
	public static URI testDownloadDir() {
		try {
			return new File(testResourceDir() + "Download/").toURI();
		} catch (Exception e) { return null; }
	}
	
	/* -- generalMethodes ---*/
	
	public static String uriToString(URI path) {
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.startsWith("windows")) {
			return path.getPath().replace('/', '\\').substring(1) + "\\";
		}else {
			return path.getPath() + "/";
		}
	}
	
}
