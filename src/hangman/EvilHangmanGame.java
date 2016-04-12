package hangman;

import java.io.File;
import java.util.Set;

public interface EvilHangmanGame {
	
	@SuppressWarnings("serial")
	public static class GuessAlreadyMadeException extends Exception {
	}
	
	/**
	 * Starts a new game of evil hangman using words from <code>dictionary</code>
	 * with length <code>wordLength</code>
	 * 
	 * @param dictionary Dictionary of words to use for the game
	 * @param wordLength Number of characters in the word to guess
	 */
	public void startGame(File dictionary, int wordLength);
	

	/**
	 * Make a guess in the current game.
	 * 
	 * @param guess The character being guessed
	 * @return The set of strings that satisfy all the guesses made so far
	 * in the game, including the guess made in this call. The game could claim
	 * that any of these words had been the secret word for the whole game. 
	 * 
	 * @throws GuessAlreadyMadeException If the character <code>guess</code> 
	 * has already been guessed in this game.
	 */
	public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException;


	public String printDict();


	public void printMap();


	public String printWord();


	public String printGuesses();


	public Pattern getPattern();
	
}
