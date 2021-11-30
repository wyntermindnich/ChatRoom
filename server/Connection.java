/**
 * This is the separate thread that services each
 * incoming echo client request.
 *
 * @author Greg Gagne 
 */

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Connection implements Runnable
{
	private Socket	client;
	private Handler handler = new Handler();
	ArrayList socketList;
	ArrayList nameList;
	ArrayList idList;
	
	public Connection(Socket client, ArrayList socketList, ArrayList nameList, ArrayList idList) {
		this.client = client;
		this.socketList =  socketList;
		this.nameList = nameList;
		this.idList = idList;
	}

    /**
     * This method runs in a separate thread.
     */	
	public void run() { 
		try {
			handler.process(client,socketList,nameList,idList);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}
}

