package edu.udel.cisc475.aisim.tasktree;

/**
 * This class represents an Enabling relationship between two nodes in the task
 * tree. When the source node is completed, the target node becomes enabled.
 * 
 * @author Mike
 *
 */
public class EnablesNodeRelationship extends NodeRelationship {

	/**
	 * Default constructor for a EnablesNodeRelationship.
	 * 
	 * @param fromNode
	 *            The node that activates the relationship.
	 * @param toNode
	 *            The node that's enabled by the relationship.
	 * @param name
	 *            The name of the relationship.
	 */
	public EnablesNodeRelationship(Node fromNode, Node toNode, String name) {
		super(fromNode, toNode, name);
	}

	@Override
	/**
	 * Evaluates the relationship on the target node. This enables the node (and all its children).
	 */
	public void evaluate() {
		toNode.enable();
	}
}
