package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

public class Distribution {
	private ArrayList<Double> values;
	private ArrayList<Double> probabilities;
		
	public Distribution(ArrayList<Double> values, ArrayList<Double> probabilities) {
		this.values = values;
		this.probabilities = probabilities;
	}

	public double evaluate(double num) {
		double sum = 0.F;
		for (int i = 0; i < probabilities.size(); i++) {
			sum += probabilities.get(i);
			if (num < sum) {
				return values.get(i);
			}
		}
		return -1;
	}
}
