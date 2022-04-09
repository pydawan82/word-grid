package wordGrid;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mcmc.Transition;

public class CharTransition implements Transition<WordGrid> {
	
	final double P = 1;
	final double Q = .0;
	
	@Override
	public double prob(WordGrid g1, WordGrid g2) {
		
		if(g1.size!=g2.size)
			return 0;
		Point modified = null;
		
		for(int i=0; i<g1.size; i++) {
			for(int j=0; j<g1.size; j++) {
				if(g1.grid[i][j]!=g2.grid[i][j]) {
					if(modified != null)
						return 0;
					
					modified = new Point(i,j);
				}
			}
		}
		
		int ok = 0;

		if(g1.isRowValid(modified.x))
			ok++;
		if(g1.isColValid(modified.y))
			ok++;
		
		if(ok==0)
			return P;
		else if(ok==1)
			return Q;
		else
			return (1-P-Q);
	}
	
	@Override
	public WordGrid generate(WordGrid grid) {
		WordGrid newGrid = null;
		try {
			newGrid = grid.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		List<Point> notOk = new ArrayList<Point>();
		List<Point> ok1 = new ArrayList<Point>();
		List<Point> ok2 = new ArrayList<Point>();
		
		for(int i=0; i<grid.size; i++) {
			for(int j=0; j<grid.size; j++) {
				int ok=0;
				
				if(grid.isRowValid(i))
					ok++;
				if(grid.isColValid(j))
					ok++;
				
				if(ok==0)
					notOk.add(new Point(i,j));
				else if(ok==1)
					ok1.add(new Point(i,j));
				else
					ok2.add(new Point(i,j));
			}
		}
		
		Random rand = new Random();
		double randD = rand.nextDouble();
		Point modified = null;
		
		if(randD<P && notOk.size()!=0) {
			modified = notOk.get(rand.nextInt(notOk.size()));
			
		} else if(randD<P+Q && ok1.size()!=0) {
			modified = ok1.get(rand.nextInt(ok1.size()));
			
		} else if(ok2.size()!=0) {
			modified = ok2.get(rand.nextInt(ok2.size()));
		}
		
		if(modified==null)
			modified = new Point(rand.nextInt(grid.size), rand.nextInt(grid.size));
		

		while(newGrid.equals(grid)) {
			newGrid.grid[modified.x][modified.y] = WordGrid.validChars.get(rand.nextInt(WordGrid.validChars.size()));
		}
			
		return newGrid;
	}
}
