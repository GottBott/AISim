package edu.udel.cisc475.aisim.simulation.communication;

import org.json.JSONStringer;

public class NotifyMethodStatusMessage extends Message{
	private String methodName;
	private boolean inProgress;
	private boolean completed;
	private boolean enabled;
	
	public NotifyMethodStatusMessage(String destinationName,
			String methodName, boolean inProgress, boolean completed,
			boolean enabled) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.methodName = methodName;
		this.inProgress = inProgress;
		this.completed = completed;
		this.enabled = enabled;
	}
	
	public String toJSON() {
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

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
