package edu.udel.cisc475.aisim.tasktree;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DisablesNodeRelationshipTest {
	
	Method m1;
	Method m2;

	DisablesNodeRelationship r;
	
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("DisablesNodeRelationshipTest");
	}
	
	@Before
	public void setUp() {
		m1 = new Method(null, "m1", "a1");
		m2 = new Method(null, "m2", "a1");
		r = new DisablesNodeRelationship(m1, m2, "r");
	}
	
	@Test
	public void testEvaluate() {
		assertTrue(m1.isEnabled());
		assertTrue(m2.isEnabled());
		
		r.evaluate();
		
		assertTrue(m1.isEnabled());
		assertFalse(m2.isEnabled());
	}
}
