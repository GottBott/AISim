package edu.udel.cisc475.aisim.output;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is responsible for logging the output of the simulation to the UI.
 *
 */
public class Logger {
	private int configSwitches;
	enum configBits{}
	private String logFileDestination;
	private HashMap<String, Integer> agentToAgentCommunications;
	private HashMap<String, Double> taskCosts;
	private HashMap<String, Double> taskQualities;
	private ArrayList<String> failedTasks;
	private ArrayList<String> successfulTasks;
	
	public Logger(int configSwitches, String logFileDestination,
			HashMap<String, Integer> agentToAgentCommunications,
			HashMap<String, Double> taskCosts,
			HashMap<String, Double> taskQualities,
			ArrayList<String> failedTasks, ArrayList<String> successfulTasks) {
		this.configSwitches = configSwitches;
		this.logFileDestination = logFileDestination;
		this.agentToAgentCommunications = agentToAgentCommunications;
		this.taskCosts = taskCosts;
		this.taskQualities = taskQualities;
		this.failedTasks = failedTasks;
		this.successfulTasks = successfulTasks;
	}

	public void logAgentToAgentCommunication(String str, int num){
		
	}
	
	public void logTask(String str, boolean bool, int num1, int num2){
		
	}
	
	public void setConfigSwitches(int num){
		
	}
	
	public void displayLog(){
		
	}
	
	public void writeLogFile(){
		
	}

	public String getLogFileDestination() {
		return logFileDestination;
	}

	public void setLogFileDestination(String logFileDestination) {
		this.logFileDestination = logFileDestination;
	}

	public HashMap<String, Integer> getAgentToAgentCommunications() {
		return agentToAgentCommunications;
	}

	public void setAgentToAgentCommunications(
			HashMap<String, Integer> agentToAgentCommunications) {
		this.agentToAgentCommunications = agentToAgentCommunications;
	}

	public HashMap<String, Double> getTaskCosts() {
		return taskCosts;
	}

	public void setTaskCosts(HashMap<String, Double> taskCosts) {
		this.taskCosts = taskCosts;
	}

	public HashMap<String, Double> getTaskQualities() {
		return taskQualities;
	}

	public void setTaskQualities(HashMap<String, Double> taskQualities) {
		this.taskQualities = taskQualities;
	}

	public ArrayList<String> getFailedTasks() {
		return failedTasks;
	}

	public void setFailedTasks(ArrayList<String> failedTasks) {
		this.failedTasks = failedTasks;
	}

	public ArrayList<String> getSuccessfulTasks() {
		return successfulTasks;
	}

	public void setSuccessfulTasks(ArrayList<String> successfulTasks) {
		this.successfulTasks = successfulTasks;
	}

	public int getConfigSwitches() {
		return configSwitches;
	}
}
