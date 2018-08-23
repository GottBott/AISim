package edu.udel.cisc475.aisim.input;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConfigurationDataTest {
	
	ConfigurationData confData;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("ConfigurationDataTest");
	}
	
	@Test
	public void basicConstructorTest() {
		confData = new ConfigurationData(1, "/myLogs", 200, 1000);

		assertEquals(confData.getSeed(), 1);
		assertEquals(confData.getOutputDestination(), "/myLogs");
		assertEquals(confData.getTickLength(), 200);
		assertEquals(confData.getPort(), 1000);
	}
	
	@Test
	public void testMakeDefault() {
		confData = ConfigurationData.makeDefault();

		assertEquals(confData.getSeed(), ConfigurationData.DEFAULT_SEED);
		assertEquals(confData.getOutputDestination(), ConfigurationData.DEFAULT_OUTPUT_DESTINATION);
		assertEquals(confData.getTickLength(), ConfigurationData.DEFAULT_TICK_LENGTH);
		assertEquals(confData.getPort(), ConfigurationData.DEFAULT_PORT);
	}
}
