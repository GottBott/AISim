package edu.udel.cisc475.aisim.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for reading in the config file which specifies a
 * random seed among other configuration constants. It then creates a
 * ConfigurationData object with the specified information.
 * @author Stefan
 *
 */
public class ConfigurationParser {
	private String fileLocation;
	private ConfigurationData configurationData;
	
	private boolean seedSet = false;
	private boolean outputDestinationSet = false;
	private boolean tickLengthSet = false;
	private boolean portSet = false;

	public ConfigurationParser(String fileLocation) {
		this.fileLocation = fileLocation;
		this.configurationData = null;
	}

	/**
	 * Parses the data found at the file location. It uses default information
	 * if anything is not specified in the file or the file is missing.
	 * 
	 * @throws InvalidFileException
	 */
	public void parse() throws InvalidFileException {
		File file = new File(fileLocation);

		long seed = ConfigurationData.DEFAULT_SEED;
		String outputDest = ConfigurationData.DEFAULT_OUTPUT_DESTINATION;
		Long tickLength = ConfigurationData.DEFAULT_TICK_LENGTH;
		int port = ConfigurationData.DEFAULT_PORT;

		try {

			ArrayList<FileError> errors = new ArrayList<FileError>();

			Scanner sc = new Scanner(file);

			int line = 0;

			while (sc.hasNextLine()) {
				line++;

				String[] tok = sc.nextLine().split("=");

				if (tok.length == 0) {
					FileError fe = new FileError(line,
							"Random \"=\"");
					errors.add(fe);
					continue;
				}
				
				if (tok.length >= 2 && tok[0].length() == 0) {
					FileError fe = new FileError(line,
							"Missing Key For Value: " + tok[1]);
					errors.add(fe);
					continue;
				}

				if (tok.length  == 1) {
					if (!tok[0].trim().equals("")) {
						FileError fe = new FileError(line,
								"Missing Value For Key: " + tok[0]);
						errors.add(fe);
					}
					continue;
				}

				tok[0] = tok[0].trim();
				tok[1] = tok[1].trim();

				switch (tok[0]) {
				case "seed":
					if(seedSet){
						FileError fe = new FileError(line,
								"Seed Was Already Set");
						errors.add(fe);
						continue;
					}
					try {
						seed = Long.parseLong(tok[1]);
						seedSet = true;
					} catch (NumberFormatException e) {
						FileError fe = new FileError(line,
								"Seed Must Be A Long");
						errors.add(fe);
					}
					break;
					
				case "outputDestination":
					if(outputDestinationSet){
						FileError fe = new FileError(line,
								"OutputDestination Was Already Set");
						errors.add(fe);
						continue;
					}
					outputDest = tok[1];
					outputDestinationSet = true;
					break;
					
				case "tickLength":
					if(tickLengthSet){
						FileError fe = new FileError(line,
								"TickLength Was Already Set");
						errors.add(fe);
						continue;
					}
					try {
						tickLength = Long.parseLong(tok[1]);
						tickLengthSet = true;
					} catch (NumberFormatException e) {
						FileError fe = new FileError(line,
								"TickLength Must Be A Long");
						errors.add(fe);
					}
					break;
					
				case "port":
					if(portSet){
						FileError fe = new FileError(line,
								"Port Was Already Set");
						errors.add(fe);
						continue;
					}
					try {
						port = Integer.parseInt(tok[1]);
						portSet = true;
					} catch (NumberFormatException e) {
						FileError fe = new FileError(line,
								"Port Must Be An Integer");
						errors.add(fe);
					}
					break;
					
				default:
					FileError fe = new FileError(line, "Invalid Key: " + tok[0]);
					errors.add(fe);
				}
			}

			sc.close();

			if (errors.size() > 0) {
				InvalidFileException ice = new InvalidConfigurationException(
						errors);
				throw ice;
			}

			configurationData = new ConfigurationData(seed, outputDest,
					tickLength, port);
		} catch (FileNotFoundException e) {
			configurationData = ConfigurationData.makeDefault();
			System.out
					.println("No configuration file (config.ini) found. Using default values.");
		}
	}

	public ConfigurationData getConfigurationData() {
		return configurationData;
	}
}
