package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * This class represents a NotifyMethodStatusMessage. This message is sent by
 * the simulator to an agent to give the agent information about a method.
 * 
 * @author Mike
 *
 */
public class NotifyMethodStatusMessage extends Message {
	/**
	 * The name of the method being asked about.
	 */
	private String methodName;

	/**
	 * Whether or not the method is currently in progress.
	 */
	private boolean inProgress;

	/**
	 * Whether or not the method has been completed.
	 */
	private boolean completed;

	/**
	 * Whether or not the method is enabled.
	 */
	private boolean enabled;

	/**
	 * The default constructor for a NotifyMethodStatusMessage.
	 * 
	 * @param destinationName
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * @param methodName
	 *            The name of the method being asked about.
	 * @param inProgress
	 *            Whether or not the method is currently in progress.
	 * @param completed
	 *            Whether or not the method has been completed.
	 * @param enabled
	 *            Whether or not the method is enabled.
	 */
	public NotifyMethodStatusMessage(String destinationName, String methodName,
			boolean inProgress, boolean completed, boolean enabled) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.methodName = methodName;
		this.inProgress = inProgress;
		this.completed = completed;
		this.enabled = enabled;
		this.messageType = "NotifyMethodStatusMessage";
		this.logMessageDetail = methodName + " is Enabled: " + enabled
				+ " Compleated: " + completed + " In Progress: " + inProgress;

	}

	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("NotifyMethodStatusMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("MethodName");
		jsonStringer.value(methodName);
		jsonStringer.key("Started");
		jsonStringer.value(inProgress);
		jsonStringer.key("Completed");
		jsonStringer.value(completed);
		jsonStringer.key("Enabled");
		jsonStringer.value(enabled);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}

	public String getMethodName() {
		return methodName;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public boolean isCompleted() {
		return completed;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
