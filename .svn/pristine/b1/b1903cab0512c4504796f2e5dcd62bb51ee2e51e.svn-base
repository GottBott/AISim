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
	
	@Override
	public void enable() {
		if (!isStarted) {
			this.enabled = true;
		}
	}
	
	@Override
	public void disable() {
		if (!isStarted) {
			this.enabled = false;
		}
	}
	
	@Override
	public void facilitate(double qualityFactor, double durationFactor) {
		if (!isStarted) {
			this.quality *= (1 + qualityFactor);
			this.duration *= durationFactor;
		}
	}
	
	@Override
	public void hinder(double qualityFactor, double durationFactor) {
		if (!isStarted) {
			this.quality *= qualityFactor;
			this.duration *= (1 + durationFactor);
		}
	}
	
	@Override
	public void computeEarliestStartAndDeadline() {
		if (parent != null) {
			if (parent.getEarliestStartTime() != -1) {
				this.earliestStartTime = parent.getEarliestStartTime();
			} else {
				this.earliestStartTime = 0;
			}
			if (parent.getDeadline() != -1) {
				this.deadline = parent.getDeadline();
			} else {
				this.deadline = Integer.MAX_VALUE;
			}
		}
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
