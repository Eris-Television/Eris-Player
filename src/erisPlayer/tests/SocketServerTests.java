package erisPlayer.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import erisPlayer.ErisLogger;
import erisPlayer.ErisSocketServer;

class SocketServerTests {
	
	private ErisLogger logger;
	private ErisSocketServer server;
	
	@Test
	void serverTest() {
		logger = new ErisLogger(null);
		server = new ErisSocketServer(logger);
		
		
		// TODO
		fail("Not yet implemented");
	}

}
