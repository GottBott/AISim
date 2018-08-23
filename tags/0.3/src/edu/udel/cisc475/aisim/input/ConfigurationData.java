package edu.udel.cisc475.aisim.input;

public class ConfigurationData {
	private long seed;
	private String inputLocation;
	private String outputDestination;
	private long tickLength;
	private int port;
	
	public static final long DEFAULT_SEED = 0L;
	public static final String DEFAULT_INPUT_LOCATION = "problem.ctaems";
	public static final String DEFAULT_OUTPUT_DESTINATION = "logs/log_" + System.currentTimeMillis() + ".txt";
	public static final long DEFAULT_TICK_LENGTH = 100L;
	public static final int DEFAULT_PORT = 9876;
	
	public ConfigurationData(long seed, String inputLocation, String outputDestination, long tickLength, int port){
		this.seed = seed;
		this.inputLocation = inputLocation;
		this.outputDestination = outputDestination;
		this.tickLength = tickLength;
		this.port = port;
	}
	
	public static ConfigurationData makeDefault(){
		return new ConfigurationData(DEFAULT_SEED, DEFAULT_INPUT_LOCATION, DEFAULT_OUTPUT_DESTINATION, 
									DEFAULT_TICK_LENGTH, DEFAULT_PORT);
	}
	
	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public String getInputLocation() {
		return inputLocation;
	}

	public void setInputLocation(String inputLocation) {
		this.inputLocation = inputLocation;
	}

	public String getOutputDestination() {
		return outputDestination;
	}

	public void setOutputDestination(String outputDestination) {
		this.outputDestination = outputDestination;
	}
	
	public long getTickLength() {
		return tickLength;
	}

	public void setTickLength(long tickLength) {
		this.tickLength = tickLength;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
