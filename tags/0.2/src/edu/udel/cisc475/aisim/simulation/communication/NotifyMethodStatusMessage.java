package edu.udel.cisc475.aisim.simulation.communication;

public class NotifyMethodStatusMessage extends Message{
	private String methodName;
	private boolean inProgress;
	private boolean completed;
	private boolean enabled;
	
	public NotifyMethodStatusMessage(String senderName, String destinationName,
			String methodName, boolean inProgress, boolean completed,
			boolean enabled) {
		super(senderName, destinationName);
		this.methodName = methodName;
		this.inProgress = inProgress;
		this.completed = completed;
		this.enabled = enabled;
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
