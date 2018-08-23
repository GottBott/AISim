package edu.udel.cisc475.aisim.simulation.communication;

import org.json.*;

/**
 * This class represents an abstract message that is sent between entities in
 * the simulation.
 * 
 * @author Mike, Ben
 *
 */
public abstract class Message {
	/**
	 * The name of the Simulator. Used as a sender or destination name.
	 */
	public static final String SIMULATOR_NAME = "SIMULATOR";

	/**
	 * The name of the entity that sent this message.
	 */
	protected String senderName;

	/**
	 * The name of the entity that is supposed to receive this message.
	 */
	protected String destinationName;

	/**
	 * The type of message.
	 */
	public String messageType;

	/**
	 * The detailed log statement depending on the message type.
	 */
	public String logMessageDetail;

	/**
	 * The default constructor for a Message.
	 * 
	 * @param senderName
	 *            The name of the entity that is sending this message.
	 * @param destinationName
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 */
	public Message(String senderName, String destinationName) {
		this.senderName = senderName;
		this.destinationName = destinationName;
	}

	/**
	 * Converts a JSON-formatted String into the appropriate message subclass.
	 * FOR INCOMMING MESSAGES
	 * @param JSON
	 *            A JSON-formatted String.
	 * @return The message that the JSON converts to.
	 * @throws JSONException
	 */
	public static Message getMessageFromJSON(String JSON) throws JSONException {
		JSONObject jsonObj = new JSONObject(JSON);
		String type = jsonObj.getString("MessageType");
		JSONObject messageContent = jsonObj.getJSONObject("Message");
		String sender = messageContent.getString("MsgSender");
		String dest = messageContent.getString("MsgDest");
		Message message;
		String method;
		JSONObject content;
		
		switch (type) {
		case "AgentRegistrationMessage":
			message = new AgentRegistrationMessage(sender);
			break;
		case "AskMethodStatusMessage":
			method = messageContent.getString("MethodName");
			message = new AskMethodStatusMessage(sender, method);
			break;
		case "StartMethodMessage":
			method = messageContent.getString("MethodName");
			message = new StartMethodMessage(sender, dest, method);
			break;
		case "AgentToAgentMessage":
			content = messageContent.getJSONObject("Content");
			message = new AgentToAgentMessage(sender, dest, content);
			break;
		case "AbortMethodMessage":
			method = messageContent.getString("MethodName");
			message = new AbortMethodMessage(sender, dest, method);
			break;

		default:
			message = null;
		}

		return message;
	}

	/**
	 * Turns a message into a JSON-formatted String.
	 * @return
	 * The JSON-formatted String that represents the message.
	 * @throws JSONException
	 */
	public abstract String toJSON() throws JSONException;

	public String getSenderName() {
		return senderName;
	}

	public String getDestinationName() {
		return destinationName;
	}

	/**
	 * 
	 * @return log statement depending if a message is inbound or outbound
	 * 
	 */
	public String getlogMessageHeader() {
		if (senderName == SIMULATOR_NAME)
			return "Sending a " + messageType + " to " + destinationName;
		return "Received a " + messageType + " From " + senderName;
	}

	/**
	 * 
	 * @return detailed Log statement depending on the message type
	 */
	public String getlogMessageDetail() {
		return logMessageDetail;
	}

}
