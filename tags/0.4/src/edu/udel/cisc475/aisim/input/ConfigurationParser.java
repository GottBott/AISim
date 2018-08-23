package edu.udel.cisc475.aisim.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


/**
 * This class is responsible for reading in the config file which specifies a
 * random seed among other configuration constants.
 * 
 *
 */
public class ConfigurationParser {
	private String fileLocation;
	private ConfigurationData configurationData;

	public ConfigurationParser(String fileLocation) {
		this.fileLocation = fileLocation;
		this.configurationData = null;
	}

	/**
	 * Parses the data found at the file location
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
			PrintWriter writer;
			try {
				writer = new PrintWriter(fileLocation, "UTF-8");
				writer.println("seed="+seed);
				writer.println("outputDestination="+outputDest);
				writer.println("tickLength="+tickLength);
				writer.println("port="+port);
				writer.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			configurationData = ConfigurationData.makeDefault();
			System.out.println("Default file created");
		}
	}
	
//	public static void main(String[] args){
//		ConfigurationParser cp = new ConfigurationParser("config.ini");
//		cp.parse();
//		System.out.println("seed="+cp.getConfigurationData().getSeed());
//		System.out.println("outDest="+cp.getConfigurationData().getOutputDestination());
//		System.out.println("tick="+cp.getConfigurationData().getTickLength());
//		System.out.println("port="+cp.getConfigurationData().getPort());
//	}

	public ConfigurationData getConfigurationData() {
		return configurationData;
	}

}
