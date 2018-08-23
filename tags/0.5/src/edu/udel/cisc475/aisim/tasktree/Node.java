package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Node {
	protected Task parent;
	protected String name;
	protected double quality;
	protected boolean enabled;
	protected boolean finished;
	protected int earliestStartTime;
	protected int deadline;
	protected ArrayList<NodeRelationship> relationships;
	protected HashSet<String> visibleToAgents;
	protected HashSet<String> agentsBelow;
	
	public Node(Task parent, String name) {
		this.parent = parent;
		this.name = name;
		this.quality = 0.F;
		this.enabled = true;
		this.finished = false;
		this.earliestStartTime = -1;
		this.deadline = -1;
		this.relationships = new ArrayList<NodeRelationship>();
		this.visibleToAgents = new HashSet<String>();
		this.agentsBelow = new HashSet<String>();
	}
	
	public void findVisibleToAgents() {
		findAgentsBelow();
		findVisibleToAgentsFromRelationships();
	}
	
	private HashSet<String> findAgentsBelow() {
		if (this instanceof Method) {
			agentsBelow.add(((Method)this).getAgent());
		} else {
			for (Node n : ((Task)this).getSubTasks()) {
				agentsBelow.addAll(n.findAgentsBelow());
			}
		}
		visibleToAgents.addAll(agentsBelow);
		
		return agentsBelow;
	}
	
	private void findVisibleToAgentsFromRelationships() {
		for (NodeRelationship r : relationships) {
			r.getToNode().getVisibleToAgents().addAll(agentsBelow);
			visibleToAgents.addAll(r.getToNode().agentsBelow);
		}
		
		if (this instanceof Task) {
			for (Node n : ((Task)this).getSubTasks()) {
				n.findVisibleToAgentsFromRelationships();
			}
		}
	}

	public void addRelationship(NodeRelationship n) {
		relationships.add(n);
	}
	
	public void activateRelationships() {
		for (NodeRelationship nr : relationships) {
			if (nr.getFromNode() == this) {
				nr.evaluate();
			}
		}
		if (relationships.size() > 0) {
			relationships = new ArrayList<NodeRelationship>();
		}
	}
	
	public abstract void enable();
	
	public abstract void disable();
	
	public abstract void facilitate(double qualityFactor, double durationFactor);
	
	public abstract void hinder(double qualityFactor, double durationFactor);
	
	public abstract void computeEarliestStartAndDeadline();
	
	public Task getParent() {
		return parent;
	}

	public void setParent(Task parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public ArrayList<NodeRelationship> getRelationships() {
		return relationships;
	}
	
	public HashSet<String> getVisibleToAgents() {
		return visibleToAgents;
	}
	
	public int getEarliestStartTime() {
		return earliestStartTime;
	}

	public void setEarliestStartTime(int earliestStartTime) {
		this.earliestStartTime = earliestStartTime;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	
}
