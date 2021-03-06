package edu.udel.cisc475.aisim.tasktree;


public class HindersNodeRelationship extends NodeRelationship{
	private double newQuality;
	private double newDuration;
	
	public HindersNodeRelationship(Node fromNode, Method toNode, String name, double newQuality, double newDuration) {
		super(fromNode, toNode, name);
		this.newQuality = newQuality;
		this.newDuration = newDuration;
	}
	
	@Override
	public void evaluate(){
		getToNode().setQuality(newQuality);
		((Method)getToNode()).setDuration(newDuration);
	}

	public double getNewQuality() {
		return newQuality;
	}

	public void setNewQuality(double newQuality) {
		this.newQuality = newQuality;
	}

	public double getNewDuration() {
		return newDuration;
	}

	public void setNewDuration(double newDuration) {
		this.newDuration = newDuration;
	}
}
