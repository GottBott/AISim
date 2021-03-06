package edu.udel.cisc475.aisim.application;

import java.util.Scanner;

import edu.udel.cisc475.aisim.input.ConfigurationData;
import edu.udel.cisc475.aisim.input.ConfigurationParser;
import edu.udel.cisc475.aisim.input.FileError;
import edu.udel.cisc475.aisim.input.InputData;
import edu.udel.cisc475.aisim.input.InputParser;
import edu.udel.cisc475.aisim.input.InvalidFileException;
import edu.udel.cisc475.aisim.simulation.simulationstate.Simulator;

/**
 * The main application class. Parses the configuration and input files and then starts the simulation.
 * @author Ben Gotthold
 *
 */

public class AIsim {
	
	private static String CONFIG_LOCATION = "config.ini";
	
	public static void main(String[] args) {
		String inputPath ="";
		if(args.length == 2){
			inputPath = args[0];
			CONFIG_LOCATION = args[1] + "/" + CONFIG_LOCATION;
		}
		else if (args.length == 1) {
			inputPath = args[0];	
		}
		else if(args.length == 0){
			inputPath = getInputPath();
		}
		else {
			System.out.println("Invalid Number of Arguments");
			System.exit(1);
		}
		
		
		ConfigurationParser configParser = new ConfigurationParser(CONFIG_LOCATION);
		try {
			configParser.parse();
		} catch (InvalidFileException e1) {
			System.err.println("Error" + (e1.getErrors().size() > 1 ? "s" : "") + " in config file");
			for(FileError fe : e1.getErrors()){
				System.err.println(fe);
			}
			System.exit(1);
		}
		ConfigurationData configData = configParser.getConfigurationData();
		
		long seed = configData.getSeed();
		System.out.println("Seed Initilized to: " + seed);
		
		InputParser inputParser = new InputParser(inputPath, seed);
		try {
			inputParser.parse();
		} catch (InvalidFileException e) {
			System.err.println("Error" + (e.getErrors().size() > 1 ? "s" : "") + " in input file");
			for(FileError fe : e.getErrors()){
				System.err.println(fe);
			}
			System.exit(1);
		}
		InputData inputData = inputParser.getInputData();
		
		Simulator simulator = new Simulator(configData, inputData);
		simulator.startServer();
	}
	
	private static String getInputPath() {
		System.out.print("Enter the name of the CTAEMS input file: ");
		Scanner scanner = new Scanner(System.in);
		String inputPath = scanner.next();
		scanner.close();
		
		return inputPath;
	}
	
}
