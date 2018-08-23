package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

public class NotifyRelationshipActivationMessage extends Message{
	private String relationshipName;

	public NotifyRelationshipActivationMessage(String destinationName, String relationshipName) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.relationshipName = relationshipName;
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
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public String getRelationshipName() {
		return relationshipName;
	}
}
