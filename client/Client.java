import java.net.*;
import java.io.*;

public class Client
{
	public static final int PORT = 4200;
	
	public static void main(String[] args) throws java.io.IOException {
		if (args.length != 1) {
			System.err.println("usage: java SimpleClient <host>");
			System.exit(0);
		}
		
		BufferedReader fromServer = null;
		Socket server = null;
		DataOutputStream toServer= null;
	
		
		try {
			// create socket and connect to default port 
			server = new Socket(args[0], PORT);

			//write args[1] to socket
			toServer = new DataOutputStream(server.getOutputStream());
			toServer.writeBytes(args[1].toString() + "\r\n");
			
			// "Readers" are used for reading text characters
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			String line;

			line = fromServer.readLine();
			System.out.println(line);

		} catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}

		finally {
			// let's close streams and sockets
			// closing the input stream closes the socket as well
			if (fromServer!= null)
				fromServer.close();
			if (server != null)
				server.close();
		}
	}
}
