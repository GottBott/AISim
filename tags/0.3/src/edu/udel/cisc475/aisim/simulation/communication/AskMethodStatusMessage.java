package edu.udel.cisc475.aisim.simulation.communication;

import org.json.*;

public class AskMethodStatusMessage extends Message{
	private String methodName;

	public AskMethodStatusMessage(String senderName, String methodName) {
		super(senderName, Message.SIMULATOR_NAME);
		this.methodName = methodName;
	}
	
	public String toJSON() {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("AskMethodStatusMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("MethodName");
		jsonStringer.value(methodName);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public static void main(String[] args) {
		AskMethodStatusMessage message = new AskMethodStatusMessage("Agent1", "Method1");
		System.out.println(message.toJSON());
	}
}
