package edu.udel.cisc475.aisim.application;

import java.util.Scanner;

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
		String inputPath;
		if (args.length != 1) {
			inputPath = getInputPath();
		} else {
			inputPath = args[0];
		}
		
		ConfigurationParser configParser = new ConfigurationParser(CONFIG_LOCATION);
		configParser.parse();
		ConfigurationData configData = configParser.getConfigurationData();
		
		long seed = configData.getSeed();
		
		InputParser inputParser = new InputParser(inputPath, seed);
		inputParser.parse();
		InputData inputData = inputParser.getInputData();
		
		Simulator simulator = new Simulator(configData, inputData);
		simulator.startServer();
	}
	
	private static String getInputPath() {
		System.out.print("Enter the name of the input file: ");
		Scanner scanner = new Scanner(System.in);
		String inputPath = scanner.next();
		scanner.close();
		
		return inputPath;
	}
	
}
