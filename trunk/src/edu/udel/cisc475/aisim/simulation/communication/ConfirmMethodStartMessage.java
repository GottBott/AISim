package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a ConfirmMethodStartMessage. This message is sent by
 * the simulator to an agent to indicate whether or not an Agent can start a
 * method.
 * 
 * @author Mike
 *
 */
public class ConfirmMethodStartMessage extends Message {
	/**
	 * The name of the method.
	 */
	private String methodName;
	
	/**
	 * Whether or not the method can be started.
	 */
	private boolean started;

	/**
	 * The default constructor for a ConfirmMethodStartMessage.
	 * @param destinationName
	 * The name of the entity that is supposed to receive this message.
	 * @param methodName
	 * The name of the method.
	 * @param started
	 * Whether or not the method can be started.
	 */
	public ConfirmMethodStartMessage(String destinationName, String methodName,
			boolean started) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.methodName = methodName;
		this.started = started;
		this.messageType = "ConfirmMethodStartMessage";
		this.logMessageDetail = (methodName + " has started: " + started);

	}

	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("ConfirmMethodStartMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("MethodName");
		jsonStringer.value(methodName);
		jsonStringer.key("Started");
		jsonStringer.value(started);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public String getMethodName() {
		return methodName;
	}

	public boolean isStarted() {
		return started;
	}
}
