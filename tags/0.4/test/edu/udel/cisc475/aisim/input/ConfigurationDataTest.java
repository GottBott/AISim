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
		confData = new ConfigurationData(1, "log.txt", 200, 1000);

		assertEquals(confData.getSeed(), 1);
		assertEquals(confData.getOutputDestination(), "log.txt");
		assertEquals(confData.getTickLength(), 200);
		assertEquals(confData.getPort(), 1000);
	}
	
	@Test
	public void testMakeDefault() {
		confData = ConfigurationData.makeDefault();

		assertEquals(confData.getSeed(), 0);
		String s = confData.getOutputDestination();
		assertEquals(s.substring(0,Math.min(s.length(), 9)), "logs/log_");
		assertEquals(confData.getTickLength(), 100);
		assertEquals(confData.getPort(), 9876);
	}
}
