package edu.udel.cisc475.aisim.simulation.simulationstate;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

import org.junit.Test;

import edu.udel.cisc475.aisim.input.ConfigurationData;
import edu.udel.cisc475.aisim.input.InputData;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Task;
import edu.udel.cisc475.aisim.tasktree.TaskTree;
import edu.udel.cisc475.aisim.simulation.communication.AgentRegistrationMessage;
import edu.udel.cisc475.aisim.simulation.communication.AskMethodStatusMessage;
import edu.udel.cisc475.aisim.simulation.communication.StartMethodMessage;
import edu.udel.cisc475.aisim.simulation.simulationstate.Simulator;


public class SimulatorTest {
	
	static class TestConnection {
		Socket socket;
		PrintWriter writer;
		BufferedReader reader;
		int port;
		
		TestConnection(int port) {
			this.port = port;
		}
		
		void connect() {
			try {
				this.socket = new Socket("127.0.0.1", port);
				this.writer = new PrintWriter(socket.getOutputStream(), true);
				this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		void close() {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	Simulator sim;
	ConfigurationData configData;
	InputData inputData;
	
	Task t1;
	Task t2;
	Method m1;
	Method m2;
	String agentReg;
	
	HashSet<String> hs;
	static TestConnection c1;
	Agent a1;
	
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("SimulatorTest");
	}
	



	// testing that the simulation exists nicely once the tree has been completed.
	@Test
	public void testSimFinishesTreeDone() {
		
		// create a tree structure with only one method
		t1 = new Task(null, "t1");
		m1 = new Method(t1, "m1", "A1");	
		t1.addSubTask(m1);
		m1.setFinished(true);	// method has been completed
		TaskTree tree = new TaskTree();
		tree.newHead(t1);
		
		hs = new HashSet<String>();

		configData = new ConfigurationData(5L, "output", 4L, 9875);
		inputData = new InputData(tree, "v5", hs);
		sim = new Simulator(configData, inputData);
		
		assertTrue(sim.startServer());
	}
	
	
	@Test
	public void testSimOneAgent() {

			c1 = new TestConnection(9875);	
			new Thread() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					c1.connect();
					c1.writer.println(new AgentRegistrationMessage("Agent1").toJSON());
				}
			}.start();
			
		// create a tree structure with only one method
			t1 = new Task(null, "t1");
			m1 = new Method(t1, "m1", "Agent1");
			t1.addSubTask(m1);	
			m1.setFinished(true);	// method has been completed
			TaskTree tree = new TaskTree(t1);
	
			
			hs = new HashSet<String>();
			hs.add("Agent1");

			configData = new ConfigurationData(5L, "output", 4L, 9875);
			inputData = new InputData(tree, "v5", hs);
			sim = new Simulator(configData, inputData);
			
			assertTrue(sim.startServer());
			
			c1.close();

	}
	
	
	@Test
	public void testAgentDoMethod() {
		c1 = new TestConnection(9875);	
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					c1.connect();
					c1.writer.println(new AgentRegistrationMessage("Agent1").toJSON());
					System.out.println(c1.reader.readLine());
					System.out.println(c1.reader.readLine());
					c1.writer.println(new StartMethodMessage("Agent1", "SIMULATOR","m2").toJSON());
					System.out.println(c1.reader.readLine());
					c1.writer.println(new StartMethodMessage("Agent1", "SIMULATOR","m1").toJSON());
					System.out.println(c1.reader.readLine());
					c1.writer.println(new AskMethodStatusMessage("Agent1","m1").toJSON());
					System.out.println(c1.reader.readLine());
					c1.writer.println(new AskMethodStatusMessage("Agent1","m2").toJSON());
					System.out.println(c1.reader.readLine());


					
					
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}.start();
		
	// create a tree structure with only one method
		t1 = new Task(null, "t1");
		t1.setEarliestStartTime(2);
		t1.setQaf("q_sum");
		
		m1 = new Method(t1, "m1", "Agent1");
		m1.setDuration(100);
		m1.setQuality(10);
		m1.setStartTime(2L);
		t1.addSubTask(m1);
		
		m2 = new Method(t1,"m2","Agent1");
		m2.setFinished(true);
		t1.addSubTask(m2);
		TaskTree tree = new TaskTree(t1);

		
		hs = new HashSet<String>();
		hs.add("Agent1");

		configData = new ConfigurationData(5L, "output", 4L, 9875);
		inputData = new InputData(tree, "v5", hs);
		sim = new Simulator(configData, inputData);
		
		assertTrue(sim.startServer());
		
		c1.close();
		
		
	}
	
	

}
