package edu.udel.cisc475.aisim.tasktree;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TaskTest {
	
	Task task1;
	Task task2;
	Task task3;
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("TaskTest");
    }

    @Before
    public void setUp()
    {
    	task1 = new Task(null, "Task1");
    	task2 = new Task(task1, "Task2");
    	task3 = new Task(task1, "Task3");
    	task1.addSubTask(task2);
    	task1.addSubTask(task3);
    }
    
    @Test
    public void testSetQAF()
    {
    	task1.setQaf("q_and");
    	assertEquals(Task.QAF.AND, task1.getQaf());
    	
    	task2.setQaf("q_or");
    	assertEquals(Task.QAF.OR, task2.getQaf());
    	
    	task3.setQaf("q_sum");
    	assertEquals(Task.QAF.SUM, task3.getQaf());
    }
    
    @Test
    public void testCalculateQualityFromChildren()
    {
    	task1 = new Task(null, "Task1");
    	task2 = new Task(task1, "Task2");
    	task3 = new Task(task1, "Task3");
    	task1.addSubTask(task2);
    	task1.addSubTask(task3);
    	
    	task1.setQaf("q_and");
    	task2.setQaf("q_or");
    	task3.setQaf("q_sum");
    	
    	Method m1 = new Method(task2, "m1", "a1");
    	Method m2 = new Method(task2, "m2", "a2");
    	Method m3 = new Method(task3, "m3", "a3");
    	Method m4 = new Method(task3, "m4", "a4");
    	
    	task2.addSubTask(m1);
    	task2.addSubTask(m2);
    	task3.addSubTask(m3);
    	task3.addSubTask(m4);
    	
    	m1.setQuality(10.0D);
    	m2.setQuality(5.0D);
    	m3.setQuality(10.0D);
    	m4.setQuality(15.0D);
    	
    	m1.setFinished(true);
    	m2.setFinished(true);
    	m3.setFinished(true);
    	m4.setFinished(true);
    	
    	task1.calculateQualityFromChildren();
    	
    	assertEquals(10.0D, task2.getQuality(), 0.0001D);
    	assertEquals(25.0D, task3.getQuality(), 0.0001D);
    	assertEquals(10.0D, task1.getQuality(), 0.0001D);
    	
    	task1.setQuality(0.0D);
    	task2.setQuality(0.0D);
    	task3.setQuality(0.0D);
    	
    	m1.setFinished(true);
    	m2.setFinished(false);
    	m3.setFinished(false);
    	m4.setFinished(false);
    	
    	task1.calculateQualityFromChildren();
    	
    	assertEquals(10.0D, task2.getQuality(), 0.0001D);
    	assertEquals(0.0D, task3.getQuality(), 0.0001D);
    	assertEquals(0.0D, task1.getQuality(), 0.0001D);
    	
    	task1.setQuality(0.0D);
    	task2.setQuality(0.0D);
    	task3.setQuality(0.0D);
    	
    	m1.setFinished(false);
    	m2.setFinished(false);
    	m3.setFinished(false);
    	m4.setFinished(false);
    	
    	task1.calculateQualityFromChildren();
    	
    	assertEquals(0.0D, task2.getQuality(), 0.0001D);
    	assertEquals(0.0D, task3.getQuality(), 0.0001D);
    	assertEquals(0.0D, task1.getQuality(), 0.0001D);
    	
    	task1.setQaf("q_or");
    	task2.setQaf("q_and");
    	task3.setQaf("q_sum");
    	
    	task1.setQuality(0.0D);
    	task2.setQuality(0.0D);
    	task3.setQuality(0.0D);
    	
    	m1.setFinished(true);
    	m2.setFinished(true);
    	m3.setFinished(true);
    	m4.setFinished(true);
    	
    	task1.calculateQualityFromChildren();
    	
    	assertEquals(5.0D, task2.getQuality(), 0.0001D);
    	assertEquals(25.0D, task3.getQuality(), 0.0001D);
    	assertEquals(25.0D, task1.getQuality(), 0.0001D);
    	
    	task1.setQaf("q_sum");
    	task2.setQaf("q_or");
    	task3.setQaf("q_and");
    	
    	task1.setQuality(0.0D);
    	task2.setQuality(0.0D);
    	task3.setQuality(0.0D);
    	
    	task1.calculateQualityFromChildren();
    	
    	assertEquals(10.0D, task2.getQuality(), 0.0001D);
    	assertEquals(10.0D, task3.getQuality(), 0.0001D);
    	assertEquals(20.0D, task1.getQuality(), 0.0001D);
    	
    	task1.setQuality(0.0D);
    	task2.setQuality(0.0D);
    	task3.setQuality(0.0D);
    	
    	m1.setFinished(false);
    	m2.setFinished(false);
    	m3.setFinished(false);
    	m4.setFinished(false);
    	
    	task1.calculateQualityFromChildren();
    	
    	assertEquals(0.0D, task2.getQuality(), 0.0001D);
    	assertEquals(0.0D, task3.getQuality(), 0.0001D);
    	assertEquals(0.0D, task1.getQuality(), 0.0001D);
    	
    	task1.setQaf("q_or");
    	task2.setQaf("q_sum");
    	task3.setQaf("q_and");
    	
    	task1.setQuality(0.0D);
    	task2.setQuality(0.0D);
    	task3.setQuality(0.0D);
    	
    	m1.setQuality(10.0D);
    	m2.setQuality(5.0D);
    	m3.setQuality(10.0D);
    	m4.setQuality(15.0D);
    	
    	m1.setFinished(true);
    	m2.setFinished(true);
    	m3.setFinished(true);
    	m4.setFinished(true);
    	
    	task1.calculateQualityFromChildren();
    	
    	assertEquals(15.0D, task2.getQuality(), 0.0001D);
    	assertEquals(10.0D, task3.getQuality(), 0.0001D);
    	assertEquals(15.0D, task1.getQuality(), 0.0001D);
    	
    	task1 = new Task(null, "T1");
    	task1.calculateQualityFromChildren();
    	assertEquals(0.0D, task1.getQuality(), 0.0001D);
    }
    
    @Test
    public void testCheckIsFinished() {
    	task1 = new Task(null, "Task1");
    	task2 = new Task(task1, "Task2");
    	task3 = new Task(task1, "Task3");
    	task1.addSubTask(task2);
    	task1.addSubTask(task3);
    	
    	Method m1 = new Method(task2, "m1", "a1");
    	Method m2 = new Method(task2, "m2", "a2");
    	Method m3 = new Method(task3, "m3", "a3");
    	Method m4 = new Method(task3, "m4", "a4");
    	
    	task2.addSubTask(m1);
    	task2.addSubTask(m2);
    	task3.addSubTask(m3);
    	task3.addSubTask(m4);
    	
    	m1.setFinished(true);
    	m2.setFinished(true);
    	m3.setFinished(true);
    	m4.setFinished(true);
    	
    	task1.checkIsFinished();
    	
    	assertTrue(task1.isFinished());
    	assertTrue(task2.isFinished());
    	assertTrue(task3.isFinished());
    	
    	m1.setFinished(false);
    	m2.setFinished(true);
    	m3.setFinished(true);
    	m4.setFinished(true);
    	
    	task1.checkIsFinished();
    	
    	assertFalse(task1.isFinished());
    	assertFalse(task2.isFinished());
    	assertTrue(task3.isFinished());
    }
}
