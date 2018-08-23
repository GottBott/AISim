package edu.udel.cisc475.aisim.tasktree;

import java.util.ArrayList;

/**
 * This class represents a random distribution (in particular, a discrete
 * probability distribution). This is used to feed in the distributions defined
 * in the CTAEMS language. Sub-classing this class could allow for different
 * types of distributions in the future.
 * 
 * @author Mike
 *
 */
public class Distribution {
	/**
	 * The list of potential values in the distribution.
	 */
	private ArrayList<Double> values;

	/**
	 * The list of probabilities for each value in the distribution. These
	 * correspond exactly to the values, i.e. values[0] has probability
	 * probabilities[0] and so on.
	 */
	private ArrayList<Double> probabilities;

	/**
	 * The default constructor for a Distribution.
	 * 
	 * @param values
	 *            The list of potential values in the distribution.
	 * @param probabilities
	 *            The list of probabilities for each value in the distribution.
	 *            These correspond exactly to the values, i.e. values[0] has
	 *            probability probabilities[0] and so on.
	 */
	public Distribution(ArrayList<Double> values,
			ArrayList<Double> probabilities) {
		this.values = values;
		this.probabilities = probabilities;
	}

	/**
	 * Evaluates the distribution based on a random value between 0 and 1.
	 * 
	 * @param num
	 *            The random value feed into the distribution.
	 * @return The chosen value based on the input value. Returns a value of -1
	 *         if something is wrong (the probabilities add to less than 1 or a
	 *         number greater than 1 is feed in).
	 */
	public double evaluate(double num) {
		if (num < 1.F) {
			double sum = 0.F;
			for (int i = 0; i < probabilities.size(); i++) {
				sum += probabilities.get(i);
				if (num < sum) {
					return values.get(i);
				}
			}
		}
		return -1;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < probabilities.size(); i++) {
			sb.append("(" + values.get(i) + "," + probabilities.get(i) + "),");
		}
		if(sb.lastIndexOf(",") == -1){
			return sb.toString();
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.toString();
	}
	
}
