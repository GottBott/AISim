package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

public class NextTickMessage extends Message {
	private long tick;

	public NextTickMessage(String destination, long tick) {
		super(Message.SIMULATOR_NAME, destination);
		this.tick = tick;
		this.MethodType = "NextTickMessage";

	}
	
	@Override
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("NextTickMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("Tick");
		jsonStringer.value(tick);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}
	
	public long getTick(){
		return tick;
	}
}
