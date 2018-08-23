package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a ConfirmMethodAbortMessage. This message is sent by
 * the simulator to an agent to indicate whether or not an Agent can abort a
 * method.
 * 
 * @author Ben
 *
 */
public class ConfirmMethodAbortMessage extends Message {
	/**
	 * The name of the method.
	 */
	private String methodName;
	
	/**
	 * Whether or not the method can be aborted.
	 */
	private boolean aborted;

	/**
	 * The default constructor for a ConfirmMethodStartMessage.
	 * @param destinationName
	 * The name of the entity that is supposed to receive this message.
	 * @param methodName
	 * The name of the method.
	 * @param started
	 * Whether or not the method can be started.
	 */
	public ConfirmMethodAbortMessage(String destinationName, String methodName,
			boolean aborted) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.methodName = methodName;
		this.aborted = aborted;
		this.messageType = "ConfirmMethodAbortMessage";
		this.logMessageDetail = (methodName + " aborted: " + aborted);

	}

	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("ConfirmMethodAbortMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("MethodName");
		jsonStringer.value(methodName);
		jsonStringer.key("Aborted");
		jsonStringer.value(aborted);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public String getMethodName() {
		return methodName;
	}

	public boolean isStarted() {
		return aborted;
	}
}
