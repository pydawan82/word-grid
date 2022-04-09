package mcmc;

public interface State {
	
	/**
	 * @return <code>true</code> if this state is considered as a final state,
	 * <code>false</code> otherwise. A state is final if its energy is at a global maximum.
	 * @see ChainGenerator#metroHastings(Probability, Transition, State)
	 */
	public boolean isFinal();
	
	/**
	 * This function returns the energy of this state. This function is to be minimized by the
	 * Metropolis-Hastings algorithm.
	 * @return the energy of this state.
	 * @see ChainGenerator#metroHastings(Probability, Transition, State)
	 */
	public double energy();
}
