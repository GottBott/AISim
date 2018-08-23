package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents an AgentRegistrationMessage. This is the message an
 * agent sends to the simulator when it first connects to identify itself by
 * name.
 * 
 * @author Mike
 *
 */
public class AgentRegistrationMessage extends Message {

	/**
	 * The default constructor for an AgentRegistrationMessage.
	 * 
	 * @param sender
	 *            The name of the entity that sent this message.
	 */
	public AgentRegistrationMessage(String sender) {
		super(sender, Message.SIMULATOR_NAME);
		this.messageType = "AgentRegistrationMessage";
	}

	@Override
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("AgentRegistrationMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

}
