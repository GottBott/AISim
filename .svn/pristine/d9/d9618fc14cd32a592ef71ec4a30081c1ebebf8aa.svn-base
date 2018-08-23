package edu.udel.cisc475.aisim.application;

import edu.udel.cisc475.aisim.input.ConfigurationData;
import edu.udel.cisc475.aisim.input.ConfigurationParser;
import edu.udel.cisc475.aisim.input.InputData;
import edu.udel.cisc475.aisim.input.InputParser;
import edu.udel.cisc475.aisim.simulation.simulationstate.Simulator;

/**
 * The main application class.
 *
 */
public class AIsim {
	
	private static final String CONFIG_LOCATION = "config.ini";
	
	public static void main(String[] args) {
		if (args.length != 1) {
			printUsage();
			System.exit(0);
		}
		
		String inputPath = args[0];
		
		ConfigurationParser configParser = new ConfigurationParser(CONFIG_LOCATION);
		configParser.parse();
		ConfigurationData configData = configParser.getConfigurationData();
		
		long seed = configData.getSeed();
		
		InputParser inputParser = new InputParser(inputPath, (double) seed);
		inputParser.parse();
		InputData inputData = inputParser.getInputData();
		
		Simulator simulator = new Simulator(configData, inputData);
		simulator.startServer();
	}
	
	private static void printUsage() {
		System.out.println("Usage:\n\tjava -jar AIsim.jar input\n\n\tinput - The location of the .ctaems input file");
	}
	
}
