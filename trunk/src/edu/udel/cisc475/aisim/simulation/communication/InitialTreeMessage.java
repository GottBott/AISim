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

/**
 * This class represents an InitialTreeMessage. This message is sent by the
 * simulator to an agent before the simulation starts. It contains the parts of
 * the task tree that the agent can see.
 * 
 * @author Mike
 *
 */
public class InitialTreeMessage extends Message {
	/**
	 * The TaskTree this message is based off of.
	 */
	private TaskTree tree;

	/**
	 * The name of the agent that this message is intended for.
	 */
	private String agent;

	/**
	 * The JSONArray of nodes that the agent can see.
	 */
	private JSONArray nodeArray;

	/**
	 * The JSONArray of relationships that the agent can see.
	 */
	private JSONArray relationshipArray;

	/**
	 * The set of relationships that have already been converted to JSON.
	 */
	private HashSet<String> writtenRelationships;

	/**
	 * The set of nodes that have already been converted to JSON.
	 */
	private HashSet<String> writtenNodes;

	/**
	 * The default constructor for an InitialTreeMessage.
	 * 
	 * @param destinationName
	 *            The name of the entity that is supposed to receive this
	 *            message.
	 * @param tree
	 *            The TaskTree to base this message off of.
	 */
	public InitialTreeMessage(String destinationName, TaskTree tree) {
		super(Message.SIMULATOR_NAME, destinationName);
		this.tree = tree;
		this.agent = destinationName;
		nodeArray = new JSONArray();
		relationshipArray = new JSONArray();
		writtenRelationships = new HashSet<String>();
		writtenNodes = new HashSet<String>();
		this.messageType = "InitialTreeMessage";
		this.logMessageDetail = "Tree Head: " + tree.getHead().getName();

	}

	public String toJSON() throws JSONException {
		JSONStringer jsonStringer = new JSONStringer();
		jsonStringer.object();
		jsonStringer.key("MessageType");
		jsonStringer.value("InitialTreeMessage");
		jsonStringer.key("Root");
		jsonStringer.value(tree.getHead().getName());
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

	/**
	 * Converts a node to JSON and adds it to the JSONArray of nodes. Also calls
	 * relationshipsToJSON on all relationships on this node. Recurses down to
	 * the node's children if the destination agent can see them.
	 * 
	 * @param node
	 *            The node to be converted to JSON.
	 * @throws JSONException
	 */
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
				if(n instanceof Method && n.getVisibleToAgents().contains(agent)){
					for (Node st : ((Task) node).getSubTasks()){
						subtasks.put(st.getName());
					}
					break;
				}
				else if (n.getVisibleToAgents().contains(agent)) {
					subtasks.put(n.getName());
				}
			}
			obj.put("SubTasks", subtasks);
			nodeArray.put(obj);
			writtenNodes.add(node.getName());
			relationshipsToJSON(node.getRelationships());
			for (Node n : ((Task) node).getSubTasks()) {
				if (n.getVisibleToAgents().contains(agent)
						&& !writtenNodes.contains(n.getName())) {
					nodeToJSON(n);
				}
			}
		} else if (node instanceof Method) {
			obj.put("NodeType", "Method");
			obj.put("SubTasks", new int[0]);
			obj.put("NodeName", node.getName());
			obj.put("AgentName", ((Method) node).getAgent());
			obj.put("Quality", ((Method) node).getQualityDistribution().toString());
			obj.put("Duration", ((Method) node).getDurationDistribution().toString());
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

	/**
	 * Converts all relationships in the given list to JSON and adds them to the
	 * relationship JSONArray. Also calls nodeToJSON on all nodes that are
	 * targets of these relationships.
	 * 
	 * @param relationships
	 *            The list of relationships to convert to JSON.
	 * @throws JSONException
	 */
	private void relationshipsToJSON(ArrayList<NodeRelationship> relationships)
			throws JSONException {
		for (NodeRelationship nr : relationships) {
			if(nr.hasArrived()){
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
						obj.put("QualityFactor", ((FacilitatesNodeRelationship) nr)
								.getQualityFactor());
						obj.put("DurationFactor",
								((FacilitatesNodeRelationship) nr)
										.getDurationFactor());
					} else if (nr instanceof HindersNodeRelationship) {
						obj.put("RelationshipType", "Hinders");
						obj.put("QualityFactor",
								((HindersNodeRelationship) nr).getQualityFactor());
						obj.put("DurationFactor",
								((HindersNodeRelationship) nr).getDurationFactor());
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
}
