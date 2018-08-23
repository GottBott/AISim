package edu.udel.cisc475.aisim.simulation.communication;

import java.net.ServerSocket;
import java.util.ArrayList;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

public class ServerListenThread extends Thread{
	private ServerSocket socket;
	private ArrayList<Agent> agents;
	private int agentsToWaitFor;
		
	public ServerListenThread(ServerSocket socket, ArrayList<Agent> agents,
			int agentsToWaitFor) {
		super();
		this.socket = socket;
		this.agents = agents;
		this.agentsToWaitFor = agentsToWaitFor;
	}

	@Override
	public void run(){
		
	}

	public ServerSocket getSocket() {
		return socket;
	}

	public void setSocket(ServerSocket socket) {
		this.socket = socket;
	}

	public ArrayList<Agent> getAgents() {
		return agents;
	}

	public void setAgents(ArrayList<Agent> agents) {
		this.agents = agents;
	}

	public int getAgentsToWaitFor() {
		return agentsToWaitFor;
	}

	public void setAgentsToWaitFor(int agentsToWaitFor) {
		this.agentsToWaitFor = agentsToWaitFor;
	}
}
