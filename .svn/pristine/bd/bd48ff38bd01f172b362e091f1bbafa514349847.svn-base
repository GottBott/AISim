package edu.udel.cisc475.aisim.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class is responsible for reading in the config file which specifies a
 * random seed among other configuration constants. It then creates a 
 * ConfigurationData object with the specified information.
 */
public class ConfigurationParser {
	private String fileLocation;
	private ConfigurationData configurationData;

	public ConfigurationParser(String fileLocation) {
		this.fileLocation = fileLocation;
		this.configurationData = null;
	}

	/**
	 * Parses the data found at the file location.
	 * It uses default information if anything is not
	 * specified in the file or the file is missing.
	 */
	public void parse(){
		File file = new File(fileLocation);

		long seed = ConfigurationData.DEFAULT_SEED;
		String outputDest = ConfigurationData.DEFAULT_OUTPUT_DESTINATION;
		Long tickLength = ConfigurationData.DEFAULT_TICK_LENGTH;
		int port = ConfigurationData.DEFAULT_PORT;

		try {

			Scanner sc = new Scanner(file);

			while(sc.hasNext()){
				String[] tok = sc.next().split("=");
				switch(tok[0]){
				case "seed":
					seed = Long.parseLong(tok[1]);
					break;
				case "outputDestination":
					outputDest = tok[1];
					break;
				case "tickLength":
					tickLength = Long.parseLong(tok[1]);
					break;
				case "port":
					port = Integer.parseInt(tok[1]);
					break;
				}
			}

			sc.close();

			configurationData = new ConfigurationData(seed, outputDest, tickLength, port);
		} 
		catch (FileNotFoundException e) {
			configurationData = ConfigurationData.makeDefault();
			System.out.println("No configuration file (config.ini) found. Using default values.");
		}
	}

	public ConfigurationData getConfigurationData() {
		return configurationData;
	}

}
