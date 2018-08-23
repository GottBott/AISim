package edu.udel.cisc475.aisim.tasktree;

public abstract class NodeRelationship {
	private Node fromNode;
	private Node toNode;
	private String name;
	
	public NodeRelationship(Node fromNode, Node toNode, String name) {
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.name = name;
	}

	public abstract void evaluate();

	public Node getFromNode() {
		return fromNode;
	}

	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	public Node getToNode() {
		return toNode;
	}

	public void setToNode(Node toNode) {
		this.toNode = toNode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
