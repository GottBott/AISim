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
		assertEquals(cfgParser.getConfigurationData().getSeed(), 0);
		String s = cfgParser.getConfigurationData().getOutputDestination();
		assertEquals(s.substring(0,Math.min(s.length(), 9)), "logs/log_");
		assertEquals(cfgParser.getConfigurationData().getTickLength(), 100);
		assertEquals(cfgParser.getConfigurationData().getPort(), 9876);

		PrintWriter writer;
		try {
			writer = new PrintWriter("config.ini", "UTF-8");
			writer.println("port="+1000);
			writer.println("outputDestination="+"log.txt");
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
		assertEquals(cfgParser.getConfigurationData().getOutputDestination(), "log.txt");
		assertEquals(cfgParser.getConfigurationData().getTickLength(), 200);
		assertEquals(cfgParser.getConfigurationData().getPort(), 1000);
		
		File f = new File("config.ini");
		f.delete();
	}
}
