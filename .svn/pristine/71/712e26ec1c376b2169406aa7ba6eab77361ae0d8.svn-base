package edu.udel.cisc475.aisim.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;

/**
 * This class represents an abstraction of a log writer that all system loggers
 * must implement
 * 
 * @author Ben
 * 
 */


public abstract class LogWriter {

	/**
	 * File Destination String after is has been cleaned
	 */
	private String formattedFileDestination;
	/**
	 * Path to log file
	 */
	private String fileDestination;
	/**
	 * name of log file
	 */
	private String fileName;
	/**
	 * Java File to hold fileDestination
	 */
	private File dir;
	/**
	 * Final java file location to log
	 */
	protected File logFile;

	/**
	 * constructor for a logger
	 * 
	 * @param fileDestination
	 * @param fileName
	 */
	public LogWriter(String fileDestination, String fileName) {

		this.fileDestination = fileDestination;
		this.fileName = fileName;
		setUp();

	}

	/**
	 * sets up the logger and creates a file at given destination
	 * 
	 * @return True if setup was successful
	 */
	private boolean setUp() {
		if (fileDestination == "") {
			URL location = LogWriter.class.getProtectionDomain()
					.getCodeSource().getLocation();
			fileDestination = location.getFile();
		}
		formattedFileDestination = formattFileDestination(fileDestination);
		dir = new File(formattedFileDestination);
		dir.mkdir();
		logFile = new File(dir, fileName);

		return true;
	}

	/**
	 * helper function to format the output location this makes the style of the
	 * input location flexible
	 * 
	 * @param fileDest
	 * @return True if formatting was successful
	 */
	private String formattFileDestination(String fileDest) {
		int i = 0;
		String formattedFD = "";
		while (i < fileDest.length()) {
			if (fileDest.charAt(i) == '/' && i != fileDest.length() - 1) {
				formattedFD += "/";
				i++;
				if (fileDest.charAt(i) != '/') {
					formattedFD += "/";
				}
			}
			formattedFD += fileDest.charAt(i);
			i++;
		}
		return formattedFD;
	}

	/**
	 * returns a string of tabs for consistent clean looking output
	 * @param numOfTabs
	 * @return A String with the specified number of tabs
	 */
	protected String createTab(int numOfTabs) {
		String tabs = "";
		for (int i = 0; i < numOfTabs; i++) {
			tabs += "    ";
		}
		return tabs;
	}

	/**
	 *  Logs a Method
	 * @param m Method to Log
	 * @return True if logging was successful
	 */
	protected boolean logMethodEvent(Method m) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n");
			out.println(createTab(1) + "## Method ##");
			out.println(createTab(2) + "Name: " + m.getName());
			out.println(createTab(2) + "Completed By : " + m.getAgent());
			out.println(createTab(2) + "With Quality : "
					+ m.getQuality());
			out.println(createTab(2) + "With Duration: "
					+ m.getDuration());
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Logs the activation of a relationship
	 * @param nodeRelation Relationship to Log
	 * @return True if logging was successful
	 */
	protected boolean logRelationshipEvent(NodeRelationship nodeRelation) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n");
			out.println(createTab(1) + "## Relationship ##");
			out.println(createTab(2) + "Name: " + nodeRelation.getName());
			out.println(createTab(2) + "Type: " + nodeRelation.getType());
			out.println(createTab(2) + "Source : " + nodeRelation.getFromNode().getName());
			out.println(createTab(2) + "Target : " + nodeRelation.getToNode().getName());
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Logs the sending or receiving of a Message
	 * @param m Message to be logged
	 * @return True if logging was successful
	 */
	protected boolean logMessageEvent(Message m) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n");
			out.println(createTab(1) + "## Message ##");
			out.println(createTab(2) + m.getlogMessageHeader());
			out.println(createTab(2) + m.getlogMessageDetail());
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Logs the activity of an agent
	 * @param a Agent to be logged
	 * @return True if logging was successful
	 */
	protected boolean logAgentEvent(Agent a) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n");
			out.println(createTab(1) + "## Agent ##");
			out.println(createTab(2) + "Name " + a.getName());
			out.println(createTab(2) + "Sent: " + a.getMessagesSent());
			out.println(createTab(2) + "Received: "+ a.getMessagesRecieved());
			out.print(createTab(2) + "Completed: ");
				for (int i = 0; i < a.getMethodsCompleted().size(); i++) {
					if (i != 0) {
						out.print(", ");
					}
					out.print(a.getMethodsCompleted().get(i).getName());

				}
			out.close();
		} catch (IOException e) {
			// do some exception handling
			return false;
		}
		return true;

	}
	
	protected boolean logMethodCSV(Method m) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.print(m.getName() + ",");
			out.print(m.getAgent() + ",");
			out.print(+ m.getQuality() + ",");
			out.print(m.getDuration());
			out.print("\n");
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Getter for fileDestination
	 * @return fileDestination
	 */
	public String getPathToFile() {
		return logFile.getAbsolutePath();
	}

}
