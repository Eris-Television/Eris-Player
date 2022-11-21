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
			return new File(localDir() + "log/").getCanonicalFile().toURI();
		} catch (Exception e) { return null; }
	}
	
	
	public static URI editorLogDir() {
		try {
			return new File(localDir() + "EditorLogs/").getCanonicalFile().toURI();
		} catch (Exception e) { return null; }
	}
	
	/* --- resourceDir --- */
	
	public static URI resourceDir() {
		try {
			return new File(localDir() + "resources/").getCanonicalFile().toURI();
		} catch (Exception e) { return null; }
	}
	
	/* --- testlDir --- */
	
	public static URI testDir() {
		try {
			return new File(localDir() + "").getCanonicalFile().toURI();
		} catch (Exception e) { return null; }
	}
	
	/* -- generalMethodes ---*/
	
	public static String uriToString(URI path) {
		return null; // TODO 
	}
	
	
	public static void main(String[] args) {
		System.out.println(localDir() + "resources/");
		System.out.println(localDir().toString() + "resources/");
	}
	
}
