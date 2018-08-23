package edu.udel.cisc475.aisim.simulation.communication;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONStringer;

import edu.udel.cisc475.aisim.tasktree.DisablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.EnablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.FacilitatesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.HindersNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Node;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Task;

public class NewNodeMessage extends Message{
	private Node node;

	public NewNodeMessage(String senderName, String destinationName, Node node) {
		super(senderName, destinationName);
		this.node = node;
	}
	
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("NewNodeMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("Node");
		nodeToJSON(node, jsonStringer);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}
	
	private void nodeToJSON(Node node, JSONStringer jsonStringer) throws JSONException {
		//TODO: Tell agents what other agents can see somehow
		jsonStringer.object();
		jsonStringer.key("NodeType");
		if (node instanceof Task) {
			jsonStringer.value("Task");
			jsonStringer.key("NodeName");
			jsonStringer.value(node.getName());
			jsonStringer.key("QAF");
			jsonStringer.value(((Task) node).getQaf());
			jsonStringer.key("EarliestStartTime");
			jsonStringer.value(((Task) node).getEarliestStartTime());
			jsonStringer.key("Deadline");
			jsonStringer.value(((Task) node).getDeadline());
			jsonStringer.key("Relationships");
			relationshipsToJSON(node.getRelationships(), jsonStringer);
			jsonStringer.key("SubTasks");
			jsonStringer.array();
			for (Node n : ((Task) node).getSubTasks()) {
				nodeToJSON(n, jsonStringer);
			}
			jsonStringer.endArray();
		} else if (node instanceof Method) {
			jsonStringer.value("Method");
			jsonStringer.key("NodeName");
			jsonStringer.value(node.getName());
			jsonStringer.key("AgentName");
			jsonStringer.value(((Method) node).getAgent());
			jsonStringer.key("Quality");
			jsonStringer.value(node.getQuality());
			jsonStringer.key("Duration");
			jsonStringer.value(((Method) node).getDuration());
			jsonStringer.key("Relationships");
			relationshipsToJSON(node.getRelationships(), jsonStringer);
		}
		jsonStringer.endObject();
	}
	
	private void relationshipsToJSON(ArrayList<NodeRelationship> relationships, JSONStringer jsonStringer) throws JSONException {
		jsonStringer.array();
		for (NodeRelationship nr : relationships) {
			jsonStringer.object();
			jsonStringer.key("RelationshipName");
			jsonStringer.value(nr.getName());
			jsonStringer.key("Source");
			jsonStringer.value(nr.getFromNode().getName());
			jsonStringer.key("Destination");
			jsonStringer.value(nr.getToNode().getName());
			jsonStringer.key("RelationshipType");
			if (nr instanceof DisablesNodeRelationship) {
				jsonStringer.value("Disables");
			} else if (nr instanceof EnablesNodeRelationship) {
				jsonStringer.value("Enables");
			} else if (nr instanceof FacilitatesNodeRelationship) {
				jsonStringer.value("Facilitates");
				jsonStringer.key("NewQuality");
				jsonStringer.value(((FacilitatesNodeRelationship) nr).getNewQuality());
				jsonStringer.key("NewDuration");
				jsonStringer.value(((FacilitatesNodeRelationship) nr).getNewDuration());
			} else if (nr instanceof HindersNodeRelationship) {
				jsonStringer.value("Hinders");
				jsonStringer.key("NewQuality");
				jsonStringer.value(((HindersNodeRelationship) nr).getNewQuality());
				jsonStringer.key("NewDuration");
				jsonStringer.value(((HindersNodeRelationship) nr).getNewDuration());
			}
			jsonStringer.endObject();
		}
		jsonStringer.endArray();
	}
}
