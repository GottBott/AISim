package edu.udel.cisc475.aisim.simulation.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

/**
 * This class is a Thread that listens for connections and packages them up into
 * agent classes. The thread will not stop until all agents have connected (or a
 * bad agent connects).
 * 
 * @author Mike
 *
 */
public class ServerListenThread extends Thread {

	/**
	 * The port to start the server on.
	 */
	private int port;

	/**
	 * The ServerSocket of the server.
	 */
	private ServerSocket serverSocket;

	/**
	 * A map from the names of agents to the agents themselves. Used to for
	 * quick lookup of agents by name.
	 */
	private HashMap<String, Agent> agents;

	/**
	 * The set of names of agents to wait for.
	 */
	private HashSet<String> agentsToWaitFor;

	/**
	 * The default constructor for a ServerListenThread.
	 * 
	 * @param port
	 *            The port to start the server on.
	 * @param agentsToWaitFor
	 *            The set of names of agents to wait for.
	 */
	public ServerListenThread(int port, HashSet<String> agentsToWaitFor) {
		super();
		this.port = port;
		this.agentsToWaitFor = agentsToWaitFor;
		this.agents = new HashMap<String, Agent>();
		this.serverSocket = null;
	}

	/**
	 * An alternate constructor for a ServerListenThread. Mainly used for
	 * testing.
	 * 
	 * @param serverSocket
	 *            The ServerSocket of the server.
	 * @param agentsToWaitFor
	 *            The set of names of agents to wait for.
	 */
	public ServerListenThread(ServerSocket serverSocket,
			HashSet<String> agentsToWaitFor) {
		super();
		this.port = serverSocket.getLocalPort();
		this.serverSocket = serverSocket;
		this.agentsToWaitFor = agentsToWaitFor;
		this.agents = new HashMap<String, Agent>();
	}

	@Override
	/**
	 * Run method of the thread. Creates a ServerSocket and starts to listen for connections. Packages them up into the Agent class.
	 * Will not stop until either all agents have been accounted for or a bad agent has connected.
	 */
	public void run() {
		if (serverSocket == null) {
			try {
				serverSocket = new ServerSocket(port);
				System.out.println("Server running on "
						+ InetAddress.getLocalHost().getHostAddress() + ":"
						+ serverSocket.getLocalPort());
			} catch (IOException e) {
				System.err.println("Socket creation failed. Error: " + e);
				throw new ServerErrorException();
			}
		}

		while (!agentsToWaitFor.isEmpty()) {
			Socket agentSocket = null;
			try {
				agentSocket = serverSocket.accept();
				System.out.println("Agent connected from "
						+ agentSocket.getInetAddress());
			} catch (IOException e) {
				System.err.println("Accept failed. Error: " + e);
				throw new ServerErrorException();
			}
			if (agentSocket != null) {
				String nameJSONMsg = null;
				try {
					BufferedReader nameReader = new BufferedReader(
							new InputStreamReader(agentSocket.getInputStream()));
					nameJSONMsg = nameReader.readLine();
				} catch (IOException e) {
					System.err.println("Name read failed. Error: " + e);
					throw new ServerErrorException();
				}
				if (nameJSONMsg != null) {
					String name = extractNameFromJSON(nameJSONMsg);
					if (agentsToWaitFor.contains(name)) {
						agentsToWaitFor.remove(name);

						try {
							Agent agent = new Agent(name, agentSocket);
							agents.put(name, agent);
						} catch (IOException e) {
							System.err.println("Agent creation failed. Error: "
									+ e);
							throw new ServerErrorException();
						}

						System.out.println("Agent " + name + " connected.");
					} else {
						System.err.println("Agent " + name
								+ " already exists or shouldn't be here.");
						throw new ServerErrorException();
					}
				}
			}
		}
	}

	/**
	 * Gets the name out of a JSON message. The message should be in the format of an AgentRegistrationMessage.
	 * @param JSONMsg
	 * @return
	 */
	private String extractNameFromJSON(String JSONMsg) {
		Message message = Message.getMessageFromJSON(JSONMsg);
		if (message instanceof AgentRegistrationMessage) {
			String name = message.getSenderName();

			return name;
		}

		return null;
	}

	public HashMap<String, Agent> getAgents() {
		return agents;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}
}
