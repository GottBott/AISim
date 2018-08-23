package edu.udel.cisc475.aisim.simulation.simulationstate;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.udel.cisc475.aisim.input.ConfigurationData;
import edu.udel.cisc475.aisim.input.InputData;
import edu.udel.cisc475.aisim.output.Logger;
import edu.udel.cisc475.aisim.simulation.communication.AbortMethodMessage;
import edu.udel.cisc475.aisim.simulation.communication.AgentToAgentMessage;
import edu.udel.cisc475.aisim.simulation.communication.AskMethodStatusMessage;
import edu.udel.cisc475.aisim.simulation.communication.ConfirmMethodAbortMessage;
import edu.udel.cisc475.aisim.simulation.communication.ConfirmMethodStartMessage;
import edu.udel.cisc475.aisim.simulation.communication.EndSimulationMessage;
import edu.udel.cisc475.aisim.simulation.communication.InitialTreeMessage;
import edu.udel.cisc475.aisim.simulation.communication.Message;
import edu.udel.cisc475.aisim.simulation.communication.NextTickMessage;
import edu.udel.cisc475.aisim.simulation.communication.NotifyMethodCompletedMessage;
import edu.udel.cisc475.aisim.simulation.communication.NotifyMethodStatusMessage;
import edu.udel.cisc475.aisim.simulation.communication.NotifyRelationshipActivationMessage;
import edu.udel.cisc475.aisim.simulation.communication.ServerCommunicateThread;
import edu.udel.cisc475.aisim.simulation.communication.ServerListenThread;
import edu.udel.cisc475.aisim.simulation.communication.SetRandomSeedMessage;
import edu.udel.cisc475.aisim.simulation.communication.StartMethodMessage;
import edu.udel.cisc475.aisim.simulation.communication.StartSimulationMessage;
import edu.udel.cisc475.aisim.simulation.communication.UpdateTreeMessage;
import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.NodeRelationship;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

/**
 * This Class is responsible for managing the flow of the simulation. It Starts
 * the simulation, initiates agent communication, handles updates to the task
 * tree, logs events and terminates the simulation.
 * 
 * @author Mike, Ben
 *
 */

public class Simulator {
	/**
	 * Simulation Tick Length From Input
	 */
	private long tick;
	/**
	 * Simulation Tick Length
	 */
	private long tickLength;
	/**
	 * Simulation Max Number of Ticks
	 */
	private int maxTicks;
	/**
	 * Random Seed Value
	 */
	private long seed;
	/**
	 * Port The Simulation is Running On
	 */
	private int port;
	/**
	 * Mapping of Agents to Their Names
	 */
	private HashMap<String, Agent> agents;
	/**
	 * Set of Agent String Names
	 */
	private HashSet<String> agentNames;
	/**
	 * Thread to Handle Agent Communication
	 */
	private ServerCommunicateThread commThread;
	/**
	 * List of Methods that Are Currently In Progress
	 */
	private ArrayList<Method> runningMethods;
	/**
	 * The Task Tree Containing All Tasks and Methods
	 */
	private TaskTree tree;
	/**
	 * Socket the Simulation is Connecting Through
	 */
	private ServerSocket serverSocket;
	
	
	/**
	 * 
	 * @param configData
	 *            Configuration data from input or generated by parser with
	 *            default values if no input exists
	 * @param inputData
	 */
	public Simulator(ConfigurationData configData, InputData inputData) {
		this.seed = configData.getSeed();
		this.tickLength = configData.getTickLength();
		this.maxTicks = configData.getMaxTicks();
		this.port = configData.getPort();
		this.tree = inputData.getTree();
		this.agentNames = inputData.getAgents();
		System.out.println(agentNames.toString());
		this.tick = 0;
		this.runningMethods = new ArrayList<Method>();
		
		// Set up the logging class
		Logger.initilizeLogger(configData.getOutputDestination());
		
	}

	/**
	 * Starts the agent connection server and calls prepare simulation
	 * 
	 * @return True if no exceptions were thrown
	 */
	public boolean startServer() {
		ServerListenThread listenThread = new ServerListenThread(port,
				new HashSet<String>(agentNames));
		listenThread.start();
		while (listenThread.isAlive())
			;
		agents = listenThread.getAgents();
		serverSocket = listenThread.getServerSocket();
		return prepareSimulation();
	}

