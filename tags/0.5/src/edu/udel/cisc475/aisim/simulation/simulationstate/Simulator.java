package edu.udel.cisc475.aisim.simulation.simulationstate;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import edu.udel.cisc475.aisim.input.ConfigurationData;
import edu.udel.cisc475.aisim.input.InputData;
import edu.udel.cisc475.aisim.output.OrderedLogger;
import edu.udel.cisc475.aisim.output.SequentialLogger;
import edu.udel.cisc475.aisim.simulation.communication.AskMethodStatusMessage;
import edu.udel.cisc475.aisim.simulation.communication.ConfirmMethodStartMessage;
import edu.udel.cisc475.aisim.simulation.communication.EndSimulationMessage;
import edu.udel.cisc475.aisim.simulation.communication.InitialTreeMessage;
import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.communication.NotifyMethodCompletedMessage;
import edu.udel.cisc475.aisim.simulation.communication.NotifyMethodStatusMessage;
import edu.udel.cisc475.aisim.simulation.communication.NotifyRelationshipActivationMessage;
import edu.udel.cisc475.aisim.simulation.communication.ServerCommunicateThread;
import edu.udel.cisc475.aisim.simulation.communication.ServerListenThread;
import edu.udel.cisc475.aisim.simulation.communication.SetRandomSeedMessage;
import edu.udel.cisc475.aisim.simulation.communication.StartMethodMessage;
import edu.udel.cisc475.aisim.simulation.communication.StartSimulationMessage;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

public class Simulator {
	private Long tick;
	private long tickLength;
	private long seed;
	private int port;
	private HashMap<String, Agent> agents;
	private HashSet<String> agentNames;
	private ServerCommunicateThread commThread;
	private ArrayList<Method> runningMethods;
	private TaskTree tree;
	private ServerSocket serverSocket;
	private OrderedLogger orderedLogger;
	private SequentialLogger sequentialLogger;

	
	public Simulator(ConfigurationData configData, InputData inputData) {
		this.seed = configData.getSeed();
		this.tickLength = configData.getTickLength();
		this.port = configData.getPort();
		this.tree = inputData.getTree();
		this.agentNames = inputData.getAgents();
		this.tick = configData.getTickLength();
		this.runningMethods = new ArrayList<Method>();
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
	    Date resultdate = new Date(time);
		this.orderedLogger = new OrderedLogger(configData.getOutputDestination(), "Log_Ordered_" + sdf.format(resultdate) + ".txt");
		this.sequentialLogger = new SequentialLogger(configData.getOutputDestination(), "Log_Sequential_" + sdf.format(resultdate) + ".txt");

	}

	public boolean startServer() {
		ServerListenThread listenThread = new ServerListenThread(port, new HashSet<String>(agentNames));
		listenThread.start();
		while (listenThread.isAlive());
		agents = listenThread.getAgents();
		serverSocket = listenThread.getServerSocket();
		return prepareSimulation();
	}
	
	private boolean prepareSimulation() {
		commThread = new ServerCommunicateThread(agents);
		commThread.start();
		
		sendInitialData();
		
		return startSimulation();
	}
	
	private void sendInitialData() {
		//Send seed
		for (String name : agentNames) {
			SetRandomSeedMessage message = new SetRandomSeedMessage(name, seed);
			commThread.handleMessage(message);
			sequentialLogger.LogMessageSent(message);

		}
		
		tree.findVisibleToAgents();
		tree.computeEarliestStartAndDeadline();
		
		for (String name : agentNames) {
			InitialTreeMessage message = new InitialTreeMessage(Message.SIMULATOR_NAME, name, tree);
			commThread.handleMessage(message);
			
		}
	}
	
