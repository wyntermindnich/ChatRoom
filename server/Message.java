/**
 * A message in the protocol
 */
import java.util.Scanner;
public class Message
{
    private int controlCode;
    private int quantity;
    private Payload[] payload;

    private int payLoadIndex;

    public Message(int controlCode, int quantity) {
        this.controlCode = controlCode; 
        this.quantity = quantity;
        payload = new Payload[quantity];

        payLoadIndex = 0;
    }

    public static Message constructMessage(String contents) {
        Scanner message = new Scanner(contents).useDelimiter(",");

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

    public void addPayload(Payload contents) {
        payload[payLoadIndex] = contents;

        payLoadIndex++;
    }

    public int getControlCode() {
        return controlCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public Payload[] getPayload() {
        return payload;
    }
}