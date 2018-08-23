package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

public class StartSimulationMessage extends Message {

	public StartSimulationMessage(String destination) {
		super(Message.SIMULATOR_NAME, destination);
		this.MethodType = "StartSimulationMessage";

	}
	
	@Override
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("StartSimulationMessage");
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
