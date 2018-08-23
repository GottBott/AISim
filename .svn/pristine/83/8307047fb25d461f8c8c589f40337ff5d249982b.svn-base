package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

/**
 * This class represents a Task in the task tree. Tasks aren't really "work"
 * that needs to be done in the tree, but they represent the logic that connects
 * methods. Tasks can have subtasks (including methods) and QAFs that determine
 * how they get their quality from their children.
 * 
 * @author Mike
 *
 */
public class Task extends Node {
	/**
	 * An enum that represents the QAF of a task.
	 * 
	 * NOT_SET: The QAF has yet to be set. AND: If all methods below the task
	 * have finished, the task's quality is set to the minimum quality of all
	 * children. Otherwise, the quality is set to 0. OR: The task's quality is
	 * set to the maximum of the qualities of all finished children. SUM: The
	 * task's quality is set to the sum of the qualities of all finished
	 * children.
	 * 
	 * @author Mike
	 *
	 */
	public enum QAF {
		NOT_SET, AND, OR, SUM
	}

	/**
	 * The list of subtasks of this task. Can include either tasks or methods.
	 */
	private ArrayList<Node> subTasks;

	/**
	 * The QAF of this task.
	 */
	private QAF qaf;

	/**
	 * The default constructor for a task.
	 * 
	 * @param parent
	 *            The task that is the parent of this task.
	 * @param name
	 *            The name of this task.
	 */
	public Task(Task parent, String name) {
		super(parent, name);
		this.qaf = QAF.NOT_SET;
		this.subTasks = new ArrayList<Node>();
	}

	/**
	 * Adds a subtask (task or method) to this task's list of subtasks.
	 * 
	 * @param s
	 *            The node to be added.
	 */
	public void addSubTask(Node s) {
		subTasks.add(s);
		s.setParent(this);
	}

	/**
	 * Called by an EnablesNodeRelationship. All of this task's children will
	 * have this method called on them (effectively enabling all methods below
	 * this task).
	 */
	@Override
	public void enable() {
		for (Node n : subTasks) {
			n.enable();
		}
	}

	/**
	 * Called by a DisablesNodeRelationship. All of this task's children will
	 * have this method called on them (effectively disabling all methods below
	 * this task).
	 */
	@Override
	public void disable() {
		for (Node n : subTasks) {
			n.disable();
		}
	}

	/**
	 * Called by a FacilitatesNodeRelationship. All of this task's children will
	 * have this method called on them (effectively facilitating all methods
	 * below this task).
	 */
	@Override
	public void facilitate(double qualityFactor, double durationFactor) {
		for (Node n : subTasks) {
			n.facilitate(qualityFactor, durationFactor);
		}
	}

	/**
	 * Called by a HindersNodeRelationship. All of this task's children will
	 * have this method called on them (effectively hindering all methods below
	 * this task).
	 */
	@Override
	public void hinder(double qualityFactor, double durationFactor) {
		for (Node n : subTasks) {
			n.hinder(qualityFactor, durationFactor);
		}
	}

	/**
	 * Computes the earliest start time and the deadline of this task based on
	 * its parent. This task compares the earliest start time and deadline of
	 * itself and its parent and chooses the most restrictive values for both
	 * between the two. It then recurses down the tree.
	 */
	@Override
	public void computeEarliestStartAndDeadline() {
		if (parent != null) {
			if (parent.getEarliestStartTime() != -1) {
				this.earliestStartTime = Math.max(
						parent.getEarliestStartTime(), earliestStartTime);
			}
			if (parent.getDeadline() != -1) {
				this.deadline = Math.min(parent.getDeadline(), deadline);
			}
		}
		for (Node n : subTasks) {
			n.computeEarliestStartAndDeadline();
		}
	}

