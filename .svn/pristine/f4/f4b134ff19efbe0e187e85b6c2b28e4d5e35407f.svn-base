package edu.udel.cisc475.aisim.tasktree;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class FacilitatesNodeRelationshipTest {
	
	Method m1;
	Task t1;
	Task t2;
	Method m2;
	Method m3;

	FacilitatesNodeRelationship r;
	
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("FacilitatesNodeRelationshipTest");
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
		r = new FacilitatesNodeRelationship(m1, t1, "r", .5D, .5D);
		m2.setQuality(5.0D);
		m2.setDuration(10);
		m3.setQuality(10.0D);
		m3.setDuration(5);
	}
	
	@Test
	public void testEvaluate() {
		r.evaluate();
		
		assertEquals(7.5D, m2.getQuality(), 0.0001D);
		assertEquals(5, m2.getDuration());
		assertEquals(15.0D, m3.getQuality(), 0.0001D);
		assertEquals(2, m3.getDuration());
		
		m2.setQuality(5.0D);
		m2.setDuration(10);
		m3.setQuality(10.0D);
		m3.setDuration(5);
		m2.start();
		m3.start();
		
		r.evaluate();
		
		assertEquals(5.0D, m2.getQuality(), 0.0001D);
		assertEquals(10, m2.getDuration());
		assertEquals(10.0D, m3.getQuality(), 0.0001D);
		assertEquals(5, m3.getDuration());
	}
}
