package edu.udel.cisc475.aisim.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import edu.udel.cisc475.aisim.simulation.simulationstate.Agent;

public class OrderedLogger extends LogWriter {

	public OrderedLogger(String fileDestination, String fileName) {
		super(fileDestination, fileName);
		StartLog();
	}

	/**
	 * Logs information about agents at the end of the simulation
	 * 
	 * @param agents
	 * @return returns true if successful
	 */
	public Boolean LogAgents(HashMap<String, Agent> agents) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("\n##### Agent Statistics #####\n");
			for (Agent a : agents.values()) {
				out.println(createTab(1) + a.getName());
				out.println(createTab(2) + "Sent: " + a.getMessageSent());
				out.println(createTab(2) + "Received: "
						+ a.getMessageRecieved());
				out.print(createTab(2) + "Completed: ");
				for (int i = 0; i < a.getMethodsCompleated().size(); i++) {
					if (i != 0) {
						out.print(", ");
					}
					out.print(a.getMethodsCompleated().get(i).getName());

				}

				out.println("\n");

			}
			out.close();
		} catch (IOException e) {
			// do some exception handling
			return false;
		}
		return true;

	}

	/**
	 * adds header to the start of the log
	 * 
	 * @return true if successful
	 */
	public Boolean StartLog() {

		try {
			PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
			out.println("##### Method Results #####\n");
			out.close();

			return true;

		} catch (IOException e) {
			// do some exception handling
			e.printStackTrace();
			return false;
		}
	}

}
