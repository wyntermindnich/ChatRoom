import java.net.*;
import java.io.*;
import java.util.concurrent.*;


public class Server
{
	public static final int PORT = 4200;
	
	public static void main(String[] args) throws java.io.IOException {
		// create a server socket listening to port 2500
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Waiting for connections ....");

		while (true) {
			// we block here until there is a client connection
			Socket client = server.accept();
			
			/**
			 * we have a connection!
			 * Let's get some information about it. 
			 * An InetAddress is an IP address
			 */ 
			
			try {
				// establish the socket
				server = new ServerSocket(DEFAULT_PORT);
				
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
				if (sock != null)
					sock.close();
			}
		}
	}
}
