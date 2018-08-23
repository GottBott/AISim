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
		m2 = new Method(t3, "m2", "a1");
		
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
		
		TaskTree tree = new TaskTree();
		tree.newHead(t1);
		tree.addNode(t2, "t1");
		tree.addNode(t3, "t1");
		
		assertEquals(t1, tree.getNodeWithName("t1"));
		assertEquals(t2, tree.getNodeWithName("t2"));
		assertEquals(t3, tree.getNodeWithName("t3"));
		assertEquals(m1, tree.getNodeWithName("m1"));
		assertEquals(m2, tree.getNodeWithName("m2"));
	}
	
	@Test
	public void testIsFinished() {
		m1.enable();
		m1.setFinished(true);
		m2.enable();
		m2.setFinished(true);
		
		TaskTree tree = new TaskTree(t1);
		
		assertTrue(tree.isFinished());
		
		m1.setFinished(false);
		
		assertFalse(tree.isFinished());
		
		m1.disable();
		
		assertTrue(tree.isFinished());
		
		m1.setFinished(true);
		
		assertTrue(tree.isFinished());
	}
}
