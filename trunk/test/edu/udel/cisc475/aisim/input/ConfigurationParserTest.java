package edu.udel.cisc475.aisim.input;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import edu.udel.cisc475.aisim.input.ConfigurationParser;
import edu.udel.cisc475.aisim.input.InvalidFileException;

public class ConfigurationParserTest {
	
	ConfigurationParser cfgParser1;
	ConfigurationParser cfgParser2;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("ConfigurationParserTest");
	}
	
	
	@Before
	public void setUp() {
		cfgParser1 = new ConfigurationParser("config.ini");
		cfgParser2 = new ConfigurationParser("config.ini");
		File f = new File("config.ini");
		f.delete();
	}
	
	@Test
	public void testParse() {
		try {
			cfgParser1.parse();
		} catch (InvalidFileException e) {
			for(FileError fe : e.getErrors()){
				System.out.println(fe);
			}
		}
		ConfigurationData confData = cfgParser1.getConfigurationData();
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
		
		try {
			cfgParser1.parse();
		} catch (InvalidFileException e) {
			for(FileError fe : e.getErrors()){
				System.out.println(fe);
			}
		}

		assertEquals(cfgParser1.getConfigurationData().getSeed(), 1);
		assertEquals(cfgParser1.getConfigurationData().getOutputDestination(), System.getProperty("user.dir") +"/Mylogs");
		assertEquals(cfgParser1.getConfigurationData().getTickLength(), 200);
		assertEquals(cfgParser1.getConfigurationData().getPort(), 1000);

		try {
			writer = new PrintWriter("config.ini", "UTF-8");
			writer.println("=");
			writer.println("port=");
			writer.println("port=c");
			writer.println("port=1");
			writer.println("port=2");
			writer.println("=randomValue");
			writer.println("outputDestination="+System.getProperty("user.dir") +"/Mylogs");
			writer.println("outputDestination="+System.getProperty("user.dir") +"/Mylogs2");
			writer.println("tickLength=a");
			writer.println("tickLength=1");
			writer.println("tickLength=2");
			writer.println("seed=b");
			writer.println("seed=1");
			writer.println("seed=2");
			writer.println("notAKey="+1);
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		try {
			cfgParser2.parse();
		} catch (InvalidFileException e) {
			for(FileError fe : e.getErrors()){
				System.out.println(fe);
			}
		}
		
		File f = new File("config.ini");
		f.delete();
	}
}
