package edu.udel.cisc475.aisim.tasktree;


public class FacilitatesNodeRelationship extends NodeRelationship{
	private double newQuality;
	private int newDuration;
	
	public FacilitatesNodeRelationship(Node fromNode, Node toNode, String name, double newQuality, int newDuration) {
		super(fromNode, toNode, name);
		this.newQuality = newQuality;
		this.newDuration = newDuration;
	}
	
	@Override
	public void evaluate(){
		if (!((Method)toNode).isStarted()) {
			getToNode().setQuality(newQuality);
			((Method)getToNode()).setDuration(newDuration);
		}
	}

	public double getNewQuality() {
		return newQuality;
	}

	public double getNewDuration() {
		return newDuration;
	}
}
