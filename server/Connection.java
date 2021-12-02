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
	int ids;
	
	public Connection(Socket client, ArrayList socketList, ArrayList nameList, ArrayList idList, int ids) {
		this.client = client;
		this.socketList =  socketList;
		this.nameList = nameList;
		this.idList = idList;
		this.ids = ids;
	}

    /**
     * This method runs in a separate thread.
     */	
	public void run() { 
		try {
			handler.process(client,socketList,nameList,idList,ids);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}
}



