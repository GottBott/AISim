package edu.udel.cisc475.aisim.tasktree;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TaskTreeTest {
	
	Task t1;
	Task t2;
	Task t3;
	Method m1;
	Method m2;
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("TaskTreeTest");
    }
	
	@Before
	public void setUp() {
		
		t1 = new Task(null, "t1");
		t2 = new Task(t1, "t2");
		t3 = new Task(t1, "t3");
		m1 = new Method(t2, "m1", "a1");
		m2 = new Method(t3, "m2", "a2");
		
		t1.addSubTask(t2);
		t1.addSubTask(t3);
		t2.addSubTask(m1);
		t3.addSubTask(m2);
	}
	
	@Test
	public void testTaskTree() {
		TaskTree tree = new TaskTree(t1);
		assertEquals(t1, tree.getNodeWithName("t1"));
		assertEquals(t2, tree.getNodeWithName("t2"));
		assertEquals(t3, tree.getNodeWithName("t3"));
		assertEquals(m1, tree.getNodeWithName("m1"));
		assertEquals(m2, tree.getNodeWithName("m2"));
		assertEquals(t1, tree.getHead());
	}
	
	@Test
	public void testNewHead() {
		TaskTree tree = new TaskTree();
		tree.newHead(t1);
		assertEquals(t1, tree.getNodeWithName("t1"));
		assertEquals(t2, tree.getNodeWithName("t2"));
		assertEquals(t3, tree.getNodeWithName("t3"));
		assertEquals(m1, tree.getNodeWithName("m1"));
		assertEquals(m2, tree.getNodeWithName("m2"));
	}
	
	@Test
	public void testAddNode() {
		t1 = new Task(null, "t1");
		t2 = new Task(null, "t2");
		
		TaskTree tree = new TaskTree();
		tree.newHead(t1);
		tree.addNode(t2, "t1");
		tree.addNode(m1, "t4");
		
		assertEquals(t1, tree.getNodeWithName("t1"));
		assertEquals(t2, tree.getNodeWithName("t2"));
		assertEquals(null, tree.getNodeWithName("m1"));
		
		
		tree.addNode(m1, "t2");
		tree.addNode(m2, "m1");
		
		assertEquals(null, tree.getNodeWithName("m2"));
	}
	
	@Test
	public void testIsFinished() {
		m1.enable();
		m1.setFinished(true);
		m2.enable();
		m2.setFinished(true);
		m1.setEarliestStartTime(0);
		m1.setDeadline(5);
		m2.setEarliestStartTime(0);
		m2.setDeadline(5);
		
		TaskTree tree = new TaskTree(t1);
		
		assertTrue(tree.isFinished(0));
		
		m1.setFinished(false);
		
		assertFalse(tree.isFinished(0));
		
		m1.disable();
		
		assertTrue(tree.isFinished(0));
		
		m1.setFinished(true);
		
		assertTrue(tree.isFinished(0));
	}
	
	@Test
	public void testGetTotalQuality() {
		m1.enable();
		m1.setFinished(true);
		m2.enable();
		m2.setFinished(true);
		m1.setQuality(10.0D);
		m2.setQuality(20.0D);
		
		t1.setQaf("q_and");
		t2.setQaf("q_and");
		t3.setQaf("q_and");
		
		TaskTree tree = new TaskTree(t1);
		
		assertEquals(10.0D, tree.getTotalQuality(), 0.0001D);
	}
	
	@Test
	public void testFindVisibleToAgents() {
		EnablesNodeRelationship e = new EnablesNodeRelationship(m1, m2, "e");
		m1.addRelationship(e);
		TaskTree tree = new TaskTree(t1);
		tree.findVisibleToAgents();
		
		assertTrue(m1.getVisibleToAgents().contains("a1"));
		assertTrue(m1.getVisibleToAgents().contains("a2"));
		
		assertTrue(m2.getVisibleToAgents().contains("a1"));
		assertTrue(m2.getVisibleToAgents().contains("a2"));
		
		assertTrue(t2.getVisibleToAgents().contains("a1"));
		assertFalse(t2.getVisibleToAgents().contains("a2"));
		
		assertFalse(t3.getVisibleToAgents().contains("a1"));
		assertTrue(t3.getVisibleToAgents().contains("a2"));
		
		assertTrue(t1.getVisibleToAgents().contains("a1"));
		assertTrue(t1.getVisibleToAgents().contains("a2"));
	}
	
	@Test
	public void testActivateAllRelationships() {
		DisablesNodeRelationship e = new DisablesNodeRelationship(t3, m1, "e");
		t3.addRelationship(e);
		m2.setFinished(true);
		TaskTree tree = new TaskTree(t1);
		
		assertTrue(m1.isEnabled());
		tree.activateAllRelationships();
		assertFalse(m1.isEnabled());
		
		
		//Make sure it can't get activated twice
		m1.enable();
		assertTrue(m1.isEnabled());
		tree.activateAllRelationships();
		assertTrue(m1.isEnabled());
	}
	
	@Test
	public void testComputeEarliestStartAndDeadline() {
		t1.setEarliestStartTime(5);
		t1.setDeadline(10);
		t2.setEarliestStartTime(3);
		t2.setDeadline(15);
		t3.setEarliestStartTime(7);
		t3.setDeadline(5);
		
		TaskTree tree = new TaskTree(t1);
		tree.computeEarliestStartAndDeadline();
		
		assertEquals(5, t1.getEarliestStartTime());
		assertEquals(10, t1.getDeadline());
		assertEquals(5, t2.getEarliestStartTime());
		assertEquals(10, t2.getDeadline());
		assertEquals(7, t3.getEarliestStartTime());
		assertEquals(5, t3.getDeadline());
		assertEquals(5, m1.getEarliestStartTime());
		assertEquals(10, m1.getDeadline());
		assertEquals(7, m2.getEarliestStartTime());
		assertEquals(5, m2.getDeadline());
	}
}
