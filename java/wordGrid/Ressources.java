package wordGrid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tuples.Triple;

public final class Ressources {
	
	/**
	 * Loads the file containing the list of words.
	 * @param file - the file to be parsed.
	 * @return The size of the words, a list containing all the characters found in the list of words,
	 * a list containing all the words.
	 * @throws IOException if an IO error occurs.
	 */
	public static Triple<Integer, List<Character>, List<String>> loadWords(File file) throws IOException {
		List<Character> validChars = new ArrayList<Character>();
		List<String> validWords = new ArrayList<String>();
		int length = -1;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String word;
			
			while((word=reader.readLine())!=null) {
				if(word.length()==0)
					continue;
				
				if(length==-1)
					length=word.length();
				else if(word.length()!=length)
					throw new IOException("The length of the words is not constant");
				
				validWords.add(word);
				
				for(Character c: word.toCharArray()) {
					Character lower = Character.toLowerCase(c);
					if(!validChars.contains(lower))
						validChars.add(lower);
				}
			}
		}
		
		validChars = Collections.unmodifiableList(validChars);
		validWords = Collections.unmodifiableList(validWords);
			
		return new Triple<Integer, List<Character>, List<String>>(length, validChars, validWords);
	}
}
