package edu.udel.cisc475.aisim.input;

import java.util.Random;

/**
 * An object for holding config information for
 * the simulation. Created by ConfigurationParser.
 */
public class ConfigurationData {
	
	/**
	 * Data values.
	 */
	private long seed;
	private String outputDestination;
	private long tickLength;
	private int port;
	private int numOfTicks;
	static Random randL = new Random();
	
	/**
	 * Default data values.
	 */
	public static final long DEFAULT_SEED = randL.nextLong();
	public static final String DEFAULT_OUTPUT_DESTINATION = System.getProperty("user.dir") +"/logs";
	public static final long DEFAULT_TICK_LENGTH = 1000L;
	public static final int DEFAULT_PORT = 9876;
	public static final int DEFAULT_NUMBER_TICKS = 1000;
	
	public ConfigurationData(long seed, String outputDestination, long tickLength, int maxTicks, int port){
		this.seed = seed;
		this.outputDestination = outputDestination;
		this.tickLength = tickLength;
		this.port = port;
		this.numOfTicks = maxTicks;
	}
	
	/**
	 * Makes a ConfigurationData with the
	 * default values.
	 * @return Default Config data.
	 */
	public static ConfigurationData makeDefault(){
		return new ConfigurationData(DEFAULT_SEED, DEFAULT_OUTPUT_DESTINATION, 
									DEFAULT_TICK_LENGTH,DEFAULT_NUMBER_TICKS, DEFAULT_PORT);
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
	public int getMaxTicks() {
		return numOfTicks;
	}

	public int getPort() {
		return port;
	}
}
