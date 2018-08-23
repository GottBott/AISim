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

public class ServerListenThread extends Thread {
	private int port;
	private ServerSocket serverSocket;
	private HashMap<String, Agent> agents;
	private HashSet<String> agentsToWaitFor;
	
	public ServerListenThread(int port, HashSet<String> agentsToWaitFor) {
		super();
		this.port = port;
		this.agentsToWaitFor = agentsToWaitFor;
		this.agents = new HashMap<String, Agent>();
		this.serverSocket = null;
	}
	
	public ServerListenThread(ServerSocket serverSocket, HashSet<String> agentsToWaitFor) {
		super();
		this.port = serverSocket.getLocalPort();
		this.serverSocket = serverSocket;
		this.agentsToWaitFor = agentsToWaitFor;
		this.agents = new HashMap<String, Agent>();
	}

	@Override
	public void run() {
		if (serverSocket == null) {
			try {
				serverSocket = new ServerSocket(port);
				System.out.println("Server running on " + InetAddress.getLocalHost().getHostAddress() + ":" + serverSocket.getLocalPort());
			} catch (IOException e) {
				System.err.println("Socket creation failed. Error: " + e);
				throw new ServerErrorException();
			}
		}
		
		while (!agentsToWaitFor.isEmpty()) {
			Socket agentSocket = null;
			try {
				agentSocket = serverSocket.accept();
				System.out.println("Agent connected from " + agentSocket.getInetAddress());
			} catch (IOException e) {
				System.err.println("Accept failed. Error: " + e);
				throw new ServerErrorException();
			}
			if (agentSocket != null) {
				String nameJSONMsg = null;
				try {
					BufferedReader nameReader = new BufferedReader(new InputStreamReader(agentSocket.getInputStream()));
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
							System.err.println("Agent creation failed. Error: " + e);
							throw new ServerErrorException();
						}
						
						System.out.println("Agent " + name + " connected.");
					}
					else {
						System.err.println("Agent " + name + " already exists or shouldn't be here.");
						throw new ServerErrorException();
					}
				}
			}
		}
	}
	
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
	
//	public static void main(String[] args) {
//		HashSet<String> toWait = new HashSet<String>();
//		toWait.add("Agent1");
//		toWait.add("Agent2");
//		toWait.add("Agent3");
//		
//		ServerListenThread listenThread = new ServerListenThread(9876, toWait);
//		listenThread.start();
//		
//		while (listenThread.isAlive());
//		
//		System.out.println("Finished");
//	}
}
