package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

public class Method extends Node{
	private String agent;
	private double duration;
	
	public Method(Task parent, String name, double quality, boolean enabled,
			boolean finished, ArrayList<NodeRelationship> relationships,
			String agent, double duration) {
		super(parent, name, quality, enabled, finished, relationships);
		this.agent = agent;
		this.duration = duration;
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
}
