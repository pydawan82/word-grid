package mcmc;
import java.util.Random;

import tuples.Pair;

/**
 * This class allows you to generate Markov chains to optimize problems.
 */
public final class ChainGenerator {
	
	/*
	 * Defines the maximum steps of the Metropolis-Hastings algorithm
	 */
	private static volatile int MAX_STEP = Integer.MAX_VALUE;
	
	/**
	 * Sets the maximum steps of the Metropolis-Hastings algorithm
	 * @param maxStep
	 * @see #metroHastings(Probability, Transition, State)
	 */
	public static void setMaxStep(int maxStep) {
		MAX_STEP = maxStep;
	}
	
	/**
	 * @return the maximum number of steps the Metropolis-Hastings
	 * algorithm will perform
	 * @see #metroHastings(Probability, Transition, State)
	 */
	public static int getMaxStep() {
		return MAX_STEP;
	}
	
	/**
	 * This function generates a Markov Chain in order to minimize the energy of a given state.
	 * This function loops until it reaches the maximum steps or if it reaches a state that is considered as final.
	 * @param proba - The implementation of the interface that returns the probability 
	 * to be in a given state.
	 * @param transition - The implementation of the interface that generates a new state
	 * from a given state. This interface also returns the probability to go from a given state
	 * to another state.
	 * @param init - The initial state.
	 * @return A pair containing the last state and the number of steps done.
	 * @see #setMaxStep(int)
	 * @see #getMaxStep()
	 */
	public static <S extends State> Pair<S, Integer> metroHastings(Probability proba, Transition<S> transition, S init) {
		
		int maxStep = MAX_STEP;
		int step = 0;
		S cur = init;
		
		S newState;
		double alpha;
		double u;
		Random rand = new Random();
		
		while(!cur.isFinal() && step<maxStep) {
			newState = transition.generate(cur);
			
			alpha = proba.proba(newState, step) * transition.prob(newState, cur);
			alpha /= proba.proba(cur, step) * transition.prob(cur, newState);
			
			u = rand.nextDouble();
			
			if(u < alpha)
				cur = newState;
			
			step++;
		}
		
		return new Pair<S, Integer>(cur, step);
	}
}
