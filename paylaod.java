/**
 * The payload of the message
 */

public class payload
{
    private int userID;
    private int length;
    private String message;

    public Payload(int userID, int length, String message) {
        this.userID = userID;
        this.length = length;
        this.message = message;
    }

    public int getUserId() {
        return userID;
    }

    public int getLength() {
        return length;
    }

    public String getMessage() {
        return message;
    }
}