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
	
	public void process(Socket client, ArrayList socketList, ArrayList nameList, ArrayList idList) throws java.io.IOException {
		//creates the input and output streams to talk to the client
		BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		//puts the client socket into the array list
		socketList.add(client);
			for(int i = 0; i < socketList.size(); i++) {
				System.out.println(socketList.get(i));
			}
			
		//reads the user input for their username
		Message message =  Message.constructMessage(fromClient.readLine());
		Payload[] messagePayload = message.getPayload();
		 for (Payload payload : messagePayload) {
			 nameList.add(payload.getMessage());
			 idList.add(payload.getUserId());
			for(int i = 0; i < nameList.size(); i++) {
				System.out.println(nameList.get(i));
				System.out.println(idList.get(i));
			} 
	} 
	}
}
