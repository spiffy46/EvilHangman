package hangman;

import java.io.File;
import java.io.IOError;

import hangman.EvilHangmanGame;
import hangman.EvilHangmanGame.GuessAlreadyMadeException;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EvilHangmanGame myGame = new iEvilHangmanGame();
		
		Scanner s = new Scanner(System.in);
		boolean win = false;
		File f = new File(args[0]);
		Set<String> words = new HashSet<String>();
		int length = Integer.parseInt(args[1]);
		String guess;
		int guesses = Integer.parseInt(args[2]);
		int counter = 0;
		myGame.startGame(f, length);
		while(counter < guesses)
			{
			try {
			
				System.out.println("You have " + (guesses - counter) + " guesses left");
				System.out.println("Used letters: " + myGame.printGuesses());
				System.out.println("Word: " + myGame.printWord());
				System.out.print("Enter a guess:");
				guess = s.next();
				if(!valid(guess))
					throw new IOError(null);
				words = myGame.makeGuess(Character.toLowerCase(guess.toCharArray()[0]));		
				if(myGame.getPattern().filled == 0)
				{
					System.out.println("Sorry, there are no " + guess + "'s");
					counter++;
				}
				else if(myGame.getPattern().filled == 1)
				{
					System.out.println("Yes, there is 1 " + guess);
				}
				else
				{
					System.out.println("Yes, there are " + myGame.getPattern().filled + " " + guess + "'s");
				}
				System.out.println("");
				if(words.size()==1 && !myGame.printWord().contains("-"))
				{
					win = true;
					break;
				}
			}
			catch (GuessAlreadyMadeException e) {
				System.out.println("You already used that letter");
				System.out.println("");
			}
			catch(IOError e)
			{
				System.out.println("Invalid input");
				System.out.println("");
			}
		} 
		if(win)
		{
			System.out.println("You Win!");
			System.out.println(myGame.printWord());
		}
		else{
			System.out.println("You lose!");
			System.out.println("The word was: " + words.toArray(new String[0])[0]);
		}
		s.close();
	}

	private static boolean valid(String s) {
		if(s.length() != 1)
			return false;
		if(!(Character.isLetter(s.toCharArray()[0])))
			return false;
		return true;
	}

}
