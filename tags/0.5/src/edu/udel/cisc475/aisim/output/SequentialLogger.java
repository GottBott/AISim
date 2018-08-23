package edu.udel.cisc475.aisim.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.udel.cisc475.aisim.simulation.communication.Message;

public class SequentialLogger extends LogWriter {

	public SequentialLogger(String fileDestination, String fileName) {
		super(fileDestination, fileName);
		StartLog();
	}

	/**
	 * Function to write the header to the sequential log
	 * 
	 * @return returns true if written successfully
	 */
	public Boolean StartLog() {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("##### Sequential Logger Results #####\n");
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Logs necessary information about a message that is received by the
	 * simulator
	 * 
	 * @param Message
	 *            m the message to log
	 * @return
	 */
	public Boolean LogMessageReceived(Message m) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n");
			out.println(createTab(1) + "Received: " + m.MethodType + "From "
					+ m.getSenderName());
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Logs necessary information about a message that is sent by the simulator
	 * 
	 * @param Message
	 *            m the message to log
	 * @return
	 */
	public Boolean LogMessageSent(Message m) {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n");
			out.println(createTab(1) + "Sent: " + m.MethodType + "To "
					+ m.getDestinationName());
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}

}
