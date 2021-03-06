package edu.udel.cisc475.aisim.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;

/**
 * This class implements LogWriter to create an easy to read ordered log file
 * output.
 * 
 * @author Ben
 * 
 */

public class OrderedLogger extends LogWriter {

	LinkedList<NodeRelationship> nodeRelations;
	LinkedList<Method> methods;
	LinkedList<Agent> agents;	

	/**
	 * Uses constructor from LogWriter
	 * 
	 * @param fileDestination
	 *            Path to file
	 * @param fileName
	 *            Name of the log file
	 */
	public OrderedLogger(String fileDestination, String fileName) {
		super(fileDestination, fileName);
		this.nodeRelations = new LinkedList<NodeRelationship>();
		this.methods = new LinkedList<Method>();
		this.agents = new LinkedList<Agent>();

		startLog();
	}

	/**
	 * Adds header statement to the start of the log
	 * 
	 * @return True if logging was successful
	 */
	private boolean startLog() {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("####### Ordered Logger #######");
			out.println("\n");
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Add an agent the list to be logged
	 * 
	 * @param a
	 *            the agent to be logged
	 * @return
	 */
	public boolean addAgent(Agent a) {
		agents.add(a);
		return true;

	}

	/**
	 * Add a relationship to the list to be logged
	 * 
	 * @param nr
	 *            the node relationship to be logged
	 * @return
	 */
	public boolean addRelationship(NodeRelationship nr) {
		nodeRelations.add(nr);
		return true;
	}

	/**
	 * Add a method to the list to be Logged
	 * 
	 * @param m
	 *            the Method to be logged
	 * @return
	 */
	public boolean addMethod(Method m) {
		methods.add(m);
		return true;
	}

	/**
	 * Log all agents in the list
	 * 
	 * @return True if logging was successful
	 */
	private boolean logAgent() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n##### Agent Results #####");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		Agent a;
		while (!agents.isEmpty()) {
			a = agents.pop();
			logAgentEvent(a);
		}
		return true;

	}

	/**
	 * Log all nodes in the list
	 * 
	 * @return True if logging was successful
	 */
	private boolean logNode() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n##### Method Results #####");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		Method n;
		while (!methods.isEmpty()) {
			n = methods.pop();
			logMethodEvent(n);
		}
		return true;

	}

	/**
	 * log all relationships in the list
	 * 
	 * @return True if logging was successful
	 */
	private boolean logRelationship() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n##### Relationship Results #####");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		NodeRelationship nr;
		while (!nodeRelations.isEmpty()) {
			nr = nodeRelations.pop();
			logRelationshipEvent(nr);
		}
		return true;
	}

	/**
	 * Log all the lists in order as well as the final quality and duration
	 * 
	 * @param duration
	 *            final duration of the simulation
	 * @param quality
	 *            final quality of the simulation
	 * @return
	 */
	public boolean logAll(double duration, double quality) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("Final Duration: " + duration);
			out.println("Final Quality: " + quality);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		logNode();
		logRelationship();
		logAgent();
		return true;
	}

	/**
	 * Getter for nodeRelations
	 * 
	 * @return list of node Relations
	 */
	public LinkedList<NodeRelationship> getNodeRelations() {
		return nodeRelations;
	}

}
