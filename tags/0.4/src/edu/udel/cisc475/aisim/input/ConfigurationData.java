package edu.udel.cisc475.aisim.input;

public class ConfigurationData {
	private long seed;
	private String outputDestination;
	private long tickLength;
	private int port;
	
	public static final long DEFAULT_SEED = 0L;
	public static final String DEFAULT_OUTPUT_DESTINATION = "logs/log_" + System.currentTimeMillis() + ".txt";
	public static final long DEFAULT_TICK_LENGTH = 100L;
	public static final int DEFAULT_PORT = 9876;
	
	public ConfigurationData(long seed, String outputDestination, long tickLength, int port){
		this.seed = seed;
		this.outputDestination = outputDestination;
		this.tickLength = tickLength;
		this.port = port;
	}
	
	public static ConfigurationData makeDefault(){
		return new ConfigurationData(DEFAULT_SEED, DEFAULT_OUTPUT_DESTINATION, 
									DEFAULT_TICK_LENGTH, DEFAULT_PORT);
	}
	
	public long getSeed() {
		return seed;
	}

	public String getOutputDestination() {
		return outputDestination;
	}

	public long getTickLength() {
		return tickLength;
	}

	public int getPort() {
		return port;
	}
}
