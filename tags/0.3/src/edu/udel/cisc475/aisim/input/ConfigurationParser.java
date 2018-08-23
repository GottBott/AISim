package edu.udel.cisc475.aisim.input;


/**
 * This class is responsible for reading in the config file which specifies a
 * random seed among other configuration constants.
 * 
 *
 */
public class ConfigurationParser extends SimulationParser {
	private ConfigurationData configurationData;

	public ConfigurationParser(String fileLocation) {
		super(fileLocation);
		setConfigurationData(null);
	}

	/**
	 * Reads in the configuration file. NOTE: currently does nothing.
	 * 
	 * @param inputFileLocation
	 *            The location of the configuration file within the project.
	 * 
	 * @return True if the file was read without throwing any exceptions, false
	 *         otherwise.
	 */
	@Override
	public boolean readFile() {
		return false;
	}
	
	/**
	 * Parses the data currently in fileData
	 */
	@Override
	public void parse(){
		System.out.println(this.getFileData());
	}

	public ConfigurationData getConfigurationData() {
		return configurationData;
	}

	public void setConfigurationData(ConfigurationData configurationData) {
		this.configurationData = configurationData;
	}

}
