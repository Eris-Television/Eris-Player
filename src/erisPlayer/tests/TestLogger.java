package erisPlayer.tests;

import erisPlayer.ErisLogger;

public class TestLogger extends ErisLogger{
	
	/* DO NOT USE - Just for extending ErisLogger */
	public TestLogger(String path) {
		super(path);
	}
	
	@Override
	public void print(String message) {
		message = getDateTimeInBrackets() + message;
		System.out.println(message);
	}
	
	@Override
	public void printSubline(String message) {
		message = getDateTimeInBrackets() +" _> "+ message;
		System.out.println(message);
	}
	
	@Override
	public void printError(String error, Exception e) {
		error = getDateTimeInBrackets() + "ERROR " + error + " : " + e.getMessage();
		System.err.println(error);
	}
	
	@Override
	public void printLog() {}
	
}
