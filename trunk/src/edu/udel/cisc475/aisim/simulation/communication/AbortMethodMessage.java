package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a AbortedMethodMessage. This message is send by an agent
 * to the simulator to indicate that the agent would like to abort a method it is currently running.
 * 
 * @author Ben
 *
 */
public class AbortMethodMessage extends Message {
	/**
	 * The name of the method to be started.
	 */
	private String methodName;

	/**
	 * The default constructor for a AbortMethodMessage.
	 * 
	 * @param senderName
	 *            The name of the entity that sent this message.
	 * @param destinationName
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * @param methodName
	 *            The name of the method to be aborted.
	 */
	public AbortMethodMessage(String senderName, String destinationName,
			String methodName) {
		super(senderName, destinationName);
		this.methodName = methodName;
		this.messageType = "AbortMethodMessage";
		this.logMessageDetail = senderName + " has asked to abort "
				+ methodName;

	}

	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("AbortMethodMessage");
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
