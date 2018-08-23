package edu.udel.cisc475.aisim.tasktree;

/**
 * This class represents a Disabling relationship between two nodes in the task
 * tree. When the source node is completed, the target node becomes disabled.
 * 
 * @author Mike
 *
 */
public class DisablesNodeRelationship extends NodeRelationship {

	/**
	 * Default constructor for a DisablesNodeRelationship.
	 * 
	 * @param fromNode
	 *            The node that activates the relationship.
	 * @param toNode
	 *            The node that's disabled by the relationship.
	 * @param name
	 *            The name of the relationship.
	 */
	public DisablesNodeRelationship(Node fromNode, Node toNode, String name) {
		super(fromNode, toNode, name);
		this.type = "Disables";

	}

	@Override
	/**
	 * Evaluates the relationship on the target node. This disables the node (and all its children).
	 */
	public void evaluate() {
		toNode.disable();
		activated = true;
	}
}