	private boolean startSimulation() {
		long lastTick = System.currentTimeMillis();
		long currentTime;
		
		for (String name : agentNames) {
			StartSimulationMessage message = new StartSimulationMessage(name);
			commThread.handleMessage(message);
			sequentialLogger.LogMessageSent(message);

		}
		
		//Main loop
		while (!tree.isFinished(tick)) {
			currentTime = System.currentTimeMillis();
			if ((currentTime - lastTick) > tickLength) {
				tick++;
				//Send a NextTickMessage (for syncing). Might not be needed.
//				for (String name : agentNames) {
//					NextTickMessage message = new NextTickMessage(name, tick);
//					commThread.handleMessage(message);
//				}
				replyToAllMessages();
				handleFinishedMethods();
				lastTick = currentTime;
			}
		}
		
		
		//Disconnect agents once the simulation is done.
		//Stop trying to receive messages from agents
		commThread.endSimulation();
		//Tell agents the simulation is over
		for (String name : agentNames) {
			EndSimulationMessage message = new EndSimulationMessage(name);
			commThread.handleMessage(message);
			//Disconnect the agent
			agents.get(name).close();
		}
		
		printResults();
		
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		orderedLogger.LogAgents(agents);
		return true;
		// Graceful exit
	}
	
	private void printResults() {
		System.out.println("End of simulation.");
		System.out.println("Results:\n");
		System.out.println("\tFinal Duration:\t" + tick);
		System.out.println("\tFinal Quality:\t" + tree.getTotalQuality());
		System.out.println("Log files can be located at: " + orderedLogger.getPathToFile());
	}
	
	private void replyToAllMessages() {
		ArrayList<Message> inBoundQueue = new ArrayList<Message>();
		commThread.getInBoundQueue().drainTo(inBoundQueue);
		for (Message message : inBoundQueue) {
			sequentialLogger.LogMessageReceived(message);
			replyToMessage(message);
		}
	}
	
	private void replyToMessage(Message message) {
		if (message instanceof AskMethodStatusMessage) {
			//AskMethodStatusMessage
			String agentName = message.getSenderName();
			String methodName = ((AskMethodStatusMessage)message).getMethodName();
			Method method = (Method)tree.getNodeWithName(methodName);
			NotifyMethodStatusMessage reply = new NotifyMethodStatusMessage(agentName, methodName, method.isStarted(), 
																			method.isFinished(), method.isEnabled());
			commThread.handleMessage(reply);
		} else if (message instanceof StartMethodMessage) {
			//StartMethodMessage
			String agentName = message.getSenderName();
			String methodName = ((StartMethodMessage)message).getMethodName();
			Method method = (Method)tree.getNodeWithName(methodName);
			ConfirmMethodStartMessage reply;
			if (agentName.equals(method.getAgent()) && method.isEnabled() && !method.isFinished() && !method.isStarted() 
					&& tick >= method.getEarliestStartTime() && tick < method.getDeadline()) {
				runningMethods.add(method);
				method.start();
				method.setStartTime(tick);
				reply = new ConfirmMethodStartMessage(agentName, methodName, true);
				
			}
			else {
				reply = new ConfirmMethodStartMessage(agentName, methodName, false);
			}
		

			commThread.handleMessage(reply);
			sequentialLogger.LogMessageSent(reply);
		}
	}
	
	private void handleFinishedMethods() {
		HashSet<Method> methodsToRemove = new HashSet<Method>();
		for (Method method : runningMethods) {

			if (method.getDuration() == (tick - method.getStartTime())) {
				//Method finished
				
				methodsToRemove.add(method);
				method.setFinished(true);
				//Activate all unactivated relationships in the tree that should now be activated and tell the agents that can see them about it
				//TODO: Log relationship activation
				HashMap<String, HashSet<String>> activatedRelationships = tree.activateAllRelationships();
				for (String relationshipName : activatedRelationships.keySet()) {
					HashSet<String> agentsToInform = activatedRelationships.get(relationshipName);
					for (String agent : agentsToInform) {
						NotifyRelationshipActivationMessage message = new NotifyRelationshipActivationMessage(agent, relationshipName);
						commThread.handleMessage(message);
					}
				}
				
				String agentName = method.getAgent();
				String methodName = method.getName();
				double quality = method.getQuality();
				double duration = method.getDuration();
				agents.get(method.getAgent()).addCompleatedMethod(method);
				orderedLogger.LogMethodEvent(method);
				sequentialLogger.LogMethodEvent(method);
				NotifyMethodCompletedMessage message = new NotifyMethodCompletedMessage(agentName, methodName, quality, duration);
				commThread.handleMessage(message);
			}
		}
		runningMethods.removeAll(methodsToRemove);
	}
}
