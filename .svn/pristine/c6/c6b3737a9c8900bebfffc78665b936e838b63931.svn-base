package edu.udel.cisc475.aisim.simulation.simulationstate;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import edu.udel.cisc475.aisim.simulation.communication.AgentCommunicateThread;

public class Agent {
	private String name;
	private Socket socket;
	private PrintWriter outputWriter;
	private BufferedReader inputReader;
	private AgentCommunicateThread thread;
	
	public Agent(String name, Socket socket, PrintWriter outputWriter,
			BufferedReader inputReader, AgentCommunicateThread thread) {
		this.name = name;
		this.socket = socket;
		this.outputWriter = outputWriter;
		this.inputReader = inputReader;
		this.thread = thread;
	}

	public void write(){
		
	}
	
	public void read(){
		
	}
	
	public boolean hasNextMessage(){
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public PrintWriter getOutputWriter() {
		return outputWriter;
	}

	public void setOutputWriter(PrintWriter outputWriter) {
		this.outputWriter = outputWriter;
	}

	public BufferedReader getInputReader() {
		return inputReader;
	}

	public void setInputReader(BufferedReader inputReader) {
		this.inputReader = inputReader;
	}

	public AgentCommunicateThread getThread() {
		return thread;
	}

	public void setThread(AgentCommunicateThread thread) {
		this.thread = thread;
	}
}
