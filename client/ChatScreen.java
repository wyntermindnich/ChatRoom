/**
* This program is a rudimentary demonstration of Swing GUI programming.
* Note, the default layout manager for JFrames is the border layout. This
* enables us to position containers using the coordinates South and Center.
*
* Usage:
*  java ChatScreen
*
* When the user enters text in the textfield, it is displayed backwards
* in the display area.
*/
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
 
 
public class ChatScreen extends JFrame implements ActionListener, KeyListener
{
   private JButton sendButton;
   private JButton exitButton;
   private JTextField sendText;
   private JTextArea displayArea;
   public static final String delimeter = "%@&";
   BufferedOutputStream output;
   BufferedReader input;
   String name;
   int id;
 
   public ChatScreen(Socket socket, String args) throws IOException {
      
       output = new BufferedOutputStream(socket.getOutputStream());
       input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       //writing the join information to the server
       String joining = "1" + delimeter + "1"  + delimeter + "0" + delimeter + args.length() + delimeter + args + "\n";
       name = args;
       byte[] message = joining.getBytes();
 
           output.write(message);
       output.flush();
       id = input.read();
       System.out.println(id);
      
       /**
        * a panel used for placing components
        */
       JPanel p = new JPanel();
 
       Border etched = BorderFactory.createEtchedBorder();
       Border titled = BorderFactory.createTitledBorder(etched, "Enter Message Here ...");
       p.setBorder(titled);
 
       /**
        * set up all the components
        */
       sendText = new JTextField(30);
       sendButton = new JButton("Send");
       exitButton = new JButton("Exit");
 
       /**
        * register the listeners for the different button clicks
        */
       sendText.addKeyListener(this);
       sendButton.addActionListener(this);
       exitButton.addActionListener(this);
 
       /**
        * add the components to the panel
        */
       p.add(sendText);
       p.add(sendButton);
       p.add(exitButton);
 
       /**
        * add the panel to the "south" end of the container
        */
       getContentPane().add(p,"South");
 
       /**
        * add the text area for displaying output. Associate
        * a scrollbar with this text area. Note we add the scrollpane
        * to the container, not the text area
        */
       displayArea = new JTextArea(15,40);
       displayArea.setEditable(false);
       displayArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
 
       JScrollPane scrollPane = new JScrollPane(displayArea);
       getContentPane().add(scrollPane,"Center");
 
       /**
        * set the title and size of the frame
        */
       setTitle("GUI Demo");
       pack();
 
       setVisible(true);
       sendText.requestFocus();
 
       /** anonymous inner class to handle window closing events */
       addWindowListener(new WindowAdapter() {
           String exiting = "2" + delimeter + "1"  + delimeter + id + delimeter + name.length() + delimeter + name + "\n";
           byte[] ExitMessage = exiting.getBytes();
      
           public void windowClosing(WindowEvent evt) {
               try {
				output.write(ExitMessage);
				output.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               System.exit(0);
           }
       } );
 
   }
 
   /**
    * Displays a message
    */
   public void displayMessage(String message) {
       String broadcast = "255" + delimeter + "1"  + delimeter + id + delimeter + name.length() + delimeter + name + "\n";
       
       displayArea.append(message + "\n");
   }
 
   /**
    * This gets the text the user entered and outputs it
    * in the display area.
    */
   public void displayText() {
       String message = sendText.getText().trim();
       StringBuffer buffer = new StringBuffer(message.length());
 
       // now reverse it
       for (int i = message.length()-1; i >= 0; i--)
           buffer.append(message.charAt(i));
 
       displayArea.append(buffer.toString() + "\n");
 
       sendText.setText("");
       sendText.requestFocus();
   }
 
 
   /**
    * This method responds to action events .... i.e. button clicks
    * and fulfills the contract of the ActionListener interface.
    */
 
   public void actionPerformed(ActionEvent evt) {
	   String exiting = null;
       Object source = evt.getSource();
 
       if (source == sendButton)
           displayText();
       else if (source == exitButton)
    	   exiting = "2" + delimeter + "1"  + delimeter + id + delimeter + name.length() + delimeter + name + "\n";
       		byte[] ExitMessage = exiting.getBytes();
           try {
			output.write(ExitMessage);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           System.exit(0);
   }
 
   /**
    * These methods responds to keystroke events and fulfills
    * the contract of the KeyListener interface.
    */
 
   /**
    * This is invoked when the user presses
    * the ENTER key.
    */
   public void keyPressed(KeyEvent e) {
       if (e.getKeyCode() == KeyEvent.VK_ENTER)
           displayText();
   }
 
   /** Not implemented */
   public void keyReleased(KeyEvent e) { }
 
   /** Not implemented */
   public void keyTyped(KeyEvent e) {  }
 
 
   public static void main(String[] args) {
       try {
           Socket annoying = new Socket(args[0], 4200);
           ChatScreen win = new ChatScreen(annoying,args[1]);
 
           Thread ReaderThread = new Thread(new ReaderThread(annoying, win));
 
           ReaderThread.start();
          
       }
       catch (UnknownHostException uhe) { System.out.println(uhe); }
       catch (IOException ioe) { System.out.println(ioe); }
 
   }
}





