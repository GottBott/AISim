package edu.udel.cisc475.aisim.output;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.output.SequentialLogger;
import edu.udel.cisc475.aisim.simulation.communication.AskMethodStatusMessage;
import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.DisablesNodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;
import edu.udel.cisc475.aisim.tasktree.Task;

public class SequentialLoggerTest {

	SequentialLogger seqLog1;
	Agent a;
	HashMap<String, Agent> agents;
	Method m1;
	Method m2;
	Task t;
	Socket socket1;
	Message mess;
	NodeRelationship nr;

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("SequentialLoggerTest");
	}

	@Before
	public void setUp() {
		seqLog1 = new SequentialLogger("", "TestLogFile");
		try {
			socket1 = new Socket("cisc475-6.cis.udel.edu", 80);
			a = new Agent("A1", socket1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		agents = new HashMap<String, Agent>();
		agents.put("Agent1", a);

		Task t = new Task(null, "task");
		m1 = new Method(t, "a", "b");
		m2 = new Method(t, "a", "b");
		mess = new AskMethodStatusMessage("aa", "bb");

	}

	@Test
	public void testLogRelationship() {
		nr = new DisablesNodeRelationship(m1, m2, "Disables");
		assertTrue(seqLog1.logRelationship(nr));
	}

	@Test
	public void testLogAgent() {
		assertTrue(seqLog1.logAgent(a));
	}
	
	@Test
	public void testLogMethod(){
		assertTrue(seqLog1.logMethod(m1));
	}
	
	@Test
	public void testLogMessage(){
		assertTrue(seqLog1.logMessage(mess));
	}

}
