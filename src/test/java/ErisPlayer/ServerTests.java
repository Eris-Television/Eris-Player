package ErisPlayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ServerTests {
	
	private ErisLogger logger;
	private ErisServer server;
	
	@Test
	void serverTest() {
		logger = new ErisLogger(null);
		server = new ErisServer(logger);
		server.close();
		
		
		// TODO
	}

}
