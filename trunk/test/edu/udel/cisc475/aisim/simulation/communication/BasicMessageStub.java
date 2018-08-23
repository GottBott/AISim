package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class BasicMessageStub {
	
	
	public static final String TESTFOLDER = "testdata/aisim/simulation/communication/";
	
	public String readJson(String filename) throws IOException {
		String path = TESTFOLDER + filename;
		return new String(Files.readAllBytes(Paths.get(path)));	
	}
}
