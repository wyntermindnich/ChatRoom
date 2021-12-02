/**
 * Handler class containing the logic for echoing results back
 * to the client. 
 *
 * @author Greg Gagne 
 */
//Edited by Eden Dronoff to write a multithreaded name service client-server application
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Handler{
	/**
	 * this method is invoked by a separate thread
	 */
	
	public void process(Socket client, ArrayList socketList, ArrayList nameList, ArrayList idList, int ids) throws java.io.IOException {
		//creates the input and output streams to talk to the client
		BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
		BufferedOutputStream toClient = new BufferedOutputStream(client.getOutputStream());
		String line;
		
		while((line = fromClient.readLine()) != null) {
			Message message =  Message.constructMessage(line);
			if  (message.getControlCode() == 1) {

				
		//puts the client socket into the array list
		socketList.add(client);
			for(int i = 0; i < socketList.size(); i++) {
				System.out.println(client);
			}
			
		//reads the user input for their username
		Payload[] messagePayload = message.getPayload();
		 for (Payload payload : messagePayload) {
			 nameList.add(payload.getMessage());
			 idList.add(ids);
			for(int i = 0; i < nameList.size(); i++) {
				System.out.println(nameList.get(i));
				System.out.println(idList.get(i));
			} 
		 }
		 toClient.write(ids);
		 toClient.flush();
	} 
			if (message.getControlCode() == 2) {
			 	for(int i = 0; i < socketList.size(); i++) {
					System.out.println(nameList.get(socketList.indexOf(client)) + " has left");
					System.out.println(idList.get(socketList.indexOf(client))  + " has left");
					System.out.println(client + " has left");
				}
				nameList.remove(socketList.indexOf(client));
				idList.remove(socketList.indexOf(client));
				socketList.remove(client);
				
			}
	}
}
}


