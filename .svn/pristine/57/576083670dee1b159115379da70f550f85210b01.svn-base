package edu.udel.cisc475.aisim.simulation.communication;

import edu.udel.cisc475.aisim.tasktree.Node;

public class NewNodeMessage extends Message{
	private Node node;

	public NewNodeMessage(String senderName, String destinationName, Node node) {
		super(senderName, destinationName);
		this.setNode(node);
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}	
}
