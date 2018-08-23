package edu.udel.cisc475.aisim.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;

/**
 * This class implements LogWriter to create a detailed log in chronological
 * order.
 * 
 * @author Ben
 * 
 */

public class MethodCSVLogger extends LogWriter {

	/**
	 * Uses default constructor from LogWriter
	 * 
	 * @param fileDestination Path to file
	 * @param fileName Name of the log file
	 */
	public MethodCSVLogger(String fileDestination, String fileName) {
		super(fileDestination, fileName);
		startLog();
	}

	/**
	 * Function to write the header to the sequential log
	 * 
	 * @return True if logged successfully
	 */
	private boolean startLog() {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("name,agent,quality,duration");
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}
	
	
//	/**
//	 * Log An Agent
//	 * @param a The Agent to be logged
//	 * @return
//	 */
//	public boolean logAgent(Agent a) {
//		logAgentEvent(a);	
//		return true;
//
//	}
//
//	/**
//	 * Log a Relationship
//	 * @param nr the relationship to be logged
//	 * @return
//	 */
//	public boolean logRelationship(NodeRelationship nr) {
//		logRelationshipEvent(nr);
//		return true;
//	}
//	
	/**
	 * Log a Method
	 * @param m the method to be logged
	 * @return
	 */
	public boolean logMethod(Method m) {
		logMethodCSV(m);
		return true;
	}
//	
//	/**
//	 * Log a Message
//	 * @param m the Message to be logged
//	 * @return
//	 */
//	public boolean logMessage(Message m) {
//		logMessageEvent(m);
//		return true;
//	}
//	
	
	

}
