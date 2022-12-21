package ErisPlayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SocketServerTests {
	
	private ErisLogger logger;
	private ErisSocketServer server;
	
	@Test
	void serverTest() {
		logger = new ErisLogger(null);
		server = new ErisSocketServer(logger);
		server.close();
		
		
		// TODO
		fail("Not yet implemented");
	}

}
