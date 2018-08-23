package edu.udel.cisc475.aisim.output;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;

public class Logger {
	

	/**
	 * Types of loggers being used
	 */
	protected static SequentialLogger SequentialLog;
	protected static OrderedLogger OrderedLog;
		
	public static void initilizeLogger(String fileDestination){
			
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
		Date resultdate = new Date(time);
		
		SequentialLog = new SequentialLogger(fileDestination,"Log_Sequential_"+ sdf.format(resultdate) + ".txt");
		OrderedLog = new OrderedLogger(fileDestination,"Log_Ordered_"+ sdf.format(resultdate) + ".txt");
		
	}
	

	/**
	 * Log An Agent
	 * @param a The Agent to be logged
	 * @return
	 */
	public static boolean logAgent(Agent a) {
		SequentialLog.logAgent(a);
		OrderedLog.logAgent(a);
		return true;

	}

	/**
	 * Log a Relationship
	 * @param nr the relationship to be logged
	 * @return
	 */
	public static boolean logRelationship(NodeRelationship nr) {	
		if (!OrderedLog.getNodeRelations().contains(nr)) {
			SequentialLog.logRelationship(nr);
			OrderedLog.logRelationship(nr);
		}
		return true;
	}
	
	/**
	 * Log a Method
	 * @param m the method to be logged
	 * @return
	 */
	public static boolean logMethod(Method m) {
		SequentialLog.logMethod(m);
		OrderedLog.logMethod(m);
		return true;
	}
	
	/**
	 * Log a Message
	 * @param m the Message to be logged
	 * @return
	 */
	public static boolean logMessage(Message m) {
		SequentialLog.logMessage(m);
		return true;
	}
	
	public static boolean logAll(double duration, double quality, long seed) {
		OrderedLog.logAll(duration, quality, seed);
		return true;
	}
	
	public static String getPathToFiles(){
		return OrderedLog.getPathToFile() + "\n" +SequentialLog.getPathToFile();
	}
	


}
