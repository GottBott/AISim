package edu.udel.cisc475.aisim.simulation.communication;

public class NotifyMethodCompletedMessage extends Message{
	private String methodName;
	private double quality;
	private double duration;
	
	public NotifyMethodCompletedMessage(String senderName,
			String destinationName, String methodName, double quality,
			double duration) {
		super(senderName, destinationName);
		this.methodName = methodName;
		this.quality = quality;
		this.duration = duration;
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
