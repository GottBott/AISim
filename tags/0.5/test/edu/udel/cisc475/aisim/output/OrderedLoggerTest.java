package edu.udel.cisc475.aisim.output;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.output.SequentialLogger;
import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Task;

public class OrderedLoggerTest {
	
	OrderedLogger ordLog1;
	Agent a;
	Agent b;
	HashMap<String, Agent> agents;
	Method m;
	Task t;
	Socket socket1;
	Socket socket2;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("SequentialLoggerTest");
	}
	
	
	
	@Before
	public void setUp() {
		ordLog1 = new OrderedLogger("","TestLogFile");
		
		try {
			socket1 = new Socket("cisc475-6.cis.udel.edu", 80);
			a = new Agent("A1",socket1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socket2 = new Socket("cisc475-6.cis.udel.edu", 80);
			b = new Agent("A1",socket2);
		} catch (IOException e) {
			e.printStackTrace();
		}


			

		
	    agents = new HashMap<String, Agent>();
		agents.put("Agent1", a);
		agents.put("Agent2", b);
		Task t = new Task(null,"task");
	     m = new Method(t, "a","b");
	}

	@Test
	public void TestLogAgents() {
		assertTrue(ordLog1.LogAgents(agents));

	}
	
	@Test
	public void TestStartLog() {
		assertTrue(ordLog1.StartLog());

	}
	
	@Test
	public void TestLogMethodEvent() {
		assertTrue(ordLog1.LogMethodEvent(m));

	}
	

}
