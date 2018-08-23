package edu.udel.cisc475.aisim.tasktree;

import java.util.HashMap;
import java.util.HashSet;

public class TaskTree {
	
	private Task head;
	private HashMap<String, Node> nameToNodeMap;
	
	public TaskTree() {
		this.head = null;
		nameToNodeMap = new HashMap<String, Node>();
	}
	
	public TaskTree(Task head) {
		this.head = head;
		nameToNodeMap = new HashMap<String, Node>();
		buildMap(head);
		
	}
	
	private void buildMap(Node current) {
		if (current != null) {
			nameToNodeMap.put(current.getName(), current);
			if (current instanceof Task) {
				for (Node n : ((Task)current).getSubTasks()) {
					buildMap(n);
				}
			}
		}
	}
	
	public void newHead(Task head) {
		this.head = head;
		nameToNodeMap.clear();
		buildMap(head);
	}
	
	public void addNode(Node node, String parentName) {
		Node parent = nameToNodeMap.get(parentName);
		if (parent != null) {
			if (parent instanceof Task) {
				((Task)parent).addSubTask(node);
				buildMap(node);
			}
		}
	}
	
	public Node getNodeWithName(String name) {
		return nameToNodeMap.get(name);
	}
	
	public double getTotalQuality() {
		head.calculateQualityFromChildren();
		return head.getQuality();
	}
	
	public Node getHead() {
		return head;
	}
	
	/**
	 * Determines if the tree is finished (no more work can be done on it). If there are any
	 * methods that are enabled and not finished, returns false. Otherwise, returns true.
	 * 
	 * @return Boolean indicating if the tree is finished or not.
	 */
	public boolean isFinished(long tick) {
		for (Node n : nameToNodeMap.values()) {
			if (n instanceof Method) {
				if (((Method)n).isEnabled() && !((Method)n).isFinished() && n.getEarliestStartTime() <= tick && n.getDeadline() > tick) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void findVisibleToAgents() {
		head.findVisibleToAgents();
	}
	
	public HashMap<String, HashSet<String>> activateAllRelationships() {
		HashMap<String, HashSet<String>> activatedRelationships = new HashMap<String, HashSet<String>>();
		head.checkIsFinished();
		for (Node n : nameToNodeMap.values()) {
			if (n.isFinished()) {
				for (NodeRelationship nr : n.getRelationships()) {
					if (!activatedRelationships.containsKey(nr.getName())) {
						HashSet<String> agents = new HashSet<String>();
						agents.addAll(nr.getFromNode().getVisibleToAgents());
						agents.addAll(nr.getToNode().getVisibleToAgents());
						activatedRelationships.put(nr.getName(), agents);
					}
				}
				n.activateRelationships();
			}
		}
		
		return activatedRelationships;
	}
	
	public void computeEarliestStartAndDeadline() {
		head.computeEarliestStartAndDeadline();
	}
}
