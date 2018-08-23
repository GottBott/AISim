package edu.udel.cisc475.aisim.output;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.output.SequentialLogger;

public class OrderedLoggerTest {
	
	SequentialLogger seqLog1;
	SequentialLogger seqLog2;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("SequentialLoggerTest");
	}
	
	
	
	@Before
	public void setUp() {
		seqLog1 = new SequentialLogger("","TestLogFile");
		//seqLog2 = new SequentialLogger("/AIsim/trunk/test/edu/udel/cisc475/aisim/output","TestLogFile");
	}

	@Test
	public void testformattFileDestinationOne() {
		assertTrue(seqLog1.LogNodeEvent("Node 1", 0,"Node Started"));
		assertTrue(seqLog1.LogNodeEvent("Task 1", 1,"Task Started"));
		assertTrue(seqLog1.LogNodeEvent("Task 1", 1,"Task Finished"));
		assertTrue(seqLog1.LogNodeEvent("Node 1", 0,"Node Finished"));
		
		assertTrue(seqLog1.LogAgentEvent("Agent 2", 1,"testing the logger"));
	}
	
	//@Test
	//public void testformattFileDestinationTwo() {
	//	assertTrue(seqLog2.LogToFile("Node 1", 2,"testing the logger"));
	//}

}
