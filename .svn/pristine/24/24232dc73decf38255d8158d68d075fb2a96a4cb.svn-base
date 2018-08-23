package edu.udel.cisc475.aisim.tasktree;

/**
 * This class represents a Method in the task tree. Methods are the leaves of
 * the tree and are the actual "work" done by agents.
 * 
 * @author Mike
 *
 */
public class Method extends Node {
	/**
	 * The name of the agent that this method is assigned to.
	 */
	private String agent;

	/**
	 * The duration of the method in ticks.
	 */
	private long duration;

	/**
	 * Indicates whether or not the method has been started by an agent.
	 */
	private boolean isStarted;

	/**
	 * The start time of the method (in ticks).
	 */
	private Long startTime;

	/**
	 * The default constructor for a Method.
	 * 
	 * @param parent
	 *            The task that is the parent of this method in the task tree.
	 * @param name
	 *            The name of the method.
	 * @param agent
	 *            The name of the agent that this method is assigned to.
	 */
	public Method(Task parent, String name, String agent) {
		super(parent, name);
		this.agent = agent;
		this.duration = 0;
		this.isStarted = false;
		this.startTime = -1L;
	}

	/**
	 * Enables this method. Called via an EnablesNodeRelationship.
	 */
	@Override
	public void enable() {
		if (!isStarted) {
			this.enabled = true;
		}
	}

	/**
	 * Disables this method. Called via a DisablesNodeRelationship.
	 */
	@Override
	public void disable() {
		if (!isStarted) {
			this.enabled = false;
		}
	}

	/**
	 * Facilitates this method. Called via a FacilitatesNodeRelationship.
	 */
	@Override
	public void facilitate(double qualityFactor, double durationFactor) {
		if (!isStarted) {
			this.quality *= (1 + qualityFactor);
			this.duration *= durationFactor;
		}
	}

	/**
	 * Hinders this method. Called via a HindersNodeRelationship.
	 */
	@Override
	public void hinder(double qualityFactor, double durationFactor) {
		if (!isStarted) {
			this.quality *= qualityFactor;
			this.duration *= (1 + durationFactor);
		}
	}

	/**
	 * Gets the earliestStartTime and deadline from this method's parent. If the
	 * parent's times aren't set, the values default to the start of the
	 * simulation and the end of maximum tick value.
	 */
	@Override
	public void computeEarliestStartAndDeadline() {
		if (parent != null) {
			if (parent.getEarliestStartTime() != -1) {
				this.earliestStartTime = parent.getEarliestStartTime();
			} else {
				this.earliestStartTime = 0;
			}
			if (parent.getDeadline() != -1) {
				this.deadline = parent.getDeadline();
			} else {
				this.deadline = Long.MAX_VALUE;
			}
		}
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void start() {
		this.isStarted = true;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long tick) {
		this.startTime = tick;
	}
}
