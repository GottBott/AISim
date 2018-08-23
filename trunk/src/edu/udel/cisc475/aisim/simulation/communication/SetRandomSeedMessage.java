package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a SetRandomSeedMessage. This message is sent by the
 * simulator to an agent before the simulation starts. It tells the agents to
 * seed their random number generator with the given value.
 * 
 * @author Mike
 *
 */
public class SetRandomSeedMessage extends Message {
	/**
	 * The seed.
	 */
	private long seed;

	/**
	 * The default constructor for a SetRandomSeedMessage.
	 * 
	 * @param destinationName
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * @param seed
	 *            The seed.
	 */
	public SetRandomSeedMessage(String destinationName, long seed) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.seed = seed;
		this.messageType = "SetRandomSeedMessage";
		this.logMessageDetail = "The random seed is " + seed;

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
