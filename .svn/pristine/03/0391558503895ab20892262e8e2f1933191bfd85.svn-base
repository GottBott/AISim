package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

/**
 * This class is a Thread that is constantly reading messages from all agents
 * (asynchronously). It can also be used to send a message to an agent
 * (synchronously).
 * 
 * @author Mike
 *
 */
public class ServerCommunicateThread extends Thread {
	/**
	 * The size of the inBoundQueue.
	 */
	private final int QUEUE_SIZE = 100; // ArrayBlockingQueue has a FIXED
										// capacity

	/**
	 * A map between agent names and the agents themselves. Used for quick
	 * lookup by name.
	 */
	private HashMap<String, Agent> agents;

	/**
	 * Whether or not the simulation is currently running. When this is false,
	 * the thread stops.
	 */
	private boolean simulationRunning;

	/**
	 * An ArrayBlockingQueue (for concurrent access) of messages that have been
	 * received from the agents.
	 */
	private ArrayBlockingQueue<Message> inBoundQueue;

	/**
	 * The default constructor for a ServerCommunicateThread.
	 * 
	 * @param agents
	 *            A map between agent names and the agents themselves.
	 */
	public ServerCommunicateThread(HashMap<String, Agent> agents) {
		super();
		this.agents = agents;
		this.simulationRunning = true;
		this.inBoundQueue = new ArrayBlockingQueue<Message>(QUEUE_SIZE);
	}

	@Override
	/**
	 * Run method of the thread. As long as the simulation is running, it will attempt to receive messages from all agents and add them to the inBoundQueue.
	 */
	public void run() {
		try {
			while (simulationRunning) {
				for (Agent agent : agents.values()) {
					if (agent.hasNextMessage()) {
						String JSON = agent.read();
						Message message = Message.getMessageFromJSON(JSON);
						inBoundQueue.add(message);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error in ServerCommThread. Error: " + e);
			throw new ServerErrorException();
		}
	}

	/**
	 * Ends the simulation. Sets simulationRunning to false. This will terminate
	 * the thread.
	 */
	public void endSimulation() {
		simulationRunning = false;
	}

	/**
	 * Sends a message to its intended destination.
	 * 
	 * @param msg
	 *            The message to be sent.
	 */
	public void handleMessage(Message msg) {
		String JSON = msg.toJSON();
		Agent destination = agents.get(msg.getDestinationName());
		if (destination != null) {
			try {
				destination.write(JSON);
			} catch (IOException e) {
				System.err
						.println("ServerCommThread couldn't write message to agent "
								+ destination.getName() + ". Error: " + e);
				throw new ServerErrorException();
			}
		}
	}

	public ArrayBlockingQueue<Message> getInBoundQueue() {
		return inBoundQueue;
	}
}
