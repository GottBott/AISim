package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class DistributionTest {
	
	Distribution d1;
	Distribution d2;
	Distribution d3;
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("DistributionTest");
    }
	
	@Before
	public void setUp() {
		ArrayList<Double> v1 = new ArrayList<Double>();
		ArrayList<Double> p1 = new ArrayList<Double>();
		ArrayList<Double> v2 = new ArrayList<Double>();
		ArrayList<Double> p2 = new ArrayList<Double>();
		ArrayList<Double> v3 = new ArrayList<Double>();
		ArrayList<Double> p3 = new ArrayList<Double>();
		
		v1.add(1D);
		
		p1.add(1D);
		
		v2.add(1D);
		v2.add(2D);
		v2.add(3D);
		v2.add(4D);
		
		p2.add(.25);
		p2.add(.25);
		p2.add(.25);
		p2.add(.25);
		
		d1 = new Distribution(v1, p1);
		d2 = new Distribution(v2, p2);
		d3 = new Distribution(v3, p3);
	}
	
	@Test
	public void testEvaluate() {
		double val = d1.evaluate(.5);
		Assert.assertEquals(1, val, 0.0001);
		val = d1.evaluate(2);
		Assert.assertEquals(-1, val, 0.0001);
		val = d1.evaluate(1);
		Assert.assertEquals(-1, val, 0.0001);
		
		val = d2.evaluate(0);
		Assert.assertEquals(1, val, 0.0001);
		val = d2.evaluate(.3);
		Assert.assertEquals(2, val, 0.0001);
		val = d2.evaluate(.5);
		Assert.assertEquals(3, val, 0.0001);
		val = d2.evaluate(.9);
		Assert.assertEquals(4, val, 0.0001);
		
		val = d3.evaluate(.1);
		Assert.assertEquals(-1, val, 0.0001);
	}
}
