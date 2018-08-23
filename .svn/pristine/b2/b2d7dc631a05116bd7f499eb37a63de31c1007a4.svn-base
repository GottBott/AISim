package edu.udel.cisc475.aisim.simulation.communication;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

public class ServerCommunicateThread extends Thread {
	private final int QUEUE_SIZE = 100; //ArrayBlockingQueue has a FIXED capacity
	
	private HashMap<String, Agent> agents;
	private boolean simulationRunning;
	private ArrayBlockingQueue<Message> inBoundQueue;
	
	public ServerCommunicateThread(HashMap<String, Agent> agents) {
		super();
		this.agents = agents;
		this.simulationRunning = true;
		this.inBoundQueue = new ArrayBlockingQueue<Message>(QUEUE_SIZE);
	}

	@Override
	public void run(){
		try {	
			while (simulationRunning) {
				for (Agent agent : agents.values()) {
					if (agent.hasNextMessage()) {
						String JSON = agent.read();
						Message message = Message.getMessageFromJSON(JSON);
						inBoundQueue.add(message);
						//System.out.println("IBQ in SCT" + message.toJSON());/////
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error in ServerCommThread. Error: " + e);
			throw new ServerErrorException();
		}
	}
	
	public void endSimulation(){
		simulationRunning = false;
	}
	
	public void handleMessage(Message msg) {
		
		String JSON = msg.toJSON();
		Agent destination = agents.get(msg.getDestinationName());
		if (destination != null) {
			try {
				destination.write(JSON);
			} catch (IOException e) {
				System.err.println("ServerCommThread couldn't write message to agent " + destination.getName() + ". Error: " + e);
				throw new ServerErrorException();
			}
		}
	}
	
	public ArrayBlockingQueue<Message> getInBoundQueue() {
		return inBoundQueue;
	}
}
