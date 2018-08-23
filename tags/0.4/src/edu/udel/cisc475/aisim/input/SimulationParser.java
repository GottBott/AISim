package edu.udel.cisc475.aisim.input;

public abstract class SimulationParser {
	protected String fileLocation;
	protected String fileData;
	
	public SimulationParser(String fileLocation){
		this.fileLocation = fileLocation;
		fileData = "";
	}
	
	public abstract boolean readFile();
	
	public abstract void parse();

	public String getFileLocation() {
		return fileLocation;
	}

	public String getFileData() {
		return fileData;
	}
}
