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
	
	public static Message getMessageFromJSON(String JSON) {
		JSONObject jsonObj = new JSONObject(JSON);
		String type = jsonObj.getString("MessageType");
		JSONObject messageContent = jsonObj.getJSONObject("Message");
		String sender = messageContent.getString("MsgSender");
		String dest = messageContent.getString("MsgDest");
		
		Message message;
		String method;
		
		switch (type) {
			case "AgentRegistrationMessage":
				message = new AgentRegistrationMessage(sender);
				break;
			case "AskMethodStatusMessage":
				method = messageContent.getString("MethodName");
				message = new AskMethodStatusMessage(sender, method);
				break;
			case "StartMethodMessage":
				method = messageContent.getString("MethodName");
				message = new StartMethodMessage(sender, dest, method);
				break;
			default:
				message = null;
		}
		
		return message;
	}
	
	public abstract String toJSON();

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	
	public static void main(String[] args) {
		String JSON = "{\"MessageType\":\"AskMethodStatusMessage\",\"Message\":{\"MsgSender\":\"Agent1\",\"MsgDest\":\"SIMULATOR\",\"MethodName\":\"Method1\"}}";
		
		Message message = Message.getMessageFromJSON(JSON);
		
		if (message instanceof AskMethodStatusMessage) {
			System.out.println(message.senderName + " " + message.destinationName + " " + ((AskMethodStatusMessage)message).getMethodName());
		}
		
	}
}
