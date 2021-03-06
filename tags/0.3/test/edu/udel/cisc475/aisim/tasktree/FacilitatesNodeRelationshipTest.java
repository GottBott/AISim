package edu.udel.cisc475.aisim.tasktree;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class FacilitatesNodeRelationshipTest {
	
	Method m1;
	Method m2;

	FacilitatesNodeRelationship r;
	
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("FacilitatesNodeRelationshipTest");
	}
	
	@Before
	public void setUp() {
		m1 = new Method(null, "m1", "a1");
		m2 = new Method(null, "m2", "a1");
		r = new FacilitatesNodeRelationship(m1, m2, "r", 10.0D, 5.0D);
		m2.setQuality(5.0D);
		m2.setDuration(10.0D);
	}
	
	@Test
	public void testEvaluate() {
		r.evaluate();
		
		assertEquals(10.0D, m2.getQuality(), 0.0001D);
		assertEquals(5.0D, m2.getDuration(), 0.0001D);
		
		m2.setQuality(5.0D);
		m2.setDuration(10.0D);
		m2.start();
		
		r.evaluate();
		
		assertEquals(5.0D, m2.getQuality(), 0.0001D);
		assertEquals(10.0D, m2.getDuration(), 0.0001D);
	}
}
