package edu.udel.cisc475.aisim.simulation.simulationstate;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.output.Logger;
import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.communication.ServerCommunicateThread;

public class Simulator {
	private int tick;
	private long seed;
	private Random rand;
	private Logger logger;
	private ArrayList<Agent> agents;
	private static ArrayBlockingQueue<Message> messageQueue;
	private ServerCommunicateThread commThread;
	private ArrayList<Method> runningMethods;
	
	public Simulator(int tick, long seed, Random rand, Logger logger,
			ArrayList<Agent> agents, ServerCommunicateThread commThread,
			ArrayList<Method> runningMethods) {
		super();
		this.tick = tick;
		this.seed = seed;
		this.rand = rand;
		this.logger = logger;
		this.agents = agents;
		this.commThread = commThread;
		this.runningMethods = runningMethods;
	}

	void startServer(){
		
	}
	
	void startSimulation(){
		
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public ArrayList<Agent> getAgents() {
		return agents;
	}

	public void setAgents(ArrayList<Agent> agents) {
		this.agents = agents;
	}

	public static ArrayBlockingQueue<Message> getMessageQueue() {
		return messageQueue;
	}

	public static void setMessageQueue(ArrayBlockingQueue<Message> messageQueue) {
		Simulator.messageQueue = messageQueue;
	}

	public ServerCommunicateThread getCommThread() {
		return commThread;
	}

	public void setCommThread(ServerCommunicateThread commThread) {
		this.commThread = commThread;
	}

	public ArrayList<Method> getRunningMethods() {
		return runningMethods;
	}

	public void setRunningMethods(ArrayList<Method> runningMethods) {
		this.runningMethods = runningMethods;
	}
}
