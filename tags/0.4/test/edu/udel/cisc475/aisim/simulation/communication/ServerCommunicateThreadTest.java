package edu.udel.cisc475.aisim.simulation.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Permission;
import java.util.HashMap;

import org.json.*;
import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

public class ServerCommunicateThreadTest implements Thread.UncaughtExceptionHandler{
	
	static class TestConnection {
		Socket socket;
		PrintWriter writer;
		BufferedReader reader;
		int port;
		
		TestConnection(int port) {
			this.port = port;
		}
		
		void connect() {
			try {
				this.socket = new Socket("127.0.0.1", port);
				this.writer = new PrintWriter(socket.getOutputStream(), true);
				this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		void close() {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class BadAgent extends Agent {
		
		BadAgent(String name, Socket socket) throws IOException{
			super(name, socket);
		}
		
		@Override
		public void write(String output) throws IOException {
			throw new IOException();
		}
		
		@Override
		public String read() throws IOException {
			throw new IOException();
		}
		
		@Override
		public boolean hasNextMessage() throws IOException {
			throw new IOException();
		}
	}
	
	final static int PORT = 9874;
	
	static Agent a1;
	static Agent a2;
	static BadAgent ba1;
	static BadAgent ba2;
	static TestConnection c1;
	static TestConnection c2;
	static ServerSocket s;
	static ServerCommunicateThread comm;
	boolean exceptionThrown = false;
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("ServerCommunicateThreadTest");
	}
	
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			s = new ServerSocket(PORT);
			c1 = new TestConnection(PORT);
			c2 = new TestConnection(PORT);
			
			new Thread() {
				public void run() {
					c1.connect();
					c2.connect();
				}
			}.start();
			
			Socket a1s = s.accept();
			Socket a2s = s.accept();
			a1 = new Agent("A1", a1s);
			a2 = new Agent("A2", a2s);
			ba1 = new BadAgent("BA1", a1s);
			ba2 = new BadAgent("BA2", a2s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		try {
			s.close();
			c1.close();
			c2.close();
			a1.close();
			a2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRun() {
		HashMap<String, Agent> agents = new HashMap<String, Agent>();
		agents.put("A1", a1);
		agents.put("A2", a2);
		comm = new ServerCommunicateThread(agents);
		comm.start();
		
		c1.writer.println(new AgentRegistrationMessage("A1").toJSON());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertFalse(comm.getInBoundQueue().isEmpty());
		Message message = comm.getInBoundQueue().remove();
		Assert.assertNotNull(message);
		Assert.assertTrue(message instanceof AgentRegistrationMessage);
		Assert.assertEquals("A1", message.getSenderName());
		
		c1.writer.println(new AgentRegistrationMessage("A1").toJSON());
		c2.writer.println(new AgentRegistrationMessage("A2").toJSON());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertFalse(comm.getInBoundQueue().isEmpty());
		message = comm.getInBoundQueue().remove();
		Assert.assertNotNull(message);
		Assert.assertTrue(message instanceof AgentRegistrationMessage);
		message = comm.getInBoundQueue().remove();
		Assert.assertNotNull(message);
		Assert.assertTrue(message instanceof AgentRegistrationMessage);
		
		comm.endSimulation();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertFalse(comm.isAlive());
	}
	
	@Test
	public void testHandleMessage() {
		HashMap<String, Agent> agents = new HashMap<String, Agent>();
		agents.put("A1", a1);
		agents.put("A2", a2);
		comm = new ServerCommunicateThread(agents);
		
		SetRandomSeedMessage msg1 = new SetRandomSeedMessage("A1", 1L);
		SetRandomSeedMessage msg2 = new SetRandomSeedMessage("A2", 2L);
		
		comm.handleMessage(msg1);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean read = false;
		try {
			if (c1.reader.ready()) {
				read = true;
				String json = c1.reader.readLine();
				JSONObject obj = new JSONObject(json);
				Assert.assertEquals("SIMULATOR", obj.getJSONObject("Message").getString("MsgSender"));
				Assert.assertEquals("A1", obj.getJSONObject("Message").getString("MsgDest"));
				Assert.assertEquals(1L, obj.getJSONObject("Message").getLong("Seed"));
			}
			else if (c2.reader.ready()) {
				read = true;
				String json = c2.reader.readLine();
				JSONObject obj = new JSONObject(json);
				Assert.assertEquals("SIMULATOR", obj.getJSONObject("Message").getString("MsgSender"));
				Assert.assertEquals("A1", obj.getJSONObject("Message").getString("MsgDest"));
				Assert.assertEquals(1L, obj.getJSONObject("Message").getLong("Seed"));
			}
			Assert.assertTrue(read);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		comm.handleMessage(msg2);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		read = false;
		try {
			if (c1.reader.ready()) {
				read = true;
				String json = c1.reader.readLine();
				JSONObject obj = new JSONObject(json);
				Assert.assertEquals("SIMULATOR", obj.getJSONObject("Message").getString("MsgSender"));
				Assert.assertEquals("A2", obj.getJSONObject("Message").getString("MsgDest"));
				Assert.assertEquals(2L, obj.getJSONObject("Message").getLong("Seed"));
			}
			else if (c2.reader.ready()) {
				read = true;
				String json = c2.reader.readLine();
				JSONObject obj = new JSONObject(json);
				Assert.assertEquals("SIMULATOR", obj.getJSONObject("Message").getString("MsgSender"));
				Assert.assertEquals("A2", obj.getJSONObject("Message").getString("MsgDest"));
				Assert.assertEquals(2L, obj.getJSONObject("Message").getLong("Seed"));
			}
			Assert.assertTrue(read);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBadRun() {
		exceptionThrown = false;
		HashMap<String, Agent> agents = new HashMap<String, Agent>();
		agents.put("BA1", ba1);
		agents.put("BA2", ba2);
		comm = new ServerCommunicateThread(agents);
		comm.setUncaughtExceptionHandler(this);
		comm.start();
		while(comm.isAlive());
		Assert.assertTrue(exceptionThrown);
		comm.setUncaughtExceptionHandler(null);
	}
	
	@Test(expected=ServerErrorException.class)
	public void testBadHandleMessage() {
		HashMap<String, Agent> agents = new HashMap<String, Agent>();
		agents.put("BA1", ba1);
		agents.put("BA2", ba2);
		comm = new ServerCommunicateThread(agents);
		SetRandomSeedMessage msg = new SetRandomSeedMessage("BA1", 1L);
		comm.handleMessage(msg);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		if (t instanceof ServerCommunicateThread) {
			if (e instanceof ServerErrorException) {
				exceptionThrown = true;
			}
		}
	}
}
