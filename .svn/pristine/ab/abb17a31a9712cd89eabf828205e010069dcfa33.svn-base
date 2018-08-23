package edu.udel.cisc475.aisim.output;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.output.SequentialLogger;

public class SequentialLoggerTest {

	SequentialLogger seqLog1;
	SequentialLogger seqLog2;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("SequentialLoggerTest");
	}
	
	
	
	@Before
	public void setUp() {
		seqLog1 = new SequentialLogger("","TestLogFile");
		seqLog2 = new SequentialLogger("/eecis/nfs.acad/home/bgott","TestLogFile");
	}

	@Test
	public void testformattFileDestinationOne() {
		assertTrue(seqLog1.LogNodeEvent("Node 2", 3,"testing the logger"));
		assertTrue(seqLog1.LogAgentEvent("Agent 2", 1,"testing the logger"));
	}
	
	//@Test
	//public void testformattFileDestinationTwo() {
	//	assertTrue(seqLog2.LogNodeEvent("Node 2", 3,"testing the logger"));
	//	assertTrue(seqLog2.LogAgentEvent("Agent 2", 1,"testing the logger"));
	//}
	
	
}

