/**
 * Example code that illustrates how to parse messages
 * received from the socket.
 */

import java.util.Scanner;

public class Practice
{
    /** 
     * You are welcome to use this method as is.
     * The parameter is a line just read from the socket
     * This method returns a Message object
     */
    public Message constructMessage(String contents) {
        Scanner message = new Scanner(contents).useDelimiter("%@&");

        int controlCode = message.nextInt();
        int quantity = message.nextInt();

        Message m = new Message(controlCode, quantity);

        Payload payload;

        for (int i = 0; i < quantity; i++) {
            int userID = message.nextInt();
            int length = message.nextInt();
            String  phrase = message.next();

            payload = new Payload(userID, length, phrase);
            m.addPayload(payload);
        }

        return m;
    }

    /**
     * This code illustrates how to extract the different
     * fields from the Message
     */
    public void testRun(String protocol) {

        Message m = constructMessage(protocol);

        System.out.println("Just got a message:");
        System.out.println("control code = " + m.getControlCode());
        System.out.println("quantity = " + m.getQuantity());
        
        Payload[] messagePayload = m.getPayload();

        for (Payload p : messagePayload) {
            System.out.println("User id = " + p.getUserId() + " Message = " + p.getMessage());
        }
    }

    public static void main(String[] args) {

        // below are some various types of messages
        // that can be exchanged

        String userList = "0,3,4,2,CK,3,6,Wynter,2,4,Eden";
        String join = "1,1,0,4,Eden";
        String broadcast = "255,1,0,6,Hello!";

        Practice p = new Practice();
    
        p.testRun(userList);
        p.testRun(join);
        p.testRun(broadcast);

    }
}