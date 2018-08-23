package edu.udel.cisc475.aisim.tasktree;

public class Method extends Node{
	private String agent;
	private int duration;
	private boolean isStarted;
	private Long startTime;
	
	public Method(Task parent, String name, String agent) {
		super(parent, name);
		this.agent = agent;
		this.duration = 0;
		this.isStarted = false;
		this.startTime = -1L;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void start() {
		this.isStarted = true;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Long tick) {
		this.startTime = tick;
	}
}
