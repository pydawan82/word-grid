package wordGrid;

import static java.lang.Math.exp;

import mcmc.Probability;
import mcmc.State;

public class CharProbability implements Probability {
	
	final double C = 1;
	final double TEMP = 1;
	
	@Override
	public double proba(State s, int step) {
		return C*exp(-TEMP*s.energy());
	}
}
