package ErisPlayer;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.glassfish.tyrus.server.Server;

@ServerEndpoint(value="/")
public class ErisWebSocket {
	
	private Session session;
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
	}
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println(" R_> "+message);
		
		if(this.session != null && this.session.isOpen()) {
			try {
				this.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				System.err.println(" E_> [While sending back the Mssage] : " +e.getMessage());
			}
		}
	}
	
	
	
	public static void main(String[] args) throws DeploymentException, IOException, URISyntaxException {
		
		Server server = new Server("127.0.0.1", 8080, "/", ErisWebSocket.class);
		server.start();
		while(true);
	}
	
	
}
