package edu.udel.cisc475.aisim.simulation.communication;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

public class AgentCommunicateThread extends Thread{
	private Agent agent;
	private boolean simulationRunning;
	
	public AgentCommunicateThread(Agent agent, boolean simulationRunning) {
		super();
		this.agent = agent;
		this.simulationRunning = simulationRunning;
	}

	@Override
	public void run(){
		
	}
	
	void endSimulation(){
		
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public boolean isSimulationRunning() {
		return simulationRunning;
	}

	public void setSimulationRunning(boolean simulationRunning) {
		this.simulationRunning = simulationRunning;
	}

}
