package edu.udel.cisc475.aisim.tasktree;

/**
 * This class represents a Facilitating relationship between two nodes in the
 * task tree. When the source node is completed, the target node's quality is
 * increased and its duration is decreased.
 * 
 * @author Mike
 *
 */
public class FacilitatesNodeRelationship extends NodeRelationship {
	/**
	 * The factor that the quality will be multiplied by (1 + factor). Should be
	 * between 0 and 1.
	 */
	private double qualityFactor;

	/**
	 * The factor that the duration will be multiplied by. Should be between 0
	 * and 1.
	 */
	private double durationFactor;

	/**
	 * The default constructor for a FacilitatesNodeRelationship.
	 * 
	 * @param fromNode
	 *            The node that activates the relationship.
	 * @param toNode
	 *            The node that is facilitated by the relationship.
	 * @param name
	 *            The name of the relationship.
	 * @param qualityFactor
	 *            The factor that the quality will be multiplied by (1 +
	 *            factor). Should be between 0 and 1.
	 * @param durationFactor
	 *            The factor that the duration will be multiplied by. Should be
	 *            between 0 and 1.
	 */
	public FacilitatesNodeRelationship(Node fromNode, Node toNode, String name,
			double qualityFactor, double durationFactor) {
		super(fromNode, toNode, name);
		this.qualityFactor = qualityFactor;
		this.durationFactor = durationFactor;
	}

	@Override
	/**
	 * Evaluates the relationship on the target node. This facilitates the node (and all of its children).
	 */
	public void evaluate() {
		toNode.facilitate(qualityFactor, durationFactor);
	}

	public double getQualityFactor() {
		return qualityFactor;
	}

	public double getDurationFactor() {
		return durationFactor;
	}
}
