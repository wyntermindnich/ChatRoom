/**
 * A message in the protocol
 */

public class message
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