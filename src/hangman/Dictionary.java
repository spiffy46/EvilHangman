package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {
	ArrayList<String> words;
	int size;
	
	public Dictionary()
	{
		words = new ArrayList<String>();
		size = 0;
	}
	
	public void create(File d, int length)
	{
		Set<String> tempSet = new HashSet<String>();
		String tempStr;
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader(d)));
			while(s.hasNext())
			{
				tempStr = s.next();
				if(tempStr.length() == length)
				{
					tempStr = tempStr.toLowerCase();
					tempSet.add(tempStr);
					
				}
			}
			size = tempSet.size();
			words = new ArrayList<String>(tempSet);
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found");
		}
	}
	
	public String get(int i)
	{
		return words.get(i);
	}
	
	public String toString()
	{
		String out = "";
		for(int i = 0; i < words.size(); i++)
		{
			out = out + words.get(i) + '\n';
		}
		return out;
	}
}