	/**
	 * Checks to see if this task is finished. The task is finished based on its
	 * QAF. A QAF of AND means all subtasks must be finished, a QAF of OR and
	 * SUM mean at least one subtasks must be finished.
	 * 
	 * @return Whether or not the task is finished.
	 */
	public boolean checkIsFinished() {
		switch (qaf) {
		case SUM:
			boolean temp = false;
			boolean oneSubTask = false;
			for (Node n : subTasks) {
				if (n.hasArrived()) {
					oneSubTask = true;
					if (n instanceof Task) {
						temp |= ((Task) n).checkIsFinished();
					} else {
						temp |= n.isFinished();
					}
				}
			}
			finished = temp || !oneSubTask;
			break;
		case AND:
			temp = true;
			for (Node n : subTasks) {
				if (n.hasArrived()) {
					if (n instanceof Task) {
						temp &= ((Task) n).checkIsFinished();
					} else {
						temp &= n.isFinished();
					}
				}
			}
			finished = temp;
			break;
		case OR:
			temp = false;
			oneSubTask = false;
			for (Node n : subTasks) {
				if (n.hasArrived()) {
					oneSubTask = true;
					if (n instanceof Task) {
						temp |= ((Task) n).checkIsFinished();
					} else {
						temp |= n.isFinished();
					}
				}
			}
			finished = temp || !oneSubTask;
			break;
		default:
			finished = false;
		}

		return finished;
	}

	/**
	 * Calculates the quality of this task based on its QAF and the quality of
	 * its children. It then sets this task's quality to the calculated value.
	 */
	public void calculateQualityFromChildren() {
		double q;
		switch (qaf) {
		case SUM:
			q = 0.0F;
			for (Node n : subTasks) {
				if (n.hasArrived()) {
					if (n instanceof Task) {
						((Task) n).calculateQualityFromChildren();
						q += n.getQuality();
					} else {
						if (n.isFinished()) {
							q += n.getQuality();
						}
					}
				}
			}
			quality = q;
			break;
		case AND:
			q = Integer.MAX_VALUE;
			boolean oneSubTask = false;
			for (Node n : subTasks) {
				if (n.hasArrived()) {
					oneSubTask = true;
					if (n instanceof Task) {
						((Task) n).calculateQualityFromChildren();
						if (n.getQuality() < q) {
							q = n.getQuality();
						}
					} else {
						if (n.isFinished()) {
							if (n.getQuality() < q) {
								q = n.getQuality();
							}
						} else {
							q = 0.0F;
							break;
						}
					}
				}
			}
			if (!oneSubTask) {
				q = 0.0F;
			}
			quality = q;
			break;
		case OR:
			q = Integer.MIN_VALUE;
			boolean oneFinished = false;
			boolean noMethods = true;
			for (Node n : subTasks) {
				if (n.hasArrived()) {
					if (n instanceof Task) {
						((Task) n).calculateQualityFromChildren();
						if (n.getQuality() > q) {
							q = n.getQuality();
						}
					} else {
						noMethods = false;
						if (n.isFinished()) {
							if (n.getQuality() > q) {
								q = n.getQuality();
							}
							oneFinished = true;
						}
					}
				}
			}
			if (!noMethods && !oneFinished) {
				// If the task has at least one method and no methods are
				// finished, then quality is 0.
				q = 0.0F;
			}
			quality = q;
			break;
		default:
			quality = 0.0F;
		}
	}

	public QAF getQaf() {
		return qaf;
	}

	/**
	 * Sets the QAF of this task based on a String representation of the QAF.
	 * Accepted values are "q_and", "q_or", and "q_sum".
	 * 
	 * @param qafStr
	 *            The String representation of a QAF to set as this task's QAF.
	 */
	public void setQaf(String qafStr) {
		if (qafStr.equals("q_and"))
			this.qaf = QAF.AND;
		else if (qafStr.equals("q_or"))
			this.qaf = QAF.OR;
		else if (qafStr.equals("q_sum")) {
			this.qaf = QAF.SUM;
		}

	}

	public ArrayList<Node> getSubTasks() {
		return subTasks;
	}
}
