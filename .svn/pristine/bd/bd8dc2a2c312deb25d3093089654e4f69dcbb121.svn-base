package edu.udel.cisc475.aisim.simulation.communication;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a StartSimulationMessage. This message is sent by the
 * simulator to an agent when the simulation is starting (after all initial data
 * has been sent).
 * 
 * @author Mike
 *
 */
public class StartSimulationMessage extends Message {

	//String agentList;
	private JSONArray agentList;
	
	
	/**
	 * The default constructor for a StartSimulationMessage.
	 * 
	 * @param destination
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 */
	public StartSimulationMessage(String destination, HashSet<String> agents) {
		super(Message.SIMULATOR_NAME, destination);
		this.messageType = "StartSimulationMessage";
		this.logMessageDetail = "The Simulation has Started";		
		agentList = new JSONArray();
		for (String name : agents) {
			agentList.put(name);
		}
		//agentList = (String) agentList.subSequence(1, agentList.length() -1);

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
		jsonStringer.key("Agents");
		jsonStringer.value(agentList);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}
}
