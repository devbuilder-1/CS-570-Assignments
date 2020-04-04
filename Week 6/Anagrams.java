//Prithiv Dev
//10453922

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Anagrams 
{
	/**
	 * Data fields
	 */
	final Integer[] primes = {2 , 3 , 5 , 7, 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41 , 43 , 47 , 53 , 59 , 61 , 67 , 71 , 73 , 79 , 83 , 89 , 97 , 101};
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;
	
	
	/**
	 * Constructor
	 */
	public Anagrams() 
	{
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}
	
	/**
	 * Processes the text file of words
	 * @param s - name of file
	 * @throws IOException if file is not found or not readable
	 */
	
	private void processFile(String s) throws IOException 
	{ 
	FileInputStream fstream = new FileInputStream(s);
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); String strLine;
	while ((strLine = br.readLine()) != null) { this.addWord(strLine);
	} br.close();
	
	}
	
	/*
	 * Letter Table for hashing
	 */
	
	private void buildLetterTable() {
		//initialise all the alphabets
		Character AAlpha[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		letterTable = new HashMap<Character, Integer>();
		
		//for loop that get all the alphabets
		for(int k = 0; k<26; k++) {
			letterTable.put(AAlpha[k], primes[k]);
		}
	}
	
	/**
	 * The functions that generates unique keys for hash functions
	 */
	
	private Long myHashCode(String s) {
		
		long wordcounter = 1;
		int k = 0;
		for(int j=0;j<s.length();j++) {
			Character a = s.charAt(k);
			wordcounter = wordcounter * letterTable.get(a);
			k++;
		}
		return wordcounter;
	}
	
	/**
	 * for storing anagrams
	 */
	
	private void addWord(String s) {
		if (anagramTable.containsKey(myHashCode(s))) {
			ArrayList<String> temp = anagramTable.get(myHashCode(s));
			temp.add(s);
			anagramTable.replace(myHashCode(s), temp);
		} else {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(s);
			anagramTable.put(myHashCode(s), temp);
		}
	}
	
	/**
	 * Finds the words with the most anagrams in the hash table.
	 * @return a list of words that have the most anagrams in the hash table.
	 */
	
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() 
	{
		int maximum = 0;
		ArrayList<Map.Entry<Long,ArrayList<String>>> m = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		for(Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			if(entry.getValue().size() > maximum) {
				maximum = entry.getValue().size();
				m.clear();
				m.add(entry);
			} else if(entry.getValue().size() == maximum) {
				m.add(entry);
			}
		}
		return m;
	}
	
	//Tests the words.alpha.txt file for anagrams
	public static void main(String[] args) 
	{
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		long key = maxEntries.get(0).getKey();
		System.out.println("Time: "+ seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue()); 
		System.out.println("Length of list of max anagrams: "+ length);
	}

}
