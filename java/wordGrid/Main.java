package wordGrid;

import mcmc.ChainGenerator;
import mcmc.Probability;
import mcmc.Transition;
import tuples.Pair;
import tuples.Triple;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
	
	public static void main(String args[]) {
		
		String fileName;
		
		if(args.length != 1) {
			System.err.println("Expected the name of the file containing the words as argument.");
			System.err.println("Only 1 argument expected.");
			System.exit(-1);
			return;
		}
		
		fileName = args[0];
		
		int gridSize;
		
		try {
			Triple<Integer, List<Character>, List<String>> triple = Ressources.loadWords(new File(fileName));
			gridSize = triple.first;
			WordGrid.validChars = triple.second;
			WordGrid.validWords = triple.third;
		} catch (IOException e1) {
			System.err.println("Could not load the file containing the words!");
			System.exit(-1);
			return;
		}
		
		ChainGenerator.setMaxStep(50000);
		Probability prob = new AnnealingProbability(5);
		Transition<WordGrid> transition = new WordTransition();
		
		Pair<WordGrid, Integer> pair = ChainGenerator.metroHastings(prob, transition, new WordGrid(gridSize));
		
		WordGrid gen = pair.first;
		
		System.out.println(gen);
		System.out.println(gen.energy());
	}
}
