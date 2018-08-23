package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a NotifyMethodCompletedMessage. This message is sent by
 * the simulator to an agent when the agent has successfully completed a method.
 * 
 * @author Mike
 *
 */
public class NotifyMethodCompletedMessage extends Message {
	/**
	 * The name of the method that has been completed.
	 */
	private String methodName;

	/**
	 * The quality earned on the method.
	 */
	private double quality;

	/**
	 * The duration of the method.
	 */
	private double duration;

	/**
	 * The default constructor for a NotifyMethodCompletedMessage.
	 * 
	 * @param destinationName
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * @param methodName
	 *            The name of the method that has been completed.
	 * @param quality
	 *            The quality earned on the method.
	 * @param duration
	 *            The duration of the method.
	 */
	public NotifyMethodCompletedMessage(String destinationName,
			String methodName, double quality, double duration) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.methodName = methodName;
		this.quality = quality;
		this.duration = duration;
		this.messageType = "NotifyMethodCompletedMessage";
		this.logMessageDetail = destinationName + " has completed "
				+ methodName;

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
