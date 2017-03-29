package HangmanGame;
import java.util.*;

public class Player {
	
// VARIABLES
	
	// The word the computer has to guess
	private String word;
	
	// Array of the alphabet
	private ArrayList<Character> alphabet;
	
	// Letter computer guesses
	private char letter;
	
	// Number of guesses left during game
	private int guesses;
	
	// Counter
	private int count;
	
	// Counter (i)
	private int i;
		
	// Scanner
	public static Scanner SCAN = new Scanner(System.in);
	
	// Length of word
	private int lengthOfWord;
	
	private String wordGuess;
	private StringBuilder wordGuess1;
		
// GETTERS
	
	public String getWord() {
		return word;
	}
	
	public String getWordGuess() {
		wordGuess = "";
		for(int j = 0; j < lengthOfWord; j++) {
			wordGuess += "_";
		}
		return wordGuess;
	}
	
	public StringBuilder getWordGuess1() {
		return new StringBuilder(getWordGuess());
	}
	
	public int getGuesses() {
		return guesses;
	}

	public int getCount() {
		return count;
	}
	
	public int getLengthOfWord() {
		return lengthOfWord;
	}
	
	// Puts alphabet letters in array
	public ArrayList<Character> getAlphabet() {
		ArrayList<Character> a = new ArrayList<Character>();
		String letters = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i < letters.length(); i++) {
			a.add(letters.charAt(i));
		}
		alphabet = a;
		return alphabet;
	}
	
	public void setLetter() {
		i = (int)(Math.random() * alphabet.size() - 1);
		char letter1 = alphabet.get(i);
		letter = Character.toLowerCase(letter1);
	}
	
	public char getLetter() {
		return letter;
	}
	
// METHODS
	
	// Lets computer know how long your word is.
	public void lengthOfWord() {
		System.out.println("In order for me to understand if I got all the letters of your word, "
				+ "I need you to tell me how long your word is!");
		System.out.print("How many letters are in your word?: ");
		
		while(SCAN.hasNext() && (lengthOfWord == (int)lengthOfWord)) {
			try {
				String l1 = SCAN.next();
				String l = l1.toLowerCase();
				lengthOfWord = Integer.parseInt(l);
				if(lengthOfWord == (int)lengthOfWord) {
					System.out.println("Okay! So your word has " + lengthOfWord + " letters.");
					break;
				}
			}
			catch (Exception e) {
				System.out.println();
				System.out.print("Please enter a number representing the length of your word: ");
			}
		} 
	}
		
	// Determines number of guesses the computer 
	// will have 
	public void numOfGuesses() {
		System.out.print("How many guesses can I have?: ");

		while (((SCAN.hasNext()) && (guesses == (int)guesses))) {
			try {
				String g = SCAN.next();
				guesses = Integer.parseInt(g);
				System.out.println("Alright! I got " + guesses + " guesses to guess your word!");
				break;
			}
			
			catch (Exception e) {
				System.out.println("The game won't start unless you pick a number!");
				System.out.println();
				System.out.print("Please type the number of guesses I can have: ");
			}
		} 
	}
	

	// The computer guesses letters in the word 
	// I'm thinking of
	public void getLetters() {

		// Creates a random letter computer "guesses"
		count = 0;
		alphabet = getAlphabet();
		setLetter();
		letter = getLetter();
		wordGuess1 = getWordGuess1();
		System.out.println("So this is the word I have to guess...");
		System.out.println(wordGuess1);
		System.out.println();
		System.out.println("Hmm, I'm thinking of the letter... " + letter + "?");
		System.out.print("Type 'yes' if I'm right or 'no' if I'm wrong: ");
		
		//Tests to see if what is inputed is a letter
		while(count < lengthOfWord && SCAN.hasNext()) {
			String choice1 = SCAN.next();
			String choice = choice1.toLowerCase();

			// Letter is right
			if(choice.equals("yes")) {
				System.out.println();
				
				if(guesses == 1) 
					System.out.println("Yes! One less letter to go! I have " + guesses + " guess left.");
				else
					System.out.println("Yes! One less letter to go! I have " + guesses + " guesses left.");
				
				// Replacing _ with letter in the word
				System.out.print("How many times is " + letter + " in the word?: " );
				int multiple = SCAN.nextInt();
				int position = 0;
				
				// Multiple occurrences of same letter in word
				for(int a = 1; a <= multiple; a++) {
					if(multiple == 1) 
						System.out.print("Which position is " + letter + " in the word?: ");
					else {
						if(a == 1)
							System.out.print(a + "st occurence: ");
						else if(a == 2)
							System.out.print(a + "nd occurence: ");
						else if(a == 3)
							System.out.print(a + "rd occurence: ");
						else
							System.out.print(a + "th occurence: ");
					}
					position = SCAN.nextInt();
					wordGuess1.setCharAt(position - 1, letter);
					count += 1;
				}
				
				// If computer guessed all letters
				if(getCount() == getLengthOfWord()) {
					System.out.println();
					System.out.println(wordGuess1);
					System.out.println();
					System.out.println("Woohoo, I win!");
					break;
				}
				alphabet.remove(alphabet.indexOf(letter));
			}
			
			// Letter is not right
			else if (choice.equals("no")) {
				System.out.println();
				guesses--;
				if(guesses == 1)
					System.out.println("Darn! I now have " + guesses + " guess left.");
				else if(guesses > 1)
					System.out.println("Darn! I now have " + guesses + " guesses left.");
				else {
					System.out.println("I've run out of guesses! :( You win. ");
					break;
				}
				alphabet.remove(alphabet.indexOf(letter));
			}
			
			// If user types anything but yes and no
			else {
				System.out.println();
				System.out.println("You have to type 'yes' or 'no' to tell me if I'm right or not and to proceed in the game!");
				System.out.println();
			}

			setLetter();
			System.out.println("So far, I got this...");
			System.out.println(wordGuess1);
			System.out.println();
			System.out.println("Hmm, I'm thinking of the letter... " + letter + "?");
			System.out.println(alphabet);
			System.out.print("Type 'yes' if I'm right or 'no' if I'm wrong: ");	
		} 
	}
}


