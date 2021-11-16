/**
 * Handler class containing the logic for echoing results back
 * to the client. 
 *
 * @author Greg Gagne 
 */

import java.io.*;
import java.net.*;

public class Handler 
{
	public static final int BUFFER_SIZE = 256;
	
	/**
	 * this method is invoked by a separate thread
	 */
	public void process(Socket client) throws java.io.IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		BufferedReader fromClient = null;
		DataOutputStream toClient = null;
		InetAddress hostAddress;
		String line;
		
		try {
			// read from socket, same as reding from client
			// look up ip DNS
			// write ip to socket, bufferend, same as writing client

			 //get the input and output streams associated with the socket.
			toClient = new DataOutputStream(client.getOutputStream());
			int numBytes;
			
			//reads from client
			fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			line = fromClient.readLine();
			// line = ip name
			System.out.println(line);

			//take address from client? 
			hostAddress = InetAddress.getByName(line);
			System.out.println(hostAddress.getHostAddress());

			//write IP to socket
			toClient.writeBytes(hostAddress.toString() + "\r\n");


   		}
		catch (IOException ioe) {
			//write this to socket 
			toClient.writeBytes(ioe.toString() + "\r\n");
			System.err.println(ioe);
		}

		finally {
			// close streams and socket
			if (fromClient != null)
				fromClient.close();
			if (toClient != null)
				toClient.close();
		}
	}
}