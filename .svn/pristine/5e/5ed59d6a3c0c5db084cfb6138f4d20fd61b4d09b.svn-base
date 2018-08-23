package edu.udel.cisc475.aisim.simulation.communication;

public class NotifyRelationshipActivationMessage extends Message{
	private String relationshipName;

	public NotifyRelationshipActivationMessage(String senderName,
			String destinationName, String relationshipName) {
		super(senderName, destinationName);
		this.setRelationshipName(relationshipName);
	}

	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}
}
