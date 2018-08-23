package edu.udel.cisc475.aisim.simulation.simulationstate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

/**
 * This class represents an Agent in the simulation. It is mostly used as a
 * wrapper class for an external Agent's sockets.
 * 
 * @author Mike
 *
 */
public class Agent {

	/**
	 * The name of this agent
	 */
	private String name;

	/**
	 * The Socket that this agent is communicating on.
	 */
	private Socket socket;

	/**
	 * The PrintWriter to write to the socket's output.
	 */
	private PrintWriter outputWriter;

	/**
	 * The BufferedReader to read from the socket's input.
	 */
	private BufferedReader inputReader;

	/**
	 * The TaskTree that this agent can see.
	 */
	private TaskTree visibleTree;
	
	/**
	 * Is the agent currently completing a task
	 */
	private Boolean isBusy = false;

	/**
	 * The number of messages sent by this agent.
	 */
	private int messagesSent = 0;

	/**
	 * The number of messages that this agent has received.
	 */
	private int messagesReceived = 0;

	/**
	 * The Methods that this agent has completed.
	 */
	private LinkedList<Method> methodsCompleted = new LinkedList<Method>();

	/**
	 * The default constructor for an Agent.
	 * 
	 * @param name
	 *            The name of this Agent.
	 * @param socket
	 *            The Socket that this Agent is communicating on.
	 * @throws IOException
	 */
	public Agent(String name, Socket socket) throws IOException {
		this.name = name;
		this.socket = socket;
		this.outputWriter = new PrintWriter(socket.getOutputStream(), true);
		this.inputReader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		this.visibleTree = new TaskTree();
	}

	/**
	 * Writes a string (appended by a new line) to the agent's socket's output.
	 * 
	 * @param output
	 *            The string to write.
	 * @throws IOException
	 */
	public void write(String output) throws IOException {
		outputWriter.println(output);
		messagesReceived++;
	}

	/**
	 * Reads a string (up to a new line character) from the agent's socket's
	 * input.
	 * 
	 * @return The string that was read in.
	 * @throws IOException
	 */
	public String read() throws IOException {

		messagesSent++;
		return inputReader.readLine();

	}

	/**
	 * Determines whether or not this agent has a message to be read.
	 * 
	 * @return Boolean indicating whether or not this agent has a message to be
	 *         read.
	 * @throws IOException
	 */
	public boolean hasNextMessage() throws IOException {
		return inputReader.ready();
	}

	/**
	 * Closes this agent's socket.
	 */
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a method to this agent's list of completed methods.
	 * 
	 * @param m
	 *            The Method to add.
	 */
	public void addCompleatedMethod(Method m) {
		this.methodsCompleted.add(m);
	}

	public String getName() {
		return name;
	}

	public Socket getSocket() {
		return socket;
	}

	public TaskTree getVisibleTree() {
		return visibleTree;
	}

	public void setVisibleTree(TaskTree visibleTree) {
		this.visibleTree = visibleTree;
	}

	public int getMessagesSent() {
		return messagesSent;
	}

	public int getMessagesRecieved() {
		return messagesReceived;
	}

	public LinkedList<Method> getMethodsCompleted() {
		return methodsCompleted;
	}
	
	public Boolean getIsBusy() {
		return isBusy;
	}

	public void setIsBusy(Boolean isBusy) {
		this.isBusy = isBusy;
	}

}
