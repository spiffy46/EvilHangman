package hangman;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class iEvilHangmanGame implements EvilHangmanGame{
	Dictionary myDict;
	ArrayList<String> currentWords;
	Map<Pattern, ArrayList<String> > myMap;
	Set<Character> guessed;
	ArrayList<Pattern> patternList;
	int length;
	StringBuilder word;
	public Pattern mergePattern;
	
	public iEvilHangmanGame()
	{
		word = new StringBuilder();
		patternList = new ArrayList<Pattern>();
		currentWords = new ArrayList<String>();
		myDict = new Dictionary();
		guessed = new HashSet<Character>();
		mergePattern = new Pattern();
		myMap = new HashMap<Pattern, ArrayList<String>>();
		length = 0;
	}
	
	@Override
	public void startGame(File dictionary, int wordLength) {
		for(int i = 0; i < wordLength; i++)
		{
			word.append('-');
		}
		myDict.create(dictionary, wordLength);
		currentWords = myDict.words;
		guessed = new HashSet<Character>();
		myMap = new HashMap<Pattern, ArrayList<String>>();
		length = wordLength;
	}

	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
		guess = toLower(guess);
		myMap = new HashMap<Pattern, ArrayList<String>>();
		patternList = new ArrayList<Pattern>();
		if(guessed.contains(guess))
		{
			throw new GuessAlreadyMadeException();
		}
		else{
			guessed.add(guess);
			fillMap(guess);
			biggest();
			if(patternList.size() != 1)
			{
				fewest();
				if(patternList.size() != 1)
				{
					rightMost();
				}
			}
			mergePattern = patternList.get(0);
			currentWords = myMap.get(mergePattern);
			System.out.println(word);
		}
		
		Set<String> tempSet = new HashSet<String>(currentWords);
		merge(mergePattern);
		return tempSet;
	}
	
	
	public void fillMap(char g)
	{
		for(int i = 0; i < currentWords.size(); i++)
		{
			ArrayList<String> tempLst = new ArrayList<String>();
			Pattern p = new Pattern(currentWords.get(i),g);
			if(myMap.containsKey(p))
			{
				tempLst = myMap.get(p);
				tempLst.add(currentWords.get(i));
				myMap.put(p, tempLst);
			}
			else
			{
				tempLst.add(currentWords.get(i));
				myMap.put(p, tempLst);
			}
		}
	}
	
	public void biggest()
	{
		int biggest = 0;
		for(Map.Entry<Pattern, ArrayList<String>> entry : myMap.entrySet())
		{
			if(entry.getValue().size() > biggest)
			{
				biggest = entry.getValue().size();
			}
		}
		
		for(Map.Entry<Pattern, ArrayList<String>> entry : myMap.entrySet())
		{
			if(entry.getValue().size() == biggest)
			{
				patternList.add(entry.getKey());
			}
		}
	}
	
	public void fewest()
	{
		ArrayList<Pattern> tempList = patternList;
		patternList = new ArrayList<Pattern>();
		int fewest = length;
		for(int i = 0; i < tempList.size(); i++)
		{
			if(tempList.get(i).filled < fewest)
				fewest = tempList.get(i).filled;
		}
		
		for(int i = 0; i < tempList.size(); i++)
		{
			if(tempList.get(i).filled == fewest)
			{
				patternList.add(tempList.get(i));
			}
		}
	}
	
	public void rightMost()
	{
		ArrayList<Pattern> tempList = patternList;
		patternList = new ArrayList<Pattern>();
		Pattern tempPat = tempList.get(0);
		for(int i = 1; i < tempList.size(); i++)
		{
			if(tempPat.hashCode() > tempList.get(i).hashCode())
			{
				tempPat = tempList.get(i);
			}
		}
		patternList.add(tempPat);
	}
	
	public char toLower(char g)
	{
		String s = "" + g;
		s = s.toLowerCase();
		g = s.charAt(0);
		return g;
	}
	
	public void printMap()
	{
		System.out.println("PrintMap:");
		for(Map.Entry<Pattern, ArrayList<String> > entry : myMap.entrySet())
		{
			System.out.println("Key: " + entry.getKey().toString());
			for(int i = 0; i < entry.getValue().size(); i++)
			{
				System.out.println("Value: " + entry.getValue().get(i));
			}
		}

	}
	
	public String printDict()
	{
		return myDict.toString();
	}


	public String printWord() {
		return word.toString();
	}
	
	public Pattern getPattern(){
		return mergePattern;
	}
	
	public void merge(Pattern p)
	{
		for(int i = 0; i < word.length(); i++)
		{
			if(p.pattern.charAt(i) != '-')
			{
				word.setCharAt(i, p.pattern.charAt(i));
			}
		}
	}

	@Override
	public String printGuesses() {
		String out = "";
		Character[] s = guessed.toArray(new Character[0]);
		Arrays.sort(s);
		for(int i = 0; i < s.length; i++)
		{
			out = out + " " + (char)s[i];
		}
		return out;
	}

}
