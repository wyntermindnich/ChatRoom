import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.*;


public class Server {
	
	static ArrayList socketList  = new ArrayList();
	static ArrayList nameList  = new ArrayList();
	static ArrayList idList  = new ArrayList();
	static int ids = 1;
	
	public static final int PORT = 4200;
    // construct a thread pool for concurrency	
	private static final Executor exec = Executors.newCachedThreadPool();
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket server = null;
		
		try {
			// establish the socket
			server = new ServerSocket(PORT);
			
			while (true) {
				Socket client = server.accept();
				
				/**
				 * now listen for connections
				 * and service the connection in a separate thread.
				 */
				ids++;
				Runnable task = new Connection(client,socketList,nameList,idList,ids);
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


