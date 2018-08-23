package edu.udel.cisc475.aisim.simulation.simulationstate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import edu.udel.cisc475.aisim.tasktree.Method;
import edu.udel.cisc475.aisim.tasktree.TaskTree;

public class Agent {
	private String name;
	private Socket socket;
	private PrintWriter outputWriter;
	private BufferedReader inputReader;
	private TaskTree visibleTree;
	private int messageSent =0;
	private int messageReceived =0;
	private LinkedList<Method> methodsCompleated = new LinkedList<Method>();
	
	public Agent(String name, Socket socket) throws IOException{
		this.name = name;
		this.socket = socket;
		this.outputWriter = new PrintWriter(socket.getOutputStream(), true);
		this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.visibleTree = new TaskTree();
	}
	
	public void write(String output) throws IOException {
		outputWriter.println(output);
		messageReceived ++;
	}
	
	public String read() throws IOException {
		
		messageSent ++;
		return inputReader.readLine();
		
	}
	
	public boolean hasNextMessage() throws IOException {
		return inputReader.ready();
	}

	public String getName() {
		return name;
	}

	public Socket getSocket() {
		return socket;
	}
	
	public TaskTree getVisibleTree() {
		return visibleTree;
	}
	
	public void setVisibleTree(TaskTree visibleTree) {
		this.visibleTree = visibleTree;
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getMessageSent() {
		return messageSent;
	}


	public int getMessageRecieved() {
		return messageReceived;
	}

	public LinkedList<Method> getMethodsCompleated() {
		return methodsCompleated;
	}

	public void addCompleatedMethod(Method m) {
		this.methodsCompleated.add(m);
	}

	
}
