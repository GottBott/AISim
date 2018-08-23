package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONStringer;

public class ConfirmMethodStartMessage extends Message {
	private String methodName;
	private boolean started;
	
	public ConfirmMethodStartMessage(String destination, String methodName, boolean started) {
		super(Message.SIMULATOR_NAME, destination);
		this.methodName = methodName;
		this.started = started;
	}
	
	public String toJSON() {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("ConfirmMethodStartMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("MethodName");
		jsonStringer.value(methodName);
		jsonStringer.key("Started");
		jsonStringer.value(started);
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

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	
	public static void main(String[] args) {
		ConfirmMethodStartMessage message = new ConfirmMethodStartMessage("Agent1", "Method1", true);
		System.out.println(message.toJSON());
	}
}
