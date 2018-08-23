package edu.udel.cisc475.aisim.tasktree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class represents an entire TaskTree containing tasks, methods, and
 * relationships in a tree-like structure. It also contains some helpful methods
 * for handling the tree.
 * 
 * @author Mike
 *
 */
public class TaskTree {

	/**
	 * The Task that is the head of the TaskTree.
	 */
	private Task head;

	/**
	 * A map between the names of node and the nodes themselves. Allows for
	 * easy/quick lookup of nodes by name.
	 */
	private HashMap<String, Node> nameToNodeMap;

	/**
	 * The default constructor for a TaskTree. Takes in the head of the tree and
	 * builds the map of names and nodes.
	 * 
	 * @param head
	 *            A Task that is the head of the TaskTree.
	 */
	public TaskTree(Task head) {
		this.head = head;
		nameToNodeMap = new HashMap<String, Node>();
		buildMap(head);
	}

	/**
	 * An alternate constructor for a TaskTree. Used to create a TaskTree
	 * without a head.
	 */
	public TaskTree() {
		this.head = null;
		nameToNodeMap = new HashMap<String, Node>();
	}

	/**
	 * Builds the map from names to nodes.
	 * 
	 * @param current
	 *            The current node to build the map on.
	 */
	private void buildMap(Node current) {
		if (current != null) {
			nameToNodeMap.put(current.getName(), current);
			if (current instanceof Task) {
				for (Node n : ((Task) current).getSubTasks()) {
					buildMap(n);
				}
			}
		}
	}

	/**
	 * Gives the TaskTree a new head. Wipes the map from names to nodes and
	 * refills it based on the new head.
	 * 
	 * @param head
	 */
	public void newHead(Task head) {
		this.head = head;
		nameToNodeMap = new HashMap<String, Node>();
		buildMap(head);
	}

	/**
	 * Adds a node to the tree and makes it a child of the node with parentName.
	 * 
	 * @param node
	 *            The node to add to the tree.
	 * @param parentName
	 *            The name of the node that will become the parent of the added
	 *            node.
	 */
	public void addNode(Node node, String parentName) {
		Node parent = nameToNodeMap.get(parentName);
		if (parent != null) {
			if (parent instanceof Task) {
				((Task) parent).addSubTask(node);
				buildMap(node);
			}
		}
	}

	/**
	 * Gets the node out of the tree with the given name.
	 * 
	 * @param name
	 *            The name of the node to get.
	 * @return The node with the given name.
	 */
	public Node getNodeWithName(String name) {
		return nameToNodeMap.get(name);
	}

	/**
	 * Calculates the quality for every node in the tree and returns it.
	 * 
	 * @return The total quality at the head of the tree.
	 */
	public double getTotalQuality() {
		head.calculateQualityFromChildren();
		return head.getQuality();
	}

	public Node getHead() {
		return head;
	}

	/**
	 * Determines if the tree is finished (no more work can be done on it). If
	 * there are any methods that are enabled, not finished, and have arrived,
	 * it returns false. Otherwise, it returns true.
	 * 
	 * @param tick
	 *            The current tick of the simulation.
	 * @return Boolean indicating if the tree is finished or not.
	 */
	public boolean isFinished(long tick) {
		for (Node n : nameToNodeMap.values()) {
			if (n instanceof Method) {
				if (((Method) n).isEnabled() && !((Method) n).isFinished()
						&& n.getEarliestStartTime() <= tick
						&& n.getDeadline() > tick
						&& n.hasArrived()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Fills out the visibleToAgents field of every single node in the tree.
	 */
	public void findVisibleToAgents() {
		head.findVisibleToAgents();
	}

	/**
	 * Activates all relationships in the tree that should be activated. Also
	 * checks whether or not each node in the tree is finished. Returns a map
	 * from the name of the activated relationships to set of names of all
	 * agents that can see that relationship.
	 * 
	 * @return A map from the names of the activated relationships to set of
	 *         names of all agents that can see those relationships.
	 */
	public HashMap<String, HashSet<String>> activateAllRelationships() {
		HashMap<String, HashSet<String>> activatedRelationships = new HashMap<String, HashSet<String>>();
		head.checkIsFinished();
		head.calculateQualityFromChildren();
		for (Node n : nameToNodeMap.values()) {
			if (n.hasArrived()) {
				if ((n instanceof Method && n.isFinished())
						|| (n instanceof Task && n.getQuality() > 0)) {
					activatedRelationships.putAll(n.activateRelationships());
				}
			}
		}

		return activatedRelationships;
	}

	/**
	 * Fills out the earliestStartTime and deadline of all nodes in the tree
	 * based on their parent's values.
	 */
	public void computeEarliestStartAndDeadline() {
		head.computeEarliestStartAndDeadline();
	}

	/**
	 * Finds the nodes and relationships that have arrived on this tick and sets
	 * their arrived field to true. Also recomputes visibleToAgents and quality.
	 * 
	 * @param tick
	 *            The current tick.
	 * 
	 * @return Whether or not new nodes or relationships have arrived.
	 */
	public boolean findArrivals(long tick) {
		boolean arrivals = findArrivals(head, tick);
		if (arrivals) {
			findVisibleToAgents();
		}
		return arrivals;
	}

	/**
	 * Finds the nodes and relationships that have arrived on this tick and sets
	 * their arrived field to true.
	 * 
	 * @param node
	 *            The node we are currently checking.
	 * @param tick
	 *            The current tick.
	 * @return Whether or not new nodes or relationships have arrived.
	 */
	private boolean findArrivals(Node node, long tick) {
		boolean arrivals = false;
		
		if (!node.hasArrived() && node.getArrivalTime() <= tick) {
			node.setArrived(true);
			arrivals = true;
		}
		for (NodeRelationship nr : node.getRelationships()) {
			if (!nr.hasArrived() && nr.getArrivalTime() <= tick) {
				nr.setArrived(true);
				if(nr.hasArrived()){
					arrivals = true;
				}
			}
		}
		
		if (node instanceof Task) {
			for (Node n : ((Task) node).getSubTasks()) {
				arrivals |= findArrivals(n, tick);
			}
		}
		
		return arrivals;
	}

}
