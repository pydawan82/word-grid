package wordGrid;

import mcmc.Probability;
import mcmc.State;

import static java.lang.Math.exp;

import java.util.function.Function;

public class AnnealingProbability implements Probability {
	private final Function<Integer, Double> betaFunc;
	
	/**
	 * Creates a new AnnealingProbability with a beta constant.
	 * @param beta
	 */
	public AnnealingProbability(double beta) {
		this.betaFunc = new Function<Integer, Double>() {
			double constBeta = beta;
			
			@Override
			public Double apply(Integer t) {
				return constBeta;
			}
			
		};
	}
	
	/**
	 * Creates a new AnnealingProbability with a beta given by a function
	 * of the number of steps.
	 * @param betaFunc - The function that gives beta.
	 */
	public AnnealingProbability(Function<Integer, Double> betaFunc) {
		this.betaFunc = betaFunc;
	}
	
	/**
	 * Return the probability of a state according to the simulated annealing
	 * algorithm
	 */
	public double proba(State s, int step) {
		return exp(-betaFunc.apply(step) * s.energy());
	}
}
