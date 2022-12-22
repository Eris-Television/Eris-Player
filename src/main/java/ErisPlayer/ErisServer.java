package ErisPlayer;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/")
public class ErisServer {
	
	private final String WS = "[WebSocket]";
	private Session session;
	
	private ErisLogger logger;
	private ErisScheduler scheduler;

	@OnOpen
	public void opOpen(Session session) {
		this.session = session;
		this.scheduler = ErisScheduler.get();
		this.logger = scheduler.getLogger();

		logger.print("Serve opend"); // TODO
	}

	@OnClose
	public void onClose() {
		// TODO
		
		ErisPlayer.closePlayer();
	}

	@OnMessage
	public void onMessage(String message) {

		switch (message) {
		case "CONNECT":
			send("CONNECTED");
			break;
			
		case "DISCONNECT":
			send("DISCONNECTED");
			break;
			
		case "ERROR":
			logger.printError(WS, new Exception("While sending message to client"));
		case "NEXT_VIDEO":
			String nextVideo = ""; //scheduler.getNextVideoPath();
			if(nextVideo == null || nextVideo.isBlank()) { nextVideo = "noSignal.mp4"; } 
			
			send("NEXT_VIDEO : "+nextVideo);
			break;
		default:
			send("ERROR");
			logger.printError(WS, new Exception("Unable to process message : " + message));
			break;
		}

	}

	private void send(String message) {
		logger.print(WS +" : "+ message);
		
		if (session != null && session.isOpen()) {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				logger.printError(WS + " While sending Message", e);
			}
		}
	}

	@OnError
	public void onError(Throwable throwable) {
		logger.printError(WS, new Exception(throwable));
	}

}
