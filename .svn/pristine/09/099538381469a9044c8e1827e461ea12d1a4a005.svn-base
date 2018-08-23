package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

public class SetRandomSeedMessage extends Message{
	private long seed;

	public SetRandomSeedMessage(String destinationName,
			long seed) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.seed = seed;
		this.MethodType = "SetRandomSeedMessage";

	}
	
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("SetRandomSeedMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("Seed");
		jsonStringer.value(seed);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public long getSeed() {
		return seed;
	}
	
}
