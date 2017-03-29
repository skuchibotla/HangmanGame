package HangmanGame;

import java.util.*;
import java.lang.*;

public class Guesser {

	// VARIABLES!!!

	// Number of guesses left during game
	private int guesses;

	// All letters of the alphabet
	private ArrayList<Character> alphabet;

	// The words I have to guess from in each category
	public static String[] BEAUTY = { "eyeliner", "foundation", "eyeshadow", "lipstick", "blush", "powder", "concealer",
			"mascara" };
	public static String[] SODA = { "coke", "pepsi", "fanta", "sprite", "drpepper", "mountaindew" };
	public static String[] COLORS = { "red", "orange", "yellow", "green", "blue", "purple", "white", "black", "grey" };
	private String[] categoryChoice;

	// Category chosen
	private String category;

	// Word you're trying to guess
	private String word;

	// Word with dashes
	private String wordGuess;

	// Word with dashes and letters
	private StringBuilder wordGuess1;

	// Letter being guessed
	private char letter;

	// Scanner
	public static Scanner SCAN = new Scanner(System.in);

	// GETTER METHODS!!!

	// Gets category array
	public String[] getCategoryChoice() {
		return categoryChoice;
	}

	// Computer chooses random word from chosen category
	// as the word of choice
	public void setWord() {
		int i = (int) (Math.random() * (getCategoryChoice().length - 1));
		word = getCategoryChoice()[i];
	}

	// Gets word
	public String getWord() {
		return word;
	}

	// Gets wordGuess
	public String getWordGuess() {
		wordGuess = "";
		for (int i = 0; i < word.length(); i++) {
			wordGuess += "_";
		}
		return wordGuess;
	}

	// Gets wordGuess1 StringBuilder
	public StringBuilder getWordGuess1() {
		return new StringBuilder(getWordGuess());
	}

	// Gets guesses
	public int getGuesses() {
		return guesses;
	}

	// Puts alphabet letters in array
	public ArrayList<Character> getAlphabet() {
		alphabet = new ArrayList<Character>();
		String letters = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < letters.length(); i++) {
			alphabet.add(letters.charAt(i));
		}
		return alphabet;
	}

	// ACTUAL METHODS!!!

	// Choose what category you want to pick words from
	public String chooseCategory() {

		System.out.print("Type 'beauty', 'soda', or 'colors' for a category: ");
		do {
			category = SCAN.next();
			category = category.toLowerCase();

			if (category.equals("beauty")) {
				category = "beauty";
				categoryChoice = BEAUTY;
				System.out.println("You have chosen beauty as a category!");
				System.out.println();
				return category;
			} else if (category.equals("soda")) {
				category = "soda";
				categoryChoice = SODA;
				System.out.println("You have chosen soda as a category!");
				System.out.println();
				return category;
			} else if (category.equals("colors")) {
				category = "colors";
				categoryChoice = COLORS;
				System.out.println("You have chosen colors as a category!");
				System.out.println();
				return category;
			} else {
				System.out.println();
				System.out.print("Please choose from either 'beauty', 'soda', or 'colors': ");
			}
		} while (SCAN.hasNext());
		return category;
	}

	// Number of guesses you want to have
	// during the game
	public void numOfGuesses() {

		System.out.print("Pick a number of guesses you want to have: ");

		while (SCAN.hasNext() && (guesses == (int) guesses)) {
			try {
				String g = SCAN.next();
				guesses = Integer.parseInt(g);
				System.out.println("Alright! You have " + guesses + " guesses to guess your word!");
				break;
			} catch (Exception x) {
				System.out.println("The game won't start unless you pick a number!");
				System.out.println();
				System.out.print("Pick a number of guesses you want to have: ");
			}
		}
	}

	// If the guess is right, remove guessed letter
	// from array of alphabet.
	// Else, remove guessed letter from the array,
	// and change the number of guesses
	public void guessLetter() {
		setWord();
		alphabet = getAlphabet();
		wordGuess1 = getWordGuess1();
		boolean flag = false;
		System.out.println("This is the word you have to guess!");
		System.out.println(wordGuess1);
		System.out.println();

		// Tests to see if the letter inputed
		// is a character
		while (!(wordGuess1.toString().equals(word)) && !flag) {
			System.out.println();
			System.out.println("These are the letters you can choose from: ");
			System.out.println(alphabet);
			System.out.println();
			System.out.print("Guess a letter, any letter!: ");

			while (SCAN.hasNext()) {
				char letter1 = SCAN.next().charAt(0);
				letter = Character.toLowerCase(letter1);
				CharSequence l = "" + letter;

				// If letter is already guessed
				if (wordGuess1.toString().contains(l) && !alphabet.contains(l) && Character.isLetter(letter)) {
					System.out.println("You already guessed this letter! Guess another one please.");
					if (guesses == 1)
						System.out.println("You have " + guesses + " guess left!");
					else
						System.out.println("You have " + guesses + " guesses left!");
				}

				// Letter is in word
				else if (word.contains(l)) {
					for (int i = 0; i < word.length(); i++) {
						if (word.charAt(i) == letter) {
							wordGuess1.setCharAt(i, letter);
						}
					}

					// If the whole letter is guessed
					if ((wordGuess1.toString().equals(word))) {
						System.out.println();
						System.out.println(word);
						System.out.println("Congratulations! You finished the game and guessed the word!");
						flag = true;
						break;
					}
					System.out.println("Nice guess!");
					if (guesses == 1)
						System.out.println("You have " + guesses + " guess left!");
					else
						System.out.println("You have " + guesses + " guesses left!");
					alphabet.remove(alphabet.indexOf(letter));
				}

				// If guess is wrong
				else if (!word.contains(l) && Character.isLetter(letter)) {
					System.out.println("Aw, nice try but this letter isn't part of the word!");
					guesses--;
					if (guesses == 1)
						System.out.println("You now have " + guesses + " guess left!");
					else if (guesses == 0) {
						System.out.println();
						System.out.println("Sorry, you have run out of guesses! End of game.");
						break;
					} else
						System.out.println("You now have " + guesses + " guesses left!");
					alphabet.remove(alphabet.indexOf(letter));
				}

				else {
					System.out.println("Please type a letter from the alphabet!");
					System.out.println();
				}

				System.out.println(wordGuess1);
				System.out.println();
				System.out.println("These are the letters you can choose from: ");
				System.out.println(alphabet);
				System.out.println();
				System.out.print("Guess another letter, any letter!: ");
			}
		}
	}
}