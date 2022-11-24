package erisPlayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErisDateTimer {
	
	protected DateTimeFormatter formatter;
	protected LocalDateTime now;
	
	public ErisDateTimer() {
		formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm-ss");
		now = LocalDateTime.now();
	}

	protected String getDateTime() {
		return formatter.format(now);
	}

	protected String getDateTimeInBrackets() {
		return "[" + getDateTime() + "]";
	}
	
	
	// TODO implement use in other Classes like ContentManger
}
