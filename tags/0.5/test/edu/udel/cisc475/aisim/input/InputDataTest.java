package edu.udel.cisc475.aisim.input;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

import edu.udel.cisc475.aisim.tasktree.TaskTree;

public class InputDataTest {
	
	InputData inputData;

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("InputDataTest");
	}

	@Test
	public void basicConstructorTest() {
		TaskTree t = new TaskTree();
		HashSet<String> a = new HashSet<String>();
		inputData = new InputData(t, "1.0", a);

		assertEquals(inputData.getTree(), t);
		assertEquals(inputData.getVersion(), "1.0");
		assertEquals(inputData.getAgents(), a);
	}
}
