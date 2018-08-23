package edu.udel.cisc475.aisim.simulation.simulationstate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import edu.udel.cisc475.aisim.simulation.communication.AgentCommunicateThread;
import edu.udel.cisc475.aisim.tasktree.Node;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

public class Agent {
	private String name;
	private Socket socket;
	private PrintWriter outputWriter;
	private BufferedReader inputReader;
	private AgentCommunicateThread thread;
	private TaskTree visibleTree;
	
	public Agent(String name, Socket socket) throws IOException{
		this.name = name;
		this.socket = socket;
		this.outputWriter = new PrintWriter(socket.getOutputStream(), true);
		this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.visibleTree = new TaskTree();
		this.thread = new AgentCommunicateThread(this);
		thread.start();
	}
	
	public Agent(String name) {
		this.name = name;
	}

	public void write(String output) throws IOException {
		outputWriter.println(output);
	}
	
	public String read() throws IOException {
		return inputReader.readLine();
	}
	
	public boolean hasNextMessage() throws IOException {
		return inputReader.ready();
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
	
	public TaskTree getVisibleTree() {
		return visibleTree;
	}
	
	public void setVisibleTree(TaskTree visibleTree) {
		this.visibleTree = visibleTree;
	}
	
	public static void main(String[] args) {
		
	}
}
