package edu.udel.cisc475.aisim.tasktree;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EnablesNodeRelationshipTest {
	
	Method m1;
	Task t1;
	Task t2;
	Method m2;
	Method m3;

	EnablesNodeRelationship r;
	
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("EnablesNodeRelationshipTest");
	}
	
	@Before
	public void setUp() {
		m1 = new Method(null, "m1", "a1");
		t1 = new Task(null, "t1");
		t2 = new Task(t1, "t2");
		m2 = new Method(t1, "m2", "a1");
		m3 = new Method(t2, "m3", "a1");
		t1.addSubTask(t2);
		t1.addSubTask(m2);
		t2.addSubTask(m3);
		r = new EnablesNodeRelationship(m1, t1, "r");
		m2.disable();
		m3.disable();
	}
	
	@Test
	public void testEvaluate() {
		assertTrue(m1.isEnabled());
		assertFalse(m2.isEnabled());
		assertFalse(m3.isEnabled());
		
		r.evaluate();
		
		assertTrue(m1.isEnabled());
		assertTrue(m2.isEnabled());
		assertTrue(m3.isEnabled());
	}
}