	/**
	 * Sets up communication thread Calls initial data to agents Calls start
	 * simulation
	 * 
	 * @return True if no exceptions were thrown
	 */
	private boolean prepareSimulation() {
		commThread = new ServerCommunicateThread(agents);
		commThread.start();

		sendInitialData();

		return startSimulation();
	}

	/**
	 * Sends initial data to all agents such as initial tree and random seed
	 */
	private void sendInitialData() {
		// Send seed
		for (String name : agentNames) {
			SetRandomSeedMessage message = new SetRandomSeedMessage(name, seed);
			commThread.handleMessage(message);
	

		}

		tree.findVisibleToAgents();
		tree.computeEarliestStartAndDeadline();

		for (String name : agentNames) {
			InitialTreeMessage message = new InitialTreeMessage(name, tree);
			commThread.handleMessage(message);
			

		}
	}

	/**
	 * Starts the simulation clock and advances the simulation Checks to see if
	 * the simulation is over before returning
	 * 
	 * @return True if no exceptions were thrown
	 */
	private boolean startSimulation() {

		for (String name : agentNames) {
			StartSimulationMessage message = new StartSimulationMessage(name, agentNames);
			commThread.handleMessage(message);

		}

		// Main loop
		long lastTick = System.currentTimeMillis();
		long currentTime;
		while (!tree.isFinished(tick)) {
			if(tick < maxTicks){
				currentTime = System.currentTimeMillis();
				if ((currentTime - lastTick) > tickLength) {
					tick++;
					
					// Send a NextTickMessage for syncing
					 for (String name : agentNames) {
						 NextTickMessage message = new NextTickMessage(name, tick);
						 commThread.handleMessage(message);
					 }
		
					boolean arrivals = tree.findArrivals(tick); // Find all nodes
																// that have
																// recently arrived.
					if (arrivals) {
				
						for (String name : agentNames) {
							UpdateTreeMessage message = new UpdateTreeMessage(name,
									tree);
							commThread.handleMessage(message);
						}
						// Activate all unactivated relationships in the tree that
						// should now be activated and tell the agents that can see them
						// about it
						HashMap<String, HashSet<String>> activatedRelationships = tree
								.activateAllRelationships();
						for (String relationshipName : activatedRelationships.keySet()) {
							HashSet<String> agentsToInform = activatedRelationships
									.get(relationshipName);
		
							for (String agent : agentsToInform) {
								NotifyRelationshipActivationMessage message = new NotifyRelationshipActivationMessage(
										agent, relationshipName,tree.getRelationshipByName(relationshipName).getType(), tree.getRelationshipByName(relationshipName).getToNode().getName() );
								commThread.handleMessage(message);
								
								
		
							}
						}
					}
					replyToAllMessages();
					handleFinishedMethods();
					lastTick = currentTime;
				}
			}
			else{
				System.out.println("\nWARNING");
				System.out.println("Simulation length limit reached: " + tick);
				System.out.println("This can be adjusted in the config.ini\n");
				break;
			}
		}

		// Disconnect agents once the simulation is done.
		// Stop trying to receive messages from agents
		commThread.endSimulation();
		// Tell agents the simulation is over
		for (String name : agentNames) {
			EndSimulationMessage message = new EndSimulationMessage(name);
			commThread.handleMessage(message);
			// Disconnect the agent
			agents.get(name).close();
		}

		printResults();

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Agent a : agents.values()) {
			Logger.logAgent(a);
		}
		Logger.logAll(tick, tree.getTotalQuality(),seed);

