import java.net.*;
import java.io.*;
import java.util.concurrent.*;


public class Server
{
	public static final int PORT = 4200;

    // construct a thread pool for concurrency	
	private static final Executor exec = Executors.newCachedThreadPool();
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = null;
		
		try {
			// establish the socket
			server = new ServerSocket(PORT);
			
			while (true) {
				/**
				 * now listen for connections
				 * and service the connection in a separate thread.
				 */
				Runnable task = new Connection(server.accept());
				exec.execute(task);
			}
		}
		
		catch (IOException ioe) { System.err.println(ioe); }
		finally {
			if (server != null)
				server.close();
		}



	}
}
