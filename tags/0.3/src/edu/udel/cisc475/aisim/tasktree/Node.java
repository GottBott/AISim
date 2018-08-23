package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

public abstract class Node {
	private Task parent;
	private String name;
	private double quality;
	private boolean enabled;
	private boolean finished;
	private ArrayList<NodeRelationship> relationships;
	
	public Node(Task parent, String name, double quality, boolean enabled,
			boolean finished, ArrayList<NodeRelationship> relationships) {
		this.parent = parent;
		this.name = name;
		this.quality = quality;
		this.enabled = enabled;
		this.finished = finished;
		this.relationships = relationships;
	}
	
	public Node(Task parent, String name) {
		this.parent = parent;
		this.name = name;
		this.quality = 0.F;
		this.enabled = true;
		this.finished = false;
		this.relationships = new ArrayList<NodeRelationship>();
	}

	public void addRelationship(NodeRelationship n) {
		relationships.add(n);
	}
	
	public void enable(){
		enabled = true;
	}
	
	public void disable(){
		enabled = false;
	}

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

	public void setRelationships(ArrayList<NodeRelationship> relationships) {
		this.relationships = relationships;
	}
	
}