		return true;
		// Graceful exit
	}

	/**
	 * Prints final results of simulation as well as location of output log.
	 */
	private void printResults() {
		System.out.println("End of simulation.");
		System.out.println("Results:\n");
		System.out.println("\tFinal Duration:\t" + tick);
		System.out.println("\tFinal Quality:\t" + tree.getTotalQuality());
		System.out.println("Log files can be located at: \n"
				+ Logger.getPathToFiles());
	}

	/**
	 * Loops through messages sent to simulator by agents Calls replyToMessage
	 */
	private void replyToAllMessages() {
		ArrayList<Message> inBoundQueue = new ArrayList<Message>();
		commThread.getInBoundQueue().drainTo(inBoundQueue);
		for (Message message : inBoundQueue) {
			replyToMessage(message);
		}
	}

	/**
	 * Replies to Agent Messages
	 * 
	 * @param message
	 *            The message that warrants a response
	 */
	private void replyToMessage(Message message) {
		if (message instanceof AskMethodStatusMessage) {
			// AskMethodStatusMessage
			String agentName = message.getSenderName();
			String methodName = ((AskMethodStatusMessage) message)
					.getMethodName();
			Method method = (Method) tree.getNodeWithName(methodName);
			NotifyMethodStatusMessage reply = new NotifyMethodStatusMessage(
					agentName, methodName, method.isStarted(),
					method.isFinished(), method.isEnabled());
			commThread.handleMessage(reply);
		} else if (message instanceof StartMethodMessage) {
			// StartMethodMessage
			String agentName = message.getSenderName();
			String methodName = ((StartMethodMessage) message).getMethodName();
			Method method = (Method) tree.getNodeWithName(methodName);
			ConfirmMethodStartMessage reply;
			if (agentName.equals(method.getAgent()) && method.isEnabled()
					&& !method.isFinished() && !method.isStarted()
					&& tick >= method.getEarliestStartTime()
					&& tick < method.getDeadline() && !agents.get(method.getAgent()).getIsBusy()) {
				runningMethods.add(method);
				method.start();
				method.setStartTime(tick);
				agents.get(method.getAgent()).setIsBusy(true);
				reply = new ConfirmMethodStartMessage(agentName, methodName,
						true);

			} else {
				reply = new ConfirmMethodStartMessage(agentName, methodName,
						false);
			}

			commThread.handleMessage(reply);
			
		} 
		else if (message instanceof AbortMethodMessage) {
			// AbortMethodMessage
			String agentName = message.getSenderName();
			String methodName = ((AbortMethodMessage) message).getMethodName();
			Method method = (Method) tree.getNodeWithName(methodName);
			ConfirmMethodAbortMessage reply;
			if (agentName.equals(method.getAgent()) && method.isStarted()
					&& !method.isFinished() ) {
				runningMethods.remove(method); ////
				method.abort();
				method.setStartTime(-1L);
				agents.get(method.getAgent()).setIsBusy(false);
				reply = new ConfirmMethodAbortMessage(agentName, methodName,
						true);

			} else {
				reply = new ConfirmMethodAbortMessage(agentName, methodName,
						false);
			}

			commThread.handleMessage(reply);
			
		} 
		else if (message instanceof AgentToAgentMessage) {
			// AgentToAgentMessage
			commThread.handleMessage(message);
		}
	}

	/**
	 * Handles Methods that are finished by setting their flag
	 */
	private void handleFinishedMethods() {
		HashSet<Method> methodsToRemove = new HashSet<Method>();
		for (Method method : runningMethods) {

			if (method.getDuration() == (tick - method.getStartTime())) {
				// Method finished

				// Log Relationship
				for (NodeRelationship nr : method.getRelationships()) {
					Logger.logRelationship(nr);
				}
				
				methodsToRemove.add(method);
				method.setFinished(true);
				agents.get(method.getAgent()).setIsBusy(false);
				// Activate all unactivated relationships in the tree that
				// should now be activated and tell the agents that can see them
				// about it
				HashMap<String, HashSet<String>> activatedRelationships = tree
						.activateAllRelationships();
				for (String relationshipName : activatedRelationships.keySet()) {
					HashSet<String> agentsToInform = activatedRelationships
							.get(relationshipName);
//					System.out.println(relationshipName);
//					System.out.println(tree.getRelationshipByName(relationshipName).getToNode().getName());
//					System.out.println(tree.getRelationshipByName(relationshipName).getFromNode().getName());
					for (String agent : agentsToInform) {
						NotifyRelationshipActivationMessage message = new NotifyRelationshipActivationMessage(
								agent, relationshipName,tree.getRelationshipByName(relationshipName).getType(), tree.getRelationshipByName(relationshipName).getToNode().getName() );
						commThread.handleMessage(message);
						

					}
				}

				String agentName = method.getAgent();
				String methodName = method.getName();
				double quality = method.getQuality();
				double duration = method.getDuration();
				agents.get(method.getAgent()).addCompleatedMethod(method);
				Logger.logMethod(method);
				NotifyMethodCompletedMessage message = new NotifyMethodCompletedMessage(
						agentName, methodName, quality, duration);
				commThread.handleMessage(message);
			}
		}
		runningMethods.removeAll(methodsToRemove);
	}
}
