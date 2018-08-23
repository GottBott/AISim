package edu.udel.cisc475.aisim.simulation.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

public class ServerListenThreadTest implements Thread.UncaughtExceptionHandler{
	
	private static final int PORT = 9876;
	
	static class TestAgent {
		Socket socket;
		PrintWriter writer;
		String name;
		int port;
		
		TestAgent(String name, int port) {
			this.name = name;
			this.port = port;
		}
		
		void connect() {
			try {
				this.socket = new Socket("127.0.0.1", port);
				this.writer = new PrintWriter(socket.getOutputStream(), true);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		void sendName() {
			Message message = new AgentRegistrationMessage(name);
			writer.println(message.toJSON());
		}
		
		void sendBadMessage() {
			Message message = new SetRandomSeedMessage(name, 1L);
			writer.println(message.toJSON());
		}
	}
	
	static class BadServerSocketThrowsException extends ServerSocket {
		BadServerSocketThrowsException(int port) throws IOException {
			super(port);
		}
		
		@Override
		public Socket accept() throws IOException {
			throw new IOException();
		}
	}
	
	static class BadServerSocketAcceptsBadReadSocket extends ServerSocket {
		BadServerSocketAcceptsBadReadSocket(int port) throws IOException {
			super(port);
		}
		
		@Override
		public Socket accept() throws IOException {
			return new BadReadSocket();
		}
	}
	
	static class BadServerSocketAcceptsBadWriteSocket extends ServerSocket {
		BadServerSocketAcceptsBadWriteSocket(int port) throws IOException {
			super(port);
		}
		
		@Override
		public Socket accept() throws IOException {
			Socket socket = super.accept();
			return new BadWriteSocket(socket);
		}
	}
	
	static class BadReadSocket extends Socket {
		BadReadSocket() {
			super();
		}
		
		@Override
		public InputStream getInputStream() throws IOException {
			throw new IOException();
		}
	}
	
	static class BadWriteSocket extends Socket {
		Socket socket;
		BadWriteSocket(Socket socket) {
			super();
			this.socket = socket;
		}
		
		@Override
		public OutputStream getOutputStream() throws IOException {
			throw new IOException();
		}
		
		@Override
		public InputStream getInputStream() throws IOException {
			return socket.getInputStream();
		}
	}
	
	static ServerListenThread thread;
	static HashSet<String> agentsToWaitFor;
	static TestAgent agent1;
	static TestAgent agent2;
	static boolean exceptionThrown;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("ServerListenThreadTest");
	}
	
	@Test
	public void testRun() {
		agentsToWaitFor = new HashSet<String>();
		agentsToWaitFor.add("Agent 1");
		agentsToWaitFor.add("Agent 2");
		thread = new ServerListenThread(PORT, agentsToWaitFor);
		agent1 = new TestAgent("Agent 1", PORT);
		agent2 = new TestAgent("Agent 2", PORT);
		
		thread.start();
		agent1.connect();
		agent1.sendName();
		agent2.connect();
		agent2.sendName();
		while (thread.isAlive());
		
		Assert.assertFalse(thread.isAlive());
		HashMap<String, Agent> agentMap = thread.getAgents();
		
		Assert.assertTrue(agentMap.containsKey("Agent 1"));
		Assert.assertTrue(agentMap.containsKey("Agent 2"));
		Assert.assertFalse(agentMap.containsKey("Agent 3"));
		
		try {
			thread.getServerSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBadRunSocketFail() {
		exceptionThrown = false;
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			thread = new ServerListenThread(PORT, null);
			thread.setUncaughtExceptionHandler(this);
			thread.start();
			while(thread.isAlive());
			Assert.assertTrue(exceptionThrown);
			thread.setUncaughtExceptionHandler(null);
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testBadRunAcceptFail() {
		exceptionThrown = false;
		
		agentsToWaitFor = new HashSet<String>();
		agentsToWaitFor.add("Agent 1");
		
		
		try {
			BadServerSocketThrowsException bs = new BadServerSocketThrowsException(PORT);
			thread = new ServerListenThread(bs, agentsToWaitFor);
			thread.setUncaughtExceptionHandler(this);
			thread.start();
			while(thread.isAlive());
			Assert.assertTrue(exceptionThrown);
			thread.setUncaughtExceptionHandler(null);
			bs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testBadRunReadFail() {
		exceptionThrown = false;
		
		agentsToWaitFor = new HashSet<String>();
		agentsToWaitFor.add("Agent 1");
		
		try {
			BadServerSocketAcceptsBadReadSocket bs = new BadServerSocketAcceptsBadReadSocket(PORT);
			thread = new ServerListenThread(bs, agentsToWaitFor);
			thread.setUncaughtExceptionHandler(this);
			thread.start();
			while(thread.isAlive());
			Assert.assertTrue(exceptionThrown);
			thread.setUncaughtExceptionHandler(null);
			bs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBadRunCreateFail() {
		exceptionThrown = false;
		
		agentsToWaitFor = new HashSet<String>();
		agentsToWaitFor.add("Agent 1");
		agent1 = new TestAgent("Agent 1", PORT);
		
		try {
			BadServerSocketAcceptsBadWriteSocket bs = new BadServerSocketAcceptsBadWriteSocket(PORT);
			thread = new ServerListenThread(bs, agentsToWaitFor);
			thread.setUncaughtExceptionHandler(this);
			thread.start();
			agent1.connect();
			agent1.sendName();
			while(thread.isAlive());
			Assert.assertTrue(exceptionThrown);
			thread.setUncaughtExceptionHandler(null);
			bs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBadRunNullName() {
		exceptionThrown = false;
		
		agentsToWaitFor = new HashSet<String>();
		agentsToWaitFor.add("Agent 1");
		thread = new ServerListenThread(PORT, agentsToWaitFor);
		thread.setUncaughtExceptionHandler(this);
		agent1 = new TestAgent("Agent 1", PORT);
		
		thread.start();
		agent1.connect();
		agent1.sendBadMessage();
		while (thread.isAlive());
		Assert.assertTrue(exceptionThrown);
		thread.setUncaughtExceptionHandler(null);
		
		try {
			thread.getServerSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		if (t instanceof ServerListenThread) {
			if (e instanceof ServerErrorException) {
				exceptionThrown = true;
			}
		}
	}
	
}
