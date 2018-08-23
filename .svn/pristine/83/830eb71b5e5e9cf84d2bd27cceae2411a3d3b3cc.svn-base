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
	 * Holds the String representation of the relationship type. 
	 */
	protected String type;
	
	/**
	 * Whether or not this node has arrived in the problem yet.
	 */
	protected boolean arrived;
	
	/**
	 * The time at which this node will arrive in the problem.
	 */
	protected long arrivalTime;
	
	/**
	 * Whether or not the relationship has been activated.
	 */
	protected boolean activated;

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
		this.arrived = true;
		this.arrivalTime = 0L;
		this.activated = false;
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
	
	public String getType(){
		return type;
	}
	
	public boolean hasArrived() {
		return arrived && toNode.hasArrived() && fromNode.hasArrived();
	}
	
	public void setArrived(boolean arrived) {
		this.arrived = arrived;
	}
	
	public long getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public boolean hasActivated() {
		return activated;
	}
}
