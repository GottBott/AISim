package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents an EndSimulationMessage. This message is sent by the
 * simulator to an agent when the simulation has completed.
 * 
 * @author Stefan, Ben
 *
 */
public class EndSimulationMessage extends Message {

	/**
	 * The default constructor for an EndSimulationMessage.
	 * 
	 * @param destinationName
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * 
	 */
	public EndSimulationMessage(String destinationName) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.messageType = "EndSimulationMessage";
		this.logMessageDetail = ("The Simulation is Over");

	}

	@Override
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("EndSimulationMessage");
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
