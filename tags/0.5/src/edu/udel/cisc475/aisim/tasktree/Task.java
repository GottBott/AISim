package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

public class Task extends Node{
	public enum QAF{
		NOT_SET,
		AND,
		OR,
		SUM
	}
	
	private ArrayList<Node> subTasks;
	private QAF qaf;
	
	public Task(Task parent, String name) {
		super(parent, name);
		this.qaf = QAF.NOT_SET;
		this.subTasks = new ArrayList<Node>();
	}

	public void addSubTask(Node s) {
		subTasks.add(s);
		s.setParent(this);
	}
	
	@Override
	public void enable() {
		for (Node n : subTasks) {
			n.enable();
		}
	}
	
	@Override
	public void disable() {
		for (Node n : subTasks) {
			n.disable();
		}
	}
	
	@Override
	public void facilitate(double qualityFactor, double durationFactor) {
		for (Node n : subTasks) {
			n.facilitate(qualityFactor, durationFactor);
		}
	}
	
	@Override
	public void hinder(double qualityFactor, double durationFactor) {
		for (Node n : subTasks) {
			n.hinder(qualityFactor, durationFactor);
		}
	}
	
	@Override
	public void computeEarliestStartAndDeadline() {
		if (parent != null) {
			if (parent.getEarliestStartTime() != -1) {
				this.earliestStartTime = Math.max(parent.getEarliestStartTime(), earliestStartTime);
			}
			if (parent.getDeadline() != -1) {
				this.deadline = Math.min(parent.getDeadline(), deadline);
			}
		}
		for (Node n : subTasks) {
			n.computeEarliestStartAndDeadline();
		}
	}
	
	public boolean checkIsFinished() {
		finished = true;
		for (Node n : subTasks) {
			if (n instanceof Task) {
				finished &= ((Task)n).checkIsFinished();
			}
			else {
				finished &= n.isFinished();
			}
		}
		
		return finished;
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

	public QAF getQaf() {
		return qaf;
	}

	public void setQaf(String qafStr) {
		if(qafStr.equals("q_and"))
			this.qaf = QAF.AND;
		else if(qafStr.equals("q_or"))
			this.qaf = QAF.OR;
		else if(qafStr.equals("q_sum")){
			this.qaf = QAF.SUM;
		}

	}
	
	public ArrayList<Node> getSubTasks() {
		return subTasks;
	}
}
