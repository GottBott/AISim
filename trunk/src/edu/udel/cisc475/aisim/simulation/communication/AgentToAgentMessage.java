package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * This class represents a message that agents can send to other agents. They
 * can fill the content field with a JSONObject containing whatever they want.
 * It is up to the specific implementation of the agents to determine the
 * details of the messages they pass to each other and how to handle them.
 * 
 * @author Mike
 *
 */
public class AgentToAgentMessage extends Message {

	/**
	 * The custom content of the message.
	 */
	private JSONObject content;

	/**
	 * The default constructor for an AgentToAgentMessage.
	 * 
	 * @param sender
	 *            The name of the entity that sent this message.
	 * @param destination
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * @param content
	 *            The custom content of the message.
	 */
	public AgentToAgentMessage(String sender, String destination,
			JSONObject content) {
		super(sender, destination);
		this.content = content;
		this.messageType = "AgentToAgentMessage";
		this.logMessageDetail = sender + " has sent a message to "
				+ destination + " with content: " + content.toString();
	}

	@Override
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("AgentToAgentMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("Content");
		jsonStringer.value(content);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public JSONObject getContent() {
		return content;
	}
}
