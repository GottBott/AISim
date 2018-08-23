package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

public class AgentRegistrationMessage extends Message {

	public AgentRegistrationMessage(String sender) {
		super(sender, Message.SIMULATOR_NAME);
		this.MethodType = "AgentRegistrationMessage";
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
