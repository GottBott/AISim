package edu.udel.cisc475.aisim.tasktree;

public abstract class NodeRelationship {
	protected Node fromNode;
	protected Node toNode;
	protected String name;
	
	public NodeRelationship(Node fromNode, Node toNode, String name) {
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.name = name;
	}

	public abstract void evaluate();

	public Node getFromNode() {
		return fromNode;
	}

	public Node getToNode() {
		return toNode;
	}

	public String getName() {
		return name;
	}
}
