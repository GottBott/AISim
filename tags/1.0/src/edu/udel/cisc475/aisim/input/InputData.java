package edu.udel.cisc475.aisim.input;

import java.util.HashSet;

import edu.udel.cisc475.aisim.tasktree.TaskTree;

/**
 * This class holds the data that was parsed out of the input .ctaems file.
 * 
 * @author Mike
 *
 */
public class InputData {
	/**
	 * The TaskTree created from the input file.
	 */
	private TaskTree tree;

	/**
	 * The version of the cTAEMS file represented as a string.
	 */
	private String version;

	/**
	 * A HashSet of all of the agents.
	 */
	private HashSet<String> agents;

	public InputData(TaskTree tree, String version, HashSet<String> agents) {
		this.tree = tree;
		this.agents = agents;
		this.version = version;
	}

	public TaskTree getTree() {
		return tree;
	}

	public HashSet<String> getAgents() {
		return agents;
	}

	public String getVersion() {
		return version;
	}
}
