package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

public class Task extends Node{
	public enum QAF{
		AND,
		OR,
		SUM}
	private int earliestStartTime;
	private int deadline;
	private ArrayList<Node> subTasks = new ArrayList<Node>();
	private QAF qaf;
		
	public Task(Task parent, String name, double quality, boolean enabled,
			boolean finished, ArrayList<NodeRelationship> relationships,
			int earliestStartTime, int deadline, QAF qaf) {
		super(parent, name, quality, enabled, finished, relationships);
		this.earliestStartTime = earliestStartTime;
		this.deadline = deadline;
		this.qaf = qaf;
	}

	public void addSubTask(Node s){
		subTasks.add(s);
	}
	
	public void calculateQualityFromChildren(){
		double q;
		switch (qaf){
			case SUM:
				q = 0.0;
				for(Node n : subTasks){
					q += n.getQuality();
				}
				setQuality(q);
				break;
			case AND:
				q = Integer.MAX_VALUE;
				for(Node n : subTasks){
					if(n.getQuality() < q)
					q = n.getQuality();
				}
				setQuality(q);
				break;
			case OR:
				q = Integer.MIN_VALUE;
				for(Node n : subTasks){
					if(n.getQuality() > q)
					q = n.getQuality();
				}
				setQuality(q);
				break;
			default:
				setQuality(0);
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
		if(qafStr=="q_and")
			this.qaf = QAF.AND;
		else if(qafStr=="q_or")
			this.qaf = QAF.OR;
		else if(qafStr=="q_sum")
			this.qaf = QAF.SUM;
	}
}
