package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

public class Distribution {
	private ArrayList<Double> values;
	private ArrayList<Double> probabilities;
		
	public Distribution(ArrayList<Double> values,
			ArrayList<Double> probabilities) {
		this.values = values;
		this.probabilities = probabilities;
	}

	double evaluate(double num){
		return num;
	}

	public ArrayList<Double> getValues() {
		return values;
	}

	public void setValues(ArrayList<Double> values) {
		this.values = values;
	}

	public ArrayList<Double> getProbabilities() {
		return probabilities;
	}

	public void setProbabilities(ArrayList<Double> probabilities) {
		this.probabilities = probabilities;
	}
}
