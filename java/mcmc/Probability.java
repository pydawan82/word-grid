package mcmc;

@FunctionalInterface
public interface Probability {
	/**
	 * 
	 * @param s - A state.
	 * @param step - The number of steps the Metropolis-Hastings already did.
	 * @return The probability to be in the given state.
	 */
	public double proba(State s, int step);
}
