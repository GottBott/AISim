package edu.udel.cisc475.aisim.simulation.simulationstate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.TaskTree;
import edu.udel.cisc475.aisim.input.ConfigurationData;
import edu.udel.cisc475.aisim.input.InputData;
import edu.udel.cisc475.aisim.simulation.communication.AskMethodStatusMessage;
import edu.udel.cisc475.aisim.simulation.communication.ConfirmMethodStartMessage;
import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.communication.NewNodeMessage;
import edu.udel.cisc475.aisim.simulation.communication.NotifyMethodCompletedMessage;
import edu.udel.cisc475.aisim.simulation.communication.NotifyMethodStatusMessage;
import edu.udel.cisc475.aisim.simulation.communication.ServerCommunicateThread;
import edu.udel.cisc475.aisim.simulation.communication.ServerListenThread;
import edu.udel.cisc475.aisim.simulation.communication.SetRandomSeedMessage;
import edu.udel.cisc475.aisim.simulation.communication.StartMethodMessage;

public class Simulator {
	private final int QUEUE_SIZE = 100; //ArrayBlockingQueue has a FIXED capacity
	
	private int tick;
	private long tickLength;
	private long seed;
	private int port;
	private Random rand;
	private HashMap<String, Agent> agents;
	private HashSet<String> agentNames;
	private ArrayBlockingQueue<Message> outBoundQueue; //Don't think we need this one anymore
	private ServerCommunicateThread commThread;
	private ArrayList<Method> runningMethods;
	private TaskTree tree;
	
	public Simulator(ConfigurationData configData, InputData inputData) {
		this.seed = configData.getSeed();
	
		this.tickLength = configData.getTickLength();
		this.port = configData.getPort();
		this.tree = inputData.getTree();
		this.agentNames = inputData.getAgents();
		this.tick = 0;
		this.rand = new Random(this.seed);
		this.outBoundQueue = new ArrayBlockingQueue<Message>(QUEUE_SIZE);
		this.runningMethods = new ArrayList<Method>();
	}

	public void startServer() {
		ServerListenThread listenThread = new ServerListenThread(port, agentNames);
		listenThread.start();
		while (listenThread.isAlive());
		agents = listenThread.getAgents();
		prepareSimulation();
	}
	
	private void prepareSimulation() {
		commThread = new ServerCommunicateThread(agents);
		commThread.start();
		
		sendInitialData();
		
		//TODO: Send agents start simulation message (maybe?)
		
		startSimulation();
	}
	
	private void sendInitialData() {
		//Send seed
		for (String name : agents.keySet()) {
			SetRandomSeedMessage message = new SetRandomSeedMessage(name, seed);
			commThread.handleMessage(message);
		}
		
		//Turns out we want to send only what agents can see. Check out 6.3 of the cTAEMS spec
		for (String name : agents.keySet()) {
			TaskTree visibleTree = new TaskTree();
			
			//TODO: Figure out how to tell agents only what they can see
			NewNodeMessage message = new NewNodeMessage(Message.SIMULATOR_NAME, name, visibleTree.getHead());
			commThread.handleMessage(message);
		}
	}
	
	private void startSimulation() {
		long lastTick = System.currentTimeMillis();
		long currentTime;
		while (!tree.isFinished()) {
			currentTime = System.currentTimeMillis();
			if (currentTime - lastTick < tickLength) {
				tick++;
				replyToAllMessages();
				handleFinishedMethods();
				lastTick = currentTime;
			}
		}
		
		//TODO: Do something once simulation is done (disconnect agents?)
	}
	
	private void replyToAllMessages() {
		ArrayList<Message> inBoundQueue = new ArrayList<Message>();
		commThread.getInBoundQueue().drainTo(inBoundQueue);
		for (Message message : inBoundQueue) {
			replyToMessage(message);
		}
	}
	
	private void replyToMessage(Message message) {
		if (message instanceof AskMethodStatusMessage) {
			String agentName = message.getSenderName();
			String methodName = ((AskMethodStatusMessage)message).getMethodName();
			Method method = (Method)tree.getNodeWithName(methodName);
			NotifyMethodStatusMessage reply = new NotifyMethodStatusMessage(agentName, methodName, method.isStarted(), 
																			method.isFinished(), method.isEnabled());
			commThread.handleMessage(reply);
		} else if (message instanceof StartMethodMessage) {
			String agentName = message.getSenderName();
			String methodName = ((StartMethodMessage)message).getMethodName();
			Method method = (Method)tree.getNodeWithName(methodName);
			ConfirmMethodStartMessage reply;
			if (method.isEnabled() && !method.isFinished() && !method.isStarted()) {
				runningMethods.add(method);
				method.start();
				reply = new ConfirmMethodStartMessage(agentName, methodName, true);
			}
			else {
				reply = new ConfirmMethodStartMessage(agentName, methodName, false);
			}
			commThread.handleMessage(reply);
		}
	}
	
	private void handleFinishedMethods() {
		//TODO: Activate all relationships (has to propogate through Tasks)
		HashSet<Method> methodsToRemove = new HashSet<Method>();
		for (Method method : runningMethods) {
			if (method.getDuration() == (tick - method.getStartTime())) {
				//Method finished
				methodsToRemove.add(method);
				method.setFinished(true);
				String agentName = method.getAgent();
				String methodName = method.getName();
				double quality = method.getQuality();
				double duration = method.getDuration();
				NotifyMethodCompletedMessage message = new NotifyMethodCompletedMessage(agentName, methodName, quality, duration);
				commThread.handleMessage(message);
			}
		}
		runningMethods.removeAll(methodsToRemove);
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

	public HashMap<String, Agent> getAgents() {
		return agents;
	}

	public void setAgents(HashMap<String, Agent> agents) {
		this.agents = agents;
	}

	public ArrayBlockingQueue<Message> getOutBoundQueue() {
		return outBoundQueue;
	}

	public void setOutBoundQueue(ArrayBlockingQueue<Message> messageQueue) {
		this.outBoundQueue = messageQueue;
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
