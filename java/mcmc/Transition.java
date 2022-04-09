package mcmc;

public interface Transition<S extends State> {
	
	/**
	 * Generate a new successive state to a given {@link State}.
	 * @param s - the predecessor of the {@link State} to be generated
	 * @return the generated {@link State}
	 */
	public S generate(S s);
	
	/**
	 * Calculate the probability to come from a given state to another.
	 * The two states must be consecutive.
	 * @param s1 - The first state.
	 * @param s2 - Another state.
	 * @return The probability to go from {@link State} s1 to s2.
	 */
	public double prob(S s1, S s2);
}
