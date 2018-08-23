package edu.udel.cisc475.aisim.simulation.communication;

import org.json.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.tasktree.DisablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.EnablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.FacilitatesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.HindersNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Task;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

public class NewNodeMessageTest {
	Task task1;
	Task task2;
	Task task3;
	Method method1;
	Method method2;
	Method method3;
	Method method4;
	NodeRelationship hinders;
	NodeRelationship facilitates;
	NodeRelationship enables;
	NodeRelationship disables;
	
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("NewNodeMessageTest");
    }
	
	@Before
	public void setUp() {
		task1 = new Task(null, "T1");
		task2 = new Task(task1, "T2");
		task3 = new Task(task1, "T3");
		task1.setQaf("q_sum");
		task2.setQaf("q_and");
		task3.setQaf("q_or");
		method1 = new Method(task2, "M1", "A1");
		method2 = new Method(task2, "M2", "A2");
		method3 = new Method(task3, "M3", "A3");
		method4 = new Method(task3, "M4", "A4");
		task1.addSubTask(task2);
		task1.addSubTask(task3);
		task2.addSubTask(method1);
		task2.addSubTask(method2);
		task3.addSubTask(method3);
		task3.addSubTask(method4);
		
		task1.setEarliestStartTime(1);
		task2.setEarliestStartTime(2);
		task3.setEarliestStartTime(3);
		task1.setDeadline(10);
		task2.setDeadline(20);
		task3.setDeadline(30);
		
		method1.setQuality(1);
		method2.setQuality(2);
		method3.setQuality(3);
		method4.setQuality(4);
		method1.setDuration(10);
		method2.setDuration(20);
		method3.setDuration(30);
		method4.setDuration(40);
		
		hinders = new HindersNodeRelationship(method1, method2, "H", 4, 5);
		facilitates = new FacilitatesNodeRelationship(method2, method3, "F", 2, 3);
		enables = new EnablesNodeRelationship(method3, method4, "E");
		disables = new DisablesNodeRelationship(method4, method1, "D");
		
		method1.addRelationship(hinders);
		method2.addRelationship(facilitates);
		method3.addRelationship(enables);
		method4.addRelationship(disables);
	}
	
	@Test
	public void testToJSON() {
		NewNodeMessage message = new NewNodeMessage("SIMULATOR", "Agent 1", task1);
		String JSON = message.toJSON();
		
		System.out.println(JSON);
		
		JSONObject obj = new JSONObject(JSON);
		
		String messageType = obj.getString("MessageType");
		Assert.assertEquals("NewNodeMessage", messageType);
		
		JSONObject messageObj = obj.getJSONObject("Message");
		
		String sender = messageObj.getString("MsgSender");
		Assert.assertEquals("SIMULATOR", sender);
		String dest = messageObj.getString("MsgDest");
		Assert.assertEquals("Agent 1", dest);
		
		//Task1
		JSONObject nodeObj1 = messageObj.getJSONObject("Node");
		
		String type = nodeObj1.getString("NodeType");
		Assert.assertEquals("Task", type);
		String name = nodeObj1.getString("NodeName");
		Assert.assertEquals("T1", name);
		String qaf = nodeObj1.getString("QAF");
		Assert.assertEquals("SUM", qaf);
		int earliestStart = nodeObj1.getInt("EarliestStartTime");
		Assert.assertEquals(1, earliestStart);
		int deadline = nodeObj1.getInt("Deadline");
		Assert.assertEquals(10, deadline);
		JSONArray relationsArray = nodeObj1.getJSONArray("Relationships");
		Assert.assertEquals(0, relationsArray.length());
		JSONArray subtasksArray = nodeObj1.getJSONArray("SubTasks");
		
		//Task2
		JSONObject nodeObj2 = subtasksArray.getJSONObject(0);
		
		type = nodeObj2.getString("NodeType");
		Assert.assertEquals("Task", type);
		name = nodeObj2.getString("NodeName");
		Assert.assertEquals("T2", name);
		qaf = nodeObj2.getString("QAF");
		Assert.assertEquals("AND", qaf);
		earliestStart = nodeObj2.getInt("EarliestStartTime");
		Assert.assertEquals(2, earliestStart);
		deadline = nodeObj2.getInt("Deadline");
		Assert.assertEquals(20, deadline);
		relationsArray = nodeObj2.getJSONArray("Relationships");
		Assert.assertEquals(0, relationsArray.length());
		
		//Task 3
		JSONObject nodeObj3 = subtasksArray.getJSONObject(1);
		
		type = nodeObj3.getString("NodeType");
		Assert.assertEquals("Task", type);
		name = nodeObj3.getString("NodeName");
		Assert.assertEquals("T3", name);
		qaf = nodeObj3.getString("QAF");
		Assert.assertEquals("OR", qaf);
		earliestStart = nodeObj3.getInt("EarliestStartTime");
		Assert.assertEquals(3, earliestStart);
		deadline = nodeObj3.getInt("Deadline");
		Assert.assertEquals(30, deadline);
		relationsArray = nodeObj3.getJSONArray("Relationships");
		Assert.assertEquals(0, relationsArray.length());
		
		subtasksArray = nodeObj2.getJSONArray("SubTasks");

		//Method 1
		JSONObject nodeObj4 = subtasksArray.getJSONObject(0);
		
		type = nodeObj4.getString("NodeType");
		Assert.assertEquals("Method", type);
		name = nodeObj4.getString("NodeName");
		Assert.assertEquals("M1", name);
		String agentName = nodeObj4.getString("AgentName");
		Assert.assertEquals("A1", agentName);
		double quality = nodeObj4.getDouble("Quality");
		Assert.assertEquals(1, quality, 0.00001);
		int duration = nodeObj4.getInt("Duration");
		Assert.assertEquals(10, duration);
		relationsArray = nodeObj4.getJSONArray("Relationships");
		
		JSONObject relationObj = relationsArray.getJSONObject(0);
		
		name = relationObj.getString("RelationshipName");
		Assert.assertEquals("H", name);
		String source = relationObj.getString("Source");
		Assert.assertEquals("M1", source);
		dest = relationObj.getString("Destination");
		Assert.assertEquals("M2", dest);
		type = relationObj.getString("RelationshipType");
		Assert.assertEquals("Hinders", type);
		quality = relationObj.getDouble("NewQuality");
		Assert.assertEquals(4, quality, 0.0001);
		duration = relationObj.getInt("NewDuration");
		Assert.assertEquals(5, duration);
		
		//Method 2
		JSONObject nodeObj5 = subtasksArray.getJSONObject(1);

		type = nodeObj5.getString("NodeType");
		Assert.assertEquals("Method", type);
		name = nodeObj5.getString("NodeName");
		Assert.assertEquals("M2", name);
		agentName = nodeObj5.getString("AgentName");
		Assert.assertEquals("A2", agentName);
		quality = nodeObj5.getDouble("Quality");
		Assert.assertEquals(2, quality, 0.00001);
		duration = nodeObj5.getInt("Duration");
		Assert.assertEquals(20, duration);
		relationsArray = nodeObj5.getJSONArray("Relationships");

		relationObj = relationsArray.getJSONObject(0);

		name = relationObj.getString("RelationshipName");
		Assert.assertEquals("F", name);
		source = relationObj.getString("Source");
		Assert.assertEquals("M2", source);
		dest = relationObj.getString("Destination");
		Assert.assertEquals("M3", dest);
		type = relationObj.getString("RelationshipType");
		Assert.assertEquals("Facilitates", type);
		quality = relationObj.getDouble("NewQuality");
		Assert.assertEquals(2, quality, 0.0001);
		duration = relationObj.getInt("NewDuration");
		Assert.assertEquals(3, duration);
		
		subtasksArray = nodeObj3.getJSONArray("SubTasks");
		
		//Method 3
		JSONObject nodeObj6 = subtasksArray.getJSONObject(0);

		type = nodeObj6.getString("NodeType");
		Assert.assertEquals("Method", type);
		name = nodeObj6.getString("NodeName");
		Assert.assertEquals("M3", name);
		agentName = nodeObj6.getString("AgentName");
		Assert.assertEquals("A3", agentName);
		quality = nodeObj6.getDouble("Quality");
		Assert.assertEquals(3, quality, 0.00001);
		duration = nodeObj6.getInt("Duration");
		Assert.assertEquals(30, duration);
		relationsArray = nodeObj6.getJSONArray("Relationships");

		relationObj = relationsArray.getJSONObject(0);

		name = relationObj.getString("RelationshipName");
		Assert.assertEquals("E", name);
		source = relationObj.getString("Source");
		Assert.assertEquals("M3", source);
		dest = relationObj.getString("Destination");
		Assert.assertEquals("M4", dest);
		type = relationObj.getString("RelationshipType");
		Assert.assertEquals("Enables", type);
		
		//Method 4
		JSONObject nodeObj7 = subtasksArray.getJSONObject(1);

		type = nodeObj7.getString("NodeType");
		Assert.assertEquals("Method", type);
		name = nodeObj7.getString("NodeName");
		Assert.assertEquals("M4", name);
		agentName = nodeObj7.getString("AgentName");
		Assert.assertEquals("A4", agentName);
		quality = nodeObj7.getDouble("Quality");
		Assert.assertEquals(4, quality, 0.00001);
		duration = nodeObj7.getInt("Duration");
		Assert.assertEquals(40, duration);
		relationsArray = nodeObj7.getJSONArray("Relationships");

		relationObj = relationsArray.getJSONObject(0);

		name = relationObj.getString("RelationshipName");
		Assert.assertEquals("D", name);
		source = relationObj.getString("Source");
		Assert.assertEquals("M4", source);
		dest = relationObj.getString("Destination");
		Assert.assertEquals("M1", dest);
		type = relationObj.getString("RelationshipType");
		Assert.assertEquals("Disables", type);
	}
	
}
