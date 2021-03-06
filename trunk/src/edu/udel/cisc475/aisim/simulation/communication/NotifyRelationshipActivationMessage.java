package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a NotifyRelationshipActivationMessage. This message is
 * sent by the simulator to an agent when a relationship was activated that can
 * be seen by that agent.
 * 
 * @author Mike
 *
 */
public class NotifyRelationshipActivationMessage extends Message {
	/**
	 * The name of the relationship that was activated.
	 */
	private String relationshipName;
	
	private String targetNode;
	
	private String relationshipType;

	/**
	 * The default constructor for a NotifyRelationshipActivationMessage.
	 * 
	 * @param destinationName
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * @param relationshipName
	 *            The name of the relationship that was activated.
	 */
	public NotifyRelationshipActivationMessage(String destinationName,
			String relationshipName, String type, String target) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.relationshipName = relationshipName;
		this.messageType = "NotifyRelationshipActivationMessage";
		this.targetNode = target;
		this.relationshipType = type;
		this.logMessageDetail = " Relationship " + relationshipName
				+ " has been activated";

	}

	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("NotifyRelationshipActivationMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("RelationshipName");
		jsonStringer.value(relationshipName);
		jsonStringer.key("Target");
		jsonStringer.value(targetNode);
		jsonStringer.key("Type");
		jsonStringer.value(relationshipType);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public String getRelationshipName() {
		return relationshipName;
	}
}
