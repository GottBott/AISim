package edu.udel.cisc475.aisim.simulation.communication;

import org.json.*;

public abstract class Message {
	public static final String SIMULATOR_NAME = "SIMULATOR";
	protected String senderName;
	protected String destinationName;
	
	public Message(String senderName, String destinationName) {
		this.senderName = senderName;
		this.destinationName = destinationName;
	}
	
	public static Message getMessageFromJSON(String JSON) throws JSONException {
		JSONObject jsonObj = new JSONObject(JSON);
		String type = jsonObj.getString("MessageType");
		JSONObject messageContent = jsonObj.getJSONObject("Message");
		String sender = messageContent.getString("MsgSender");
		String dest = messageContent.getString("MsgDest");
		
		Message message;
		String method;
		boolean started, completed, enabled;
		double quality, duration;
		String relationshipname;
		long seed;
		
		
		switch (type) {
			case "AgentRegistrationMessage":
				message = new AgentRegistrationMessage(sender);
				break;
			case "AskMethodStatusMessage":
				method = messageContent.getString("MethodName");
				message = new AskMethodStatusMessage(sender, method);
				break;
			case "NotifyMethodStatusMessage":
				method = messageContent.getString("MethodName");
				started = Boolean.parseBoolean(messageContent.getString("started"));
				completed = Boolean.parseBoolean(messageContent.getString("completed"));
				enabled = Boolean.parseBoolean(messageContent.getString("enabled"));
				message = new NotifyMethodStatusMessage(dest, method, started, completed, enabled);
				break;
			case "NotifyMethodCompletedMessage":
				method = messageContent.getString("MethodName");
				quality = messageContent.getDouble("Quality");
				duration = messageContent.getDouble("Duration");
				message = new NotifyMethodCompletedMessage(dest, method, quality, duration);		
				break;
			case "NotifyRelationshipActivationMessage":
				relationshipname = messageContent.getString("RelationshipName");
				message = new NotifyRelationshipActivationMessage(dest, relationshipname);
				break;
			case "StartMethodMessage":
				method = messageContent.getString("MethodName");
				message = new StartMethodMessage(sender, dest, method);
				break;
			case "ConfirmMethodStartMessage":
				method = messageContent.getString("MethodName");
				started = Boolean.parseBoolean(messageContent.getString("started"));
				message = new ConfirmMethodStartMessage(dest, method, started);
				break;
			case "SetRandomSeedMessage":
				seed = messageContent.getLong("Seed");
				message = new SetRandomSeedMessage(dest, seed);
				break;
/*			case "NewNodeMessage":
				//
				break;*/
			default:
				message = null;
		}
		
		return message;
	}
	
	public abstract String toJSON() throws JSONException;

	public String getSenderName() {
		return senderName;
	}

	public String getDestinationName() {
		return destinationName;
	}
	
/*	public static void main(String[] args) throws JSONException {
		String JSON = "{\"MessageType\":\"AskMethodStatusMessage\",\"Message\":{\"MsgSender\":\"Agent1\",\"MsgDest\":\"SIMULATOR\",\"MethodName\":\"Method1\"}}";
		
		Message message = Message.getMessageFromJSON(JSON);
		
		if (message instanceof AskMethodStatusMessage) {
			System.out.println(message.senderName + " " + message.destinationName + " " + ((AskMethodStatusMessage)message).getMethodName());
		}
		
	}*/
}
