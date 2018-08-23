package edu.udel.cisc475.aisim.tasktree;

public class DisablesNodeRelationship extends NodeRelationship{
	
	public DisablesNodeRelationship(Node fromNode, Node toNode, String name) {
		super(fromNode, toNode, name);
	}

	@Override
	public void evaluate() {
		getToNode().disable();
	}
}
