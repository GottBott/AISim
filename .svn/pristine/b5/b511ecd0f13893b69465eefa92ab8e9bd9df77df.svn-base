package edu.udel.cisc475.aisim.simulation.communication;

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
	
	public String toJSON() {
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

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
}
