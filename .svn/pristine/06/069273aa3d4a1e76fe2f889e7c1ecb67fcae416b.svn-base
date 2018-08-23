package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a NextTickMessage. This message is sent by the
 * simulator to an agent to indicate that the tick has changed.
 * 
 * @author Mike
 *
 */
public class NextTickMessage extends Message {
	/**
	 * The current tick of the simulation.
	 */
	private long tick;

	/**
	 * The default constructor for a NextTickMessage.
	 * 
	 * @param destination
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * @param tick
	 *            The current tick of the simulation.
	 */
	public NextTickMessage(String destination, long tick) {
		super(Message.SIMULATOR_NAME, destination);
		this.tick = tick;
		this.messageType = "NextTickMessage";
		this.logMessageDetail = ("Tick " + tick +" has occurred");

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

	public long getTick() {
		return tick;
	}
}
