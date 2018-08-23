package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class represents an abstract Node in the task tree. Nodes can be either
 * a task or a method.
 * 
 * @author Mike
 *
 */
public abstract class Node {
	/**
	 * The task that is the parent of this node. null if this the head of the
	 * task tree.
	 */
	protected Task parent;

	/**
	 * The name of the node.
	 */
	protected String name;

	/**
	 * The quality of the node. Method's have a fixed quality (earned upon
	 * completion), task's have a variable quality based on their QAFs.
	 */
	protected double quality;

	/**
	 * Whether or not this node is enabled.
	 */
	protected boolean enabled;

	/**
	 * Whether or not this node is finished. A method finishes when an agent has
	 * worked on it for the required duration. A task finishes when all of it's
	 * children (recursively going down the tree) finish.
	 */
	protected boolean finished;

	/**
	 * The earliest possible start time of this node in ticks.
	 */
	protected long earliestStartTime;

	/**
	 * The deadline of this node in ticks. A node cannot be started after the
	 * deadline.
	 */
	protected long deadline;

	/**
	 * The list of relationships that have this node as either a source or a
	 * target.
	 */
	protected ArrayList<NodeRelationship> relationships;

	/**
	 * The set (names) of all agents that can see this node. This is the union
	 * of agentsBelow for this node and all nodes that are connected to it via a
	 * relationship.
	 */
	protected HashSet<String> visibleToAgents;

	/**
	 * The set (names) of some of the agents that can see this node. This is
	 * union of agentsBelow for all children of this node. If this node is a
	 * method, agentBelow is the agent assigned to it.
	 */
	protected HashSet<String> agentsBelow;

	/**
	 * Whether or not this node has arrived in the problem yet.
	 */
	protected boolean arrived;

	/**
	 * The time at which this node will arrive in the problem.
	 */
	protected long arrivalTime;

	/**
	 * The default constructor for a Node.
	 * 
	 * @param parent
	 *            The task that is the parent of this node. null if this the
	 *            head of the task tree.
	 * @param name
	 *            The name of the node.
	 */
	public Node(Task parent, String name) {
		this.parent = parent;
		this.name = name;
		this.quality = 0.F;
		this.enabled = true;
		this.finished = false;
		this.earliestStartTime = -1;
		this.deadline = -1;
		this.relationships = new ArrayList<NodeRelationship>();
		this.visibleToAgents = new HashSet<String>();
		this.agentsBelow = new HashSet<String>();
		this.arrived = true;
		this.arrivalTime = 0L;
	}

	/**
	 * Fills visibleToAgents with the names of all agents that can see this
	 * node. This is the union of agentsBelow for this node and all nodes that
	 * are connected to it via a relationship.
	 */
	public void findVisibleToAgents() {
		findAgentsBelow();
		findVisibleToAgentsFromRelationships();
	}

	/**
	 * Fills agentsBelow with the names of all agents below this node. This is
	 * union of agentsBelow for all children of this node. If this node is a
	 * method, agentBelow is the agent assigned to it.
	 * 
	 * @return The set (names) of all agents that are below this node.
	 */
	private HashSet<String> findAgentsBelow() {
		if (this instanceof Method) {
			agentsBelow.add(((Method) this).getAgent());
		} else {
			for (Node n : ((Task) this).getSubTasks()) {
				if (n.hasArrived())
				{
					agentsBelow.addAll(n.findAgentsBelow());
				}
			}
		}
		visibleToAgents.addAll(agentsBelow);

		return agentsBelow;
	}

	/**
	 * Adds the agentsBelow sets of all nodes that are connected to this one via
	 * a relationship to visibleToAgents. Also recursively calls this down the
	 * tree.
	 */
	private void findVisibleToAgentsFromRelationships() {
		for (NodeRelationship r : relationships) {
			if (r.hasArrived()) {
				if (r.getToNode() != this && r.getToNode().hasArrived()) {
					r.getToNode().getVisibleToAgents().addAll(agentsBelow);
					visibleToAgents.addAll(r.getToNode().agentsBelow);
				}
				else if(r.getFromNode() != this && r.getFromNode().hasArrived()) {
					r.getFromNode().getVisibleToAgents().addAll(agentsBelow);
					visibleToAgents.addAll(r.getFromNode().agentsBelow);
				}
			}
		}

		if (this instanceof Task) {
			for (Node n : ((Task) this).getSubTasks()) {
				if (n.hasArrived()) {
					n.findVisibleToAgentsFromRelationships();
				}
			}
		}
	}

