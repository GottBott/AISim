package edu.udel.cisc475.aisim.tasktree;

public class Method extends Node{
	private String agent;
	private double duration;
	private boolean isStarted;
	private int startTime;
	
	public Method(Task parent, String name, String agent) {
		super(parent, name);
		this.agent = agent;
		this.duration = 0.F;
		this.isStarted = false;
		this.startTime = -1;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public void start() {
		this.isStarted = true;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
}
