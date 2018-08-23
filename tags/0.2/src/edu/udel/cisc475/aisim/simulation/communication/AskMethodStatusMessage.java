package edu.udel.cisc475.aisim.simulation.communication;

public class AskMethodStatusMessage extends Message{
	private String methodName;

	public AskMethodStatusMessage(String senderName, String destinationName,
			String methodName) {
		super(senderName, destinationName);
		this.setMethodName(methodName);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
}
