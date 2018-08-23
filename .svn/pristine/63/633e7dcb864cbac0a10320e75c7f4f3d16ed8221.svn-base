package edu.udel.cisc475.aisim.input;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.input.ConfigurationParser;

public class ConfigurationParserTest {
	
	ConfigurationParser cfgParser;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("ConfigurationParserTest");
	}
	
	
	@Before
	public void setUp() {
		cfgParser = new ConfigurationParser("config.ini");
		File f = new File("config.ini");
		f.delete();
	}
	
	@Test
	public void testParse() {
		cfgParser.parse();
		ConfigurationData confData = cfgParser.getConfigurationData();
		assertEquals(confData.getSeed(), ConfigurationData.DEFAULT_SEED);
		assertEquals(confData.getOutputDestination(), ConfigurationData.DEFAULT_OUTPUT_DESTINATION);
		assertEquals(confData.getTickLength(), ConfigurationData.DEFAULT_TICK_LENGTH);
		assertEquals(confData.getPort(), ConfigurationData.DEFAULT_PORT);

		PrintWriter writer;
		try {
			writer = new PrintWriter("config.ini", "UTF-8");
			writer.println("port="+1000);
			writer.println("outputDestination="+System.getProperty("user.dir") +"/Mylogs");
			writer.println("tickLength="+200);
			writer.println("seed="+1);
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		cfgParser.parse();

		assertEquals(cfgParser.getConfigurationData().getSeed(), 1);
		assertEquals(cfgParser.getConfigurationData().getOutputDestination(), System.getProperty("user.dir") +"/Mylogs");
		assertEquals(cfgParser.getConfigurationData().getTickLength(), 200);
		assertEquals(cfgParser.getConfigurationData().getPort(), 1000);
		
		File f = new File("config.ini");
		f.delete();
	}
}
