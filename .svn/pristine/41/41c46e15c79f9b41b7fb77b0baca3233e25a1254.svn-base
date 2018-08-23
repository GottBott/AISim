package edu.udel.cisc475.aisim.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.DisablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.EnablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Task;

public class OrderedLoggerTest {

	OrderedLogger ordLog1;
	OrderedLogger ordLog2;

	Agent a;
	HashMap<String, Agent> agents;
	Method m1;
	Method m2;
	Task t;
	Socket socket;
	Message mess;
	NodeRelationship nr1;
	NodeRelationship nr2;

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("SequentialLoggerTest");
	}

	@Before
	public void setUp() {
		ordLog1 = new OrderedLogger("", "TestLogFile");

		try {
			socket = new Socket("cisc475-6.cis.udel.edu", 80);
			a = new Agent("A1", socket);
		} catch (IOException e) {
			e.printStackTrace();
		}

		agents = new HashMap<String, Agent>();
		agents.put("Agent1", a);

		Task t = new Task(null, "task");
		m1 = new Method(t, "a", "b");
		m2 = new Method(t, "a", "b");
	}

	@Test
	public void testAddAgent() {
		assertTrue(ordLog1.addAgent(a));
		assertNotNull(ordLog1.agents);
		assertEquals(1, ordLog1.agents.size());

	}

	@Test
	public void testAddRelationship() {
		nr1 = new DisablesNodeRelationship(m1, m2, "Disables");
		assertTrue(ordLog1.addRelationship(nr1));
		assertNotNull(ordLog1.nodeRelations);
		assertEquals(1, ordLog1.nodeRelations.size());

	}

	@Test
	public void testAddMethod() {
		assertTrue(ordLog1.addMethod(m1));
		assertTrue(ordLog1.addMethod(m2));
		assertNotNull(ordLog1.methods);
		assertEquals(2, ordLog1.methods.size());

	}

	@Test
	public void testLogAll() {
		assertTrue(ordLog1.logAll(50.0, 100.0,500L));
		assertEquals(0, ordLog1.methods.size());
		assertEquals(0, ordLog1.nodeRelations.size());
		assertEquals(0, ordLog1.agents.size());

	}
	
	@Test
	public void testgetnodeRelations() {
		nr1 = new DisablesNodeRelationship(m1, m2, "Disables");
		assertTrue(ordLog1.addRelationship(nr1));
		
		assertEquals(1, ordLog1.getNodeRelations().size());

		nr2 = new EnablesNodeRelationship(m1, m2, "Enables");
		assertTrue(ordLog1.addRelationship(nr2));
		
		assertEquals(2, ordLog1.getNodeRelations().size());
		
		assertTrue(ordLog1.getNodeRelations().contains(nr1));
		assertTrue(ordLog1.getNodeRelations().contains(nr2));
		


		
	}

}
