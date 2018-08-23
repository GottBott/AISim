package edu.udel.cisc475.aisim.tasktree;

/**
 * This class represents an abstract relationship between two nodes in the task
 * tree.
 * 
 * @author Mike
 *
 */
public abstract class NodeRelationship {
	/**
	 * The node that activates the relationship.
	 */
	protected Node fromNode;

	/**
	 * The node that's affected by the relationship.
	 */
	protected Node toNode;

	/**
	 * The name of the relationship.
	 */
	protected String name;

	/**
	 * Default constructor for a NodeRelationship.
	 * 
	 * @param fromNode
	 *            The node that activates the relationship.
	 * @param toNode
	 *            The node that's affected by the relationship.
	 * @param name
	 *            The name of the relationship.
	 */
	public NodeRelationship(Node fromNode, Node toNode, String name) {
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.name = name;
	}

	/**
	 * Evaluates the relationship on the target node. This is different for each
	 * type of relationship.
	 */
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
