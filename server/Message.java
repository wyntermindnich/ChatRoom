import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Message {

	// Enum provides known message types
	public enum MessageType {
		USER_LIST(0),
		JOIN(1),
		LEAVE(2),
		DIRECT_MESSAGE(254),
		BROADCAST_MESSAGE(255);
		public final int controlCode;
		
		// Initialize message's controlCode
		MessageType(final int controlCode) {
			this.controlCode = controlCode;
		}
	}

	// Reverse map the appropriate controlCodes to the message types.
	// ex. {0: USER_LIST} is {USER_LIST: 0}
	public static final Map<Integer, MessageType> mappedControlCodes;
	static {
		final Map<Integer, MessageType> _mappedControlCodes = new HashMap<>();
		for(final MessageType value : MessageType.values())
			_mappedControlCodes.put(value.controlCode, value);
		mappedControlCodes = Collections.unmodifiableMap(_mappedControlCodes);
	}


	public static class Payload {
		// instance variables
		private int userId;
		private String payloadString = "";
		private byte[] payloadBytes = new byte[0];

		// This constructor sets the userId
		public Payload(final int userId) {
			this.userId = userId;
		}

		// This constructor sets the userId and sets the payload as a string.
		// For the sender.
		public Payload(final int userId, final String payloadString) {
			this.userId = userId;
			this.setPayloadString(payloadString);
		}

		// This constructor sets the userId and sets the payload as a byte array.
		// For the receiver.
		public Payload(final int userId, final byte[] payloadBytes) {
			this.userId = userId;
			this.setPayloadBytes(payloadBytes);
		}


		// Get methods for payloads.
		public int getUserId() {
			return this.userId;
		}

		public String getPayloadString() {
			return this.payloadString;
		}

		public int getPayloadLength() {
			return this.payloadBytes.length;
		}

		public byte getPayloadByteAtIndex(final int byteIndex) {
			return this.payloadBytes[byteIndex];
		}
		// End get methods.

		// Set methods for payloads.
		public void setPayloadString(final String payloadString) {
			this.payloadString = payloadString;
			this.payloadBytes = payloadString.getBytes(StandardCharsets.UTF_8);
		}

		public void setPayloadBytes(final byte[] payloadBytes) {
			this.payloadString = new String(payloadBytes, StandardCharsets.UTF_8);
			this.payloadBytes = payloadBytes;
		}
		// End set methods for payloads.
	}

	// Message instance variables
	private MessageType messageType;
	private int payloadQuantity;
	private Payload[] payloads;


	/**
	 * This constructor is used for initializing message type with controlCode 
	 * and initializes new Payload array using payloadQuantity as the number of payloads.
	 * @param controlCode, payloadQuantity
	 */
	public Message(final int controlCode, final int payloadQuantity) {
		this.messageType = mappedControlCodes.get(controlCode);
		this.payloadQuantity = payloadQuantity;
		this.payloads = new Payload[payloadQuantity];
	}

	/**
	 * This constructor is used for initializing message type and 
	 * initializes new Payload array using payloadQuantity as the number of payloads.
	 * @param messageType, payloadQuantity
	 */
	public Message(final MessageType messageType, final int payloadQuantity) {
		this.messageType = messageType;
		this.payloadQuantity = payloadQuantity;
		this.payloads = new Payload[payloadQuantity];
	}

	// Adds the payload at the specified index of the array that contains all payloads.
	public void addPayload(final Payload payload, final int payloadIndex) {
		this.payloads[payloadIndex] = payload;
	}

	//  Get methods for message type and payload quantity.
	public MessageType getMessageType() {
		return this.messageType;
	}

	public int getPayloadQuantity() {
		return this.payloadQuantity;
	}

	public Payload getPayload(final int payloadIndex) {
		return this.payloads[payloadIndex];
	}
	//  End get methods for message type and payload quantity.

	/**
	 * Static helper method for creating a message with only a single payload (quantity of 1).
	 * @param messageType
	 * @param userId
	 * @param payloadString
	 * @return The created Message.
	 */
	public static Message createSinglePayloadMessage(final MessageType messageType, final int userId, final String payloadString) {
		final Message message = new Message(messageType, 1);
		message.addPayload(new Payload(userId, payloadString), 0);
		return message;
	}
}
