package wordGrid;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import mcmc.State;

/**
 * This class represents a N-by-N grid of characters. It is a state and can be
 * minimized by creating valid words on rows and columns
 * 
 * The static fields {@link #validChars} and {@link #validWords} must be initialized before
 * creating and using any WordGrid.
 */
public class WordGrid implements State, Cloneable {
	
	public static List<Character> validChars = null;
	public static List<String> validWords = null;
	
	/**
	 * The size of the grid.
	 */
	public final int size;
	
	/**
	 * The N-by-N array of characters.
	 */
	public char[][] grid;
	
	/**
	 * Creates a new WordGrid of size N with random valid words on the rows.
	 * @param size
	 */
	public WordGrid(int size) {
		
		if(validWords==null)
			throw new NullPointerException("List of valid words has not been initialized");
		
		this.size = size;
		grid = new char[size][size];
		
		Random rand = new Random();
		
		for(int i=0; i<size; i++) {
			String word = validWords.get(rand.nextInt(validWords.size()));
			setRow(word, i);
		}
	}
	
	@Override
	public boolean isFinal() {
		return isValid();
	}
	
	/**
	 * 
	 * @return <code>true</code> if the grid is only made of valid words, <code>false</code> otherwise.
	 */
	public boolean isValid() {
		for(int i=0; i<size; i++) {
			if(!isRowValid(i) || !isColValid(i))
				return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param row - the index of the row
	 * @return <code>true</code> if the given row is a valid word, <code>false</code> otherwise.
	 */
	public boolean isRowValid(int row) {
		String word = "";
		
		for(int i=0; i<size; i++)
			word += grid[row][i];
		
		return validWords.contains(word);
	}
	
	/**
	 * 
	 * @param col - the index of the column
	 * @return <code>true</code> if the given column is a valid word, <code>false</code> otherwise.
	 */
	public boolean isColValid(int col) {
		String word = "";
		
		for(int i=0; i<size; i++)
			word += grid[i][col];
		
		return validWords.contains(word);
	}
	
	/**
	 * 
	 * @param row - the index of the row.
	 * @return a {@link String} representation of the word contained in the given row.
	 */
	public String getRow(int row){
		return new String(grid[row]);
	}
	
	/**
	 * 
	 * @param col - the index of the column.
	 * @return a {@link String} representation of the word contained in the
	 * given column.
	 */
	public String getCol(int col){
		char c[] = new char[size];
		for(int i = 0; i < size; i++){
			c[i] = grid[i][col];
		}
		return new String(c);
	}
	
	/**
	 * Replaces the characters of a row by the characters of a word
	 * @param word - A word of length {@link #size}.
	 * @param row - the index of the row.
	 * @throws IllegalArgumentException if the length of the word is not valid.
	 */
	public void setRow(CharSequence word, int row) {
		if(word.length()!=size)
			throw new IllegalArgumentException();
		
		for(int i=0; i<size; i++)
			grid[row][i] = word.charAt(i);
	}
	
	/**
	 * Replaces the characters of a column by the characters of a word
	 * @param word - A word of length {@link #size}.
	 * @param row - the index of the column.
	 * @throws IllegalArgumentException if the length of the word is not valid.
	 */
	public void setCol(CharSequence word, int col) {
		if(word.length()!=size)
			throw new IllegalArgumentException();
		
		for(int i=0; i<size; i++)
			grid[i][col] = word.charAt(i);
	}
	
	/**
	 * @return the number of rows and columns that do not contain a valid word.
	 */
	@Override
	public double energy() {
		double utility = 2*size;
		
		for(int i=0; i<size; i++) {
			if(isRowValid(i))
				utility--;
			
			if(isColValid(i))
				utility--;
		}
		
		return utility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(grid);
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordGrid other = (WordGrid) obj;
		if (!Arrays.deepEquals(grid, other.grid))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		for(char[] row: grid) {
			for(char c: row) {
				s += c;
			}
			s += System.lineSeparator();
		}
		
		return s;
	}
	
	@Override
	public WordGrid clone() throws CloneNotSupportedException {
		
		WordGrid grid = (WordGrid) super.clone();
		grid.grid = this.grid.clone();
		for(int i=0; i<size; i++)
			grid.grid[i] = this.grid[i].clone();
		return grid;
		
	}
}
