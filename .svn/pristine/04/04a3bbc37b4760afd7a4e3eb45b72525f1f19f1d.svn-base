package edu.udel.cisc475.aisim.output;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;


//Ben Gotthold

public abstract class LogWriter {
	
	private String formattedFileDestination;
	private String fileDestination;
	private String fileName;
	private File dir; 
	private File logFile;

	

	public LogWriter(String fileDestination, String fileName){
		
		this.fileDestination = fileDestination;
		this.fileName = fileName;
		setUp();
	
	}
	
	// Sets up the location of the output file
	private Boolean setUp(){
		if(fileDestination ==""){
			URL location = LogWriter.class.getProtectionDomain().getCodeSource().getLocation();
	        fileDestination = location.getFile();
		}
		formattedFileDestination = formattFileDestination(fileDestination);
		dir = new File (formattedFileDestination);
		logFile = new File (dir, fileName);
		
		
		return true;
	}
	
	// helper function to format the output location
	// this makes the style of the input location flexible
	private String formattFileDestination(String fileDest){
		int i =0;
		String formattedFD ="";
		while(i < fileDest.length()){
			if(fileDest.charAt(i) == '/' && i != fileDest.length() -1){ 
				formattedFD += "/";
				i++;
				if(fileDest.charAt(i) != '/'){
					formattedFD += "/";
				}
			}
			formattedFD += fileDest.charAt(i);
			i++;
		}
		return formattedFD;
	}
	
	
	private String createTab(int numOfTabs){
		String tabs = "";
		for(int i = 0; i < numOfTabs; i++){
			tabs += "    ";
		}
		return tabs;
	}
	
	
	public Boolean LogNodeEvent(String nodeName, int priority, String logStatment){

		try {
		    PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
		    out.println(createTab(priority) + nodeName);
		    out.println(createTab(priority) + "  " + logStatment);
		    out.println("");	    
		    out.close();
		    return true;
		    
		} catch (IOException e) {
		    //do some exception handling
			return false;
		}
	}
	
	
	public Boolean LogAgentEvent(String AgentName, int priority, String logStatment){

		try {
		    PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
		    out.println(createTab(priority) + AgentName);
		    out.println(createTab(priority) + "  " + logStatment);
		    out.println("");	    
		    out.close();
		    return true;
		    
		} catch (IOException e) {
		    //do some exception handling
			return false;
		}
	}
	
}
