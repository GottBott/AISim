package edu.udel.cisc475.aisim.tasktree;


public class EnablesNodeRelationship extends NodeRelationship{
	
	public EnablesNodeRelationship(Node fromNode, Node toNode, String name) {
		super(fromNode, toNode, name);
	}

	@Override
	public void evaluate(){
		getToNode().enable();
	}
}
