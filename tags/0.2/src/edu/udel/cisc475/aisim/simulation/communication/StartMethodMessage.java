package edu.udel.cisc475.aisim.simulation.communication;

public class StartMethodMessage extends Message {
	private String methodName;

	public StartMethodMessage(String senderName, String destinationName,
			String methodName) {
		super(senderName, destinationName);
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
