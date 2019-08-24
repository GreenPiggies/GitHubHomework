import java.io.*;
import java.util.*;
public class WordPermutationIterator 
{
	private String word;
	private WordPermutationIterator subPermIter;
	private String currWord;
	private int currIndex;
	
	public WordPermutationIterator() {
		word = null;
		currIndex = -1;
		currWord = "";
		subPermIter = null;
	}
	
	public WordPermutationIterator(String input) {
		word = input;
		currIndex = -1;
		currWord = "";
		subPermIter = null;
		next();
	}
	
	public String next() {
		String perm = currWord;
		if (word.length() > 0) {
			if (subPermIter == null || !subPermIter.hasNext()) {
				currIndex++;
				if (currIndex < word.length()) {
					String subPerm = word.substring(0, currIndex) + word.substring(currIndex + 1);
					subPermIter = new WordPermutationIterator(subPerm);
					currWord = word.charAt(currIndex) + subPermIter.next();
				} else {
					currWord = null;
				}
			} else {
				currWord = word.charAt(currIndex) + subPermIter.next();
			}
		} else {
			currIndex++;
		}
		return perm;
	}
	
	public String peek() {
		return currWord;
	}
	
	public boolean hasNext() {
		return currIndex < word.length() && peek() != null;
	}
	
	public static void main(String[] args) {
		WordPermutationIterator permIter = new WordPermutationIterator("beat");
		while (permIter.hasNext()) {
			System.out.println(permIter.next());
		}
		
	}
}
