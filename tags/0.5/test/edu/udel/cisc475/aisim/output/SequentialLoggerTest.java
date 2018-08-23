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
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Task;

public class SequentialLoggerTest {

	SequentialLogger seqLog1;
	Agent a;
	Agent b;
	HashMap<String, Agent> agents;
	Method m;
	Task t;
	Socket socket1;
	Socket socket2;
	Message mess;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("SequentialLoggerTest");
	}
	
	
	
	@Before
	public void setUp() {
		seqLog1 = new SequentialLogger("","TestLogFile");
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
	     mess = new AskMethodStatusMessage("aa","bb");
	     
	}

	@Test
	public void testLogMessageReceived() {
		assertTrue(seqLog1.LogMessageReceived(mess));
	}

	@Test
	public void testLogMessageSent() {
		assertTrue(seqLog1.LogMessageSent(mess));
	}
	
}

