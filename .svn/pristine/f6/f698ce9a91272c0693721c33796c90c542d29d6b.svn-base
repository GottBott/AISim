package edu.udel.cisc475.aisim.simulation.communication;

import java.util.ArrayList;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import edu.udel.cisc475.aisim.tasktree.DisablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.EnablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.FacilitatesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.HindersNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Node;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Task;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

public class InitialTreeMessage extends Message{
	private TaskTree tree;
	private String agent;
	private JSONArray nodeArray;
	private JSONArray relationshipArray;
	private HashSet<String> writtenRelationships;
	private HashSet<String> writtenNodes;

	public InitialTreeMessage(String senderName, String destinationName, TaskTree tree) {
		super(senderName, destinationName);
		this.tree = tree;
		this.agent = destinationName;
		nodeArray = new JSONArray();
		relationshipArray = new JSONArray();
		writtenRelationships = new HashSet<String>();
		writtenNodes = new HashSet<String>();
		this.MethodType = "InitialTreeMessage";

	}
	
	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("InitialTreeMessage");
		jsonStringer.key("Message");
		jsonStringer.object();
		jsonStringer.key("MsgSender");
		jsonStringer.value(senderName);
		jsonStringer.key("MsgDest");
		jsonStringer.value(destinationName);
		jsonStringer.key("Nodes");
		if (tree.getHead().getVisibleToAgents().contains(agent)) {
			nodeToJSON(tree.getHead());
		}
		jsonStringer.value(nodeArray);
		jsonStringer.key("Relationships");
		jsonStringer.value(relationshipArray);
		jsonStringer.endObject();
		jsonStringer.endObject();
		return jsonStringer.toString();
	}
	
	private void nodeToJSON(Node node) throws JSONException {
		JSONObject obj = new JSONObject();
		if (node instanceof Task) {
			obj.put("NodeType", "Task");
			obj.put("NodeName", node.getName());
			obj.put("QAF", ((Task) node).getQaf());
			obj.put("EarliestStartTime", ((Task) node).getEarliestStartTime());
			obj.put("Deadline", ((Task) node).getDeadline());
			JSONArray visibleTo = new JSONArray();
			for (String name : node.getVisibleToAgents()) {
				visibleTo.put(name);
			}
			obj.put("VisibleToAgents", visibleTo);
			JSONArray subtasks = new JSONArray();
			for (Node n : ((Task) node).getSubTasks()) {
				if (n.getVisibleToAgents().contains(agent)) {
					subtasks.put(n.getName());
				}
			}
			obj.put("SubTasks", subtasks);
			nodeArray.put(obj);
			writtenNodes.add(node.getName());
			relationshipsToJSON(node.getRelationships());
			for (Node n : ((Task) node).getSubTasks()) {
				if (n.getVisibleToAgents().contains(agent) && !writtenNodes.contains(n.getName())) {
					nodeToJSON(n);
				}
			}
		} else if (node instanceof Method) {
			obj.put("NodeType", "Method");
			obj.put("NodeName", node.getName());
			obj.put("AgentName", ((Method) node).getAgent());
			obj.put("Quality", node.getQuality());
			obj.put("Duration", ((Method) node).getDuration());
			JSONArray visibleTo = new JSONArray();
			for (String name : node.getVisibleToAgents()) {
				visibleTo.put(name);
			}
			obj.put("VisibleToAgents", visibleTo);
			obj.put("VisibleToAgents", visibleTo);
			nodeArray.put(obj);
			writtenNodes.add(node.getName());
			relationshipsToJSON(node.getRelationships());
		}
	}
	
	private void relationshipsToJSON(ArrayList<NodeRelationship> relationships) throws JSONException {
		for (NodeRelationship nr : relationships) {
			if (!writtenRelationships.contains(nr.getName())) {
				JSONObject obj = new JSONObject();
				obj.put("RelationshipName", nr.getName());
				obj.put("Source", nr.getFromNode().getName());
				obj.put("Destination", nr.getToNode().getName());
				HashSet<String> visibleToSet = new HashSet<String>();
				visibleToSet.addAll(nr.getToNode().getVisibleToAgents());
				visibleToSet.addAll(nr.getFromNode().getVisibleToAgents());
				JSONArray visibleTo = new JSONArray();
				for (String name : visibleToSet) {
					visibleTo.put(name);
				}
				obj.put("VisibleToAgents", visibleTo);
				if (nr instanceof DisablesNodeRelationship) {
					obj.put("RelationshipType", "Disables");
				} else if (nr instanceof EnablesNodeRelationship) {
					obj.put("RelationshipType", "Enables");
				} else if (nr instanceof FacilitatesNodeRelationship) {
					obj.put("RelationshipType", "Facilitates");
					obj.put("QualityFactor", ((FacilitatesNodeRelationship) nr).getQualityFactor());
					obj.put("DurationFactor", ((FacilitatesNodeRelationship) nr).getDurationFactor());
				} else if (nr instanceof HindersNodeRelationship) {
					obj.put("RelationshipType", "Hinders");
					obj.put("QualityFactor", ((HindersNodeRelationship) nr).getQualityFactor());
					obj.put("DurationFactor", ((HindersNodeRelationship) nr).getDurationFactor());
				}
				relationshipArray.put(obj);
				writtenRelationships.add(nr.getName());
				if (!writtenNodes.contains(nr.getToNode().getName())) {
					nodeToJSON(nr.getToNode());
				}
				if (!writtenNodes.contains(nr.getFromNode().getName())) {
					nodeToJSON(nr.getFromNode());
				}
			}
		}
	}
}
