package ErisPlayer;

import java.time.LocalDate;
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
	
	public static LocalDate toLocalDate(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4, 6));
		int day = Integer.valueOf(date.substring(6, 8));
		
		return LocalDate.of(year, month, day);
	}
	
	public static int toInt(LocalDate date) {
		String stringDate = ""+ fillString(date.getYear()) + fillString(date.getMonthValue()) + fillString(date.getDayOfMonth());
		return Integer.parseInt(stringDate);
	}
	
	private static String fillString(int integer) {
		if(integer < 10) {
			return "0"+integer;
		}else {
			return ""+integer;
		}
	}
	
}