	/**
	 * Adds a relationship to the list of relationships connected to this node.
	 * 
	 * @param relationship
	 *            The relationship to be added the list of relationships
	 *            connected to this node.
	 */
	public void addRelationship(NodeRelationship relationship) {
		relationships.add(relationship);
	}

	/**
	 * Activates all relationships on this node that have this node as a source.
	 * Returns a map from the name of the activated relationships to set of
	 * names of all agents that can see that relationship.
	 * 
	 * @return A map from the names of the activated relationships to set of
	 *         names of all agents that can see those relationships.
	 */
	public HashMap<String, HashSet<String>> activateRelationships() {
		HashMap<String, HashSet<String>> activatedRelationships = new HashMap<String, HashSet<String>>();
		for (NodeRelationship nr : relationships) {
			if (!nr.hasActivated() && nr.hasArrived()
					&& nr.getFromNode() == this) {
				nr.evaluate();
				if (!activatedRelationships.containsKey(nr.getName())) {
					HashSet<String> agents = new HashSet<String>();
					agents.addAll(nr.getFromNode().getVisibleToAgents());
					agents.addAll(nr.getToNode().getVisibleToAgents());
					activatedRelationships.put(nr.getName(), agents);
				}
			}
		}
		return activatedRelationships;
	}

	/**
	 * Called by an EnablesNodeRelationship. If called on a method, the method
	 * will become enabled. If called on a task, all of that task's children
	 * will have this method called on them (effectively enabling all methods
	 * below this task).
	 */
	public abstract void enable();

	/**
	 * Called by a DisablesNodeRelationship. If called on a method, the method
	 * will become disabled. If called on a task, all of that task's children
	 * will have this method called on them (effectively disabling all methods
	 * below this task).
	 */
	public abstract void disable();

	/**
	 * Called by a FaciliatesNodeRelationship. If called on a method, the method
	 * will become facilitated. If called on a task, all of that task's children
	 * will have this method called on them (effectively facilitating all
	 * methods below this task).
	 * 
	 * @param qualityFactor
	 *            The factor that the quality will be multiplied by (1 +
	 *            factor). Should be between 0 and 1.
	 * @param durationFactor
	 *            The factor that the duration will be multiplied by. Should be
	 *            between 0 and 1.
	 */
	public abstract void facilitate(double qualityFactor, double durationFactor);

	/**
	 * Called by a HindersNodeRelationship. If called on a method, the method
	 * will become hindered. If called on a task, all of that task's children
	 * will have this method called on them (effectively hindering all methods
	 * below this task).
	 * 
	 * @param qualityFactor
	 *            The factor that the quality will be multiplied by. Should be
	 *            between 0 and 1.
	 * @param durationFactor
	 *            The factor that the duration will be multiplied by (1 +
	 *            factor). Should be between 0 and 1.
	 */
	public abstract void hinder(double qualityFactor, double durationFactor);

	/**
	 * Computes the earliest start time and the deadline of this node based on
	 * its parent. If this node is a task, it compares the earliest start time
	 * and deadline of itself and its parent and chooses the most restrictive
	 * values for both between the two. It then recurses down the tree. If this
	 * node is a method, it takes these values straight from its parent.
	 */
	public abstract void computeEarliestStartAndDeadline();

	public Task getParent() {
		return parent;
	}

	public void setParent(Task parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public ArrayList<NodeRelationship> getRelationships() {
		return relationships;
	}

	public HashSet<String> getVisibleToAgents() {
		return visibleToAgents;
	}

	public long getEarliestStartTime() {
		return earliestStartTime;
	}

	public void setEarliestStartTime(int earliestStartTime) {
		this.earliestStartTime = earliestStartTime;
	}

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public boolean hasArrived() {
		return arrived;
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

}
