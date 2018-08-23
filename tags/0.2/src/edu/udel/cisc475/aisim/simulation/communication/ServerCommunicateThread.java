package edu.udel.cisc475.aisim.simulation.communication;

import java.util.ArrayList;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

public class ServerCommunicateThread extends Thread{
	private ArrayList<Agent> agents;
	private boolean simulationRunning;
	
	public ServerCommunicateThread(ArrayList<Agent> agents,
			boolean simulationRunning) {
		super();
		this.agents = agents;
		this.simulationRunning = simulationRunning;
	}

	@Override
	public void run(){
		
	}
	
	public void endSimulation(){
		
	}
	
	public void handleMessage(Message msg){
		
	}

	public ArrayList<Agent> getAgents() {
		return agents;
	}

	public void setAgents(ArrayList<Agent> agents) {
		this.agents = agents;
	}

	public boolean isSimulationRunning() {
		return simulationRunning;
	}

	public void setSimulationRunning(boolean simulationRunning) {
		this.simulationRunning = simulationRunning;
	}
}
