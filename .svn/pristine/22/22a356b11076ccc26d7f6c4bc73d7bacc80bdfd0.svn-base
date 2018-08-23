package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

public class NotifyMethodCompletedMessage extends Message{
	private String methodName;
	private double quality;
	private double duration;
	
	public NotifyMethodCompletedMessage(String destinationName, String methodName, double quality,
			double duration) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.methodName = methodName;
		this.quality = quality;
		this.duration = duration;
	}
	
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("NotifyMethodCompletedMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("MethodName");
		jsonStringer.value(methodName);
		jsonStringer.key("Quality");
		jsonStringer.value(quality);
		jsonStringer.key("Duration");
		jsonStringer.value(duration);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public String getMethodName() {
		return methodName;
	}

	public double getQuality() {
		return quality;
	}

	public double getDuration() {
		return duration;
	}
}
