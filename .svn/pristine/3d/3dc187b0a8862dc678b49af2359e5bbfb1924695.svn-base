package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

public class Task extends Node{
	public enum QAF{
		NOT_SET,
		AND,
		OR,
		SUM
	}
	
	private int earliestStartTime;
	private int deadline;
	private ArrayList<Node> subTasks;
	private QAF qaf;
	
	public Task(Task parent, String name, double quality, boolean enabled,
			boolean finished, ArrayList<NodeRelationship> relationships,
			int earliestStartTime, int deadline, QAF qaf) {
		super(parent, name, quality, enabled, finished, relationships);
		this.earliestStartTime = earliestStartTime;
		this.deadline = deadline;
		this.qaf = qaf;
		this.subTasks = new ArrayList<Node>();
	}
	
	public Task(Task parent, String name) {
		super(parent, name);
		this.earliestStartTime = -1;
		this.deadline = -1;
		this.qaf = QAF.NOT_SET;
		this.subTasks = new ArrayList<Node>();
	}

	public void addSubTask(Node s) {
		subTasks.add(s);
	}
	
	public void calculateQualityFromChildren(){
		double q;
		switch (qaf){
			case SUM:
				q = 0.0F;
				for(Node n : subTasks){
					if (n instanceof Task) {
						((Task)n).calculateQualityFromChildren();
						q += n.getQuality();
					}
					else {
						if (n.isFinished()) {
							q += n.getQuality();
						}
					}
				}
				setQuality(q);
				break;
			case AND:
				q = Integer.MAX_VALUE;
				for(Node n : subTasks){
					if (n instanceof Task) {
						((Task)n).calculateQualityFromChildren();
						if(n.getQuality() < q) {
							q = n.getQuality();
						}
					}
					else {
						if (n.isFinished()) {
							if(n.getQuality() < q) {
								q = n.getQuality();
							}
						}
						else {
							q = 0.0F;
							break;
						}
					}
				}
				setQuality(q);
				break;
			case OR:
				q = Integer.MIN_VALUE;
				boolean oneFinished = false;
				boolean noMethods = true;
				for(Node n : subTasks){
					if (n instanceof Task) {
						((Task)n).calculateQualityFromChildren();
						if(n.getQuality() > q) {
							q = n.getQuality();
						}
					}
					else {
						noMethods = false;
						if (n.isFinished()) {
							if(n.getQuality() > q) {
								q = n.getQuality();
							}
							oneFinished = true;
						}
					}
				}
				if (!noMethods && !oneFinished) {
					q = 0.0F;
				}
				setQuality(q);
				break;
			default:
				setQuality(0.0F);
		}
	}

	public int getEarliestStartTime() {
		return earliestStartTime;
	}

	public void setEarliestStartTime(int earliestStartTime) {
		this.earliestStartTime = earliestStartTime;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public QAF getQaf() {
		return qaf;
	}

	public void setQaf(String qafStr) {
		if(qafStr.equals("q_and"))
			this.qaf = QAF.AND;
		else if(qafStr.equals("q_or"))
			this.qaf = QAF.OR;
		else if(qafStr.equals("q_sum"))
			this.qaf = QAF.SUM;
	}
	
	public ArrayList<Node> getSubTasks() {
		return subTasks;
	}
}
