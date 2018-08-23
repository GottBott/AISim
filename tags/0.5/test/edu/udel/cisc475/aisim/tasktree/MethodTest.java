package edu.udel.cisc475.aisim.tasktree;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class MethodTest {
	
	Method m;
	Task t;
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("MethodTest");
    }
	
	@Before
	public void setUp() {
		m = new Method(null, "M", "A");
		t = new Task(null, "T");
	}
	
	@Test
	public void testMethodGetSet() {
		Assert.assertEquals("A", m.getAgent());
		Assert.assertEquals(0, m.getDuration());
		Assert.assertFalse(m.isStarted());
		Assert.assertEquals(-1, m.getStartTime());
		Assert.assertEquals("M", m.getName());
		Assert.assertNull(m.getParent());
		
		m.setAgent("B");
		m.setDuration(1);
		m.start();
		m.setStartTime(2L);
		m.setName("N");
		m.setParent(t);
		
		Assert.assertEquals("B", m.getAgent());
		Assert.assertEquals(1, m.getDuration());
		Assert.assertTrue(m.isStarted());
		Assert.assertEquals(2, m.getStartTime());
		Assert.assertEquals("N", m.getName());
		Assert.assertEquals(t, m.getParent());
	}
}
