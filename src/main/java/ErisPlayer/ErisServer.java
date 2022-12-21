package ErisPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ErisServer {
	
	private AtomicBoolean close;
	
	private ServerSocket serverSocket;
	private ServerThread serverThread;
	
	private ErisLogger logger;
	
	public ErisServer(ErisLogger logger) {
		this.logger = logger;
		this.close = new AtomicBoolean(false);
	}
	
	public boolean start() {
		try {
			serverSocket = new ServerSocket(2332);
			logger.print("SocketServer started");
			
			serverSocket.accept();
			logger.print("Client connected");
			
			serverThread = new ServerThread();
			serverThread.setDaemon(true);
			serverThread.start();
			logger.print("ServerThread started");
			
			return true;
		} catch (IOException e) {
			logger.printError("Can't start Server", e);
			return false;
		}
	}
	
	public boolean close() {
		close.set(true);
		return true;
	}
	
	
	private class ServerThread extends Thread {
		@Override
		public void run() {
			while(!serverSocket.isClosed() && close.get()) {
				// waiting for request ...
				
				snooze(10);
			}
		}
		
		public void snooze(long milies) {
			try {
				Thread.sleep(milies);
			} catch (InterruptedException e) {
				logger.printError("While Thread.sleep", e);
			}
		}
	}
	
	public static void main(String[] args) {
		ErisServer run = new ErisServer(new ErisLogger(null));
		run.start();
	}
	
}
