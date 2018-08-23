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
		seqLog2 = new SequentialLogger("/AIsim/trunk/test/edu/udel/cisc475/aisim/output/","TestLogFile");
	}

	@Test
	public void testformattFileDestinationOne() {
		//assertTrue(seqLog1.LogToFile("testing the logger"));
	}
	@Test
	public void testformattFileDestinationTwo() {
		//assertTrue(seqLog2.LogToFile("testing the logger"));
	}
	
}

