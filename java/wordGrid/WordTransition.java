package wordGrid;

import java.util.Random;

import mcmc.Transition;

public class WordTransition implements Transition<WordGrid> {
	
	/**
	 * Generates a new WordGrid by choosing a random word 
	 * and putting it at the best place it can.
	 */
	@Override
	public WordGrid generate(WordGrid grid) {
		WordGrid newGrid = null;
		WordGrid tmpGrid = null;
		
		try {
			newGrid = grid.clone();
			tmpGrid = grid.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		Random rand = new Random();
		String word = WordGrid.validWords.get(rand.nextInt(WordGrid.validWords.size()));
		String tmp;

		double utility = grid.size * 2;

		for(int i = 0; i < newGrid.size; i++){
			tmp = grid.getCol(i);
			tmpGrid.setCol(word, i);
			if(tmpGrid.energy() < utility){
				try{
					newGrid = tmpGrid.clone();
				}catch(CloneNotSupportedException e){
					e.printStackTrace();
				}
				utility = tmpGrid.energy();
			}
			tmpGrid.setCol(tmp, i);
			tmp = grid.getRow(i);
			tmpGrid.setRow(word, i);
			if(tmpGrid.energy() < utility){
				try{
					newGrid = tmpGrid.clone();
				}catch(CloneNotSupportedException e){
					e.printStackTrace();
				}
				utility = tmpGrid.energy();
			}
			tmpGrid.setRow(tmp, i);
		}
		
		return newGrid;
	}
	
	/**
	 * Returns always 1.
	 */
	@Override
	public double prob(WordGrid g1, WordGrid g2) {
		return 1;
	}
}
