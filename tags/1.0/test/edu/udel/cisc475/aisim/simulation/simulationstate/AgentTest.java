package edu.udel.cisc475.aisim.simulation.simulationstate;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.Task;
import edu.udel.cisc475.aisim.tasktree.TaskTree;


public class AgentTest {
	

	Agent a;
	Socket socket;
	Task t1;
	Task t2;
	Method m1;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("AgentTest");
	}
	
	
	
	@Before
	public void setUp() {
		//create a socket and an agent
		try {
			socket = new Socket("cisc475-6.cis.udel.edu", 80);
			a = new Agent("A1",socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// build a tree to test agents use of tasktree
		t1 = new Task(null, "t1");
		t2 = new Task(t1, "t2");
		m1 = new Method(t2, "m1", "a1");	
		t1.addSubTask(t2);
		t2.addSubTask(m1);

		
	}
	
	@After
	public void tearDown() {
		
	}
	

	@Test
	public void testAgentName() {
		Assert.assertEquals(a.getName(),"A1");

	}
	
	@Test
	public void testTaskTree() {
		TaskTree tree = new TaskTree();
		tree.newHead(t1);
		a.setVisibleTree(tree);
		Assert.assertEquals(a.getVisibleTree(),tree);


	}
	
	@Test
	public void testSocket() {
		Assert.assertEquals(a.getSocket(),socket);
	}
	
	@Test
	public void testHasNextMessage() {
		try {
			assertTrue(!a.hasNextMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}

