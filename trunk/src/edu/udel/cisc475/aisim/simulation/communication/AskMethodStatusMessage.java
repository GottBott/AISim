package edu.udel.cisc475.aisim.simulation.communication;

import org.json.*;

/**
 * This class represents an AskMethodStatusMessage. This is a message that an
 * agent sends to the Simulator to ask the status of a method.
 * 
 * @author Mike
 *
 */
public class AskMethodStatusMessage extends Message {
	/**
	 * The name of the method being asked about.
	 */
	private String methodName;

	/**
	 * The default constructor for an AskMethodStatusMessage.
	 * @param senderName
	 * The name of the entity that sent this message.
	 * @param methodName
	 * The name of the method being asked about.
	 */
	public AskMethodStatusMessage(String senderName, String methodName) {
		super(senderName, Message.SIMULATOR_NAME);
		this.methodName = methodName;
		this.messageType = "AskMethodStatusMessage";
		this.logMessageDetail = "Asked Status of " + methodName;
	}

	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("AskMethodStatusMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("MethodName");
		jsonStringer.value(methodName);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public String getMethodName() {
		return methodName;
	}
}
