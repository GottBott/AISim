package edu.udel.cisc475.aisim.tasktree;

import java.util.HashMap;

public class TaskTree {
	
	private Node head;
	private HashMap<String, Node> nameToNodeMap;
	
	public TaskTree() {
		this.head = null;
		nameToNodeMap = new HashMap<String, Node>();
	}
	
	public TaskTree(Node head) {
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
	
	public void newHead(Node head) {
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
		if (head instanceof Task) {
			((Task)head).calculateQualityFromChildren();
		}
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
	public boolean isFinished() {
		for (Node n : nameToNodeMap.values()) {
			if (n instanceof Method) {
				if (((Method)n).isEnabled() && !((Method)n).isFinished()) {
					return false;
				}
			}
		}
		return true;
	}
}
