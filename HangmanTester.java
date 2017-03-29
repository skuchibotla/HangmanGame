package HangmanGame;

import java.util.Scanner;

public class HangmanTester {

	public static void main(String[] args) {
		
		rules();
		
		Scanner scan = new Scanner(System.in);
		Object obj = new Object();
		
		System.out.print("Please click 'g' if you want to guess the word I'm thinking! Otherwise, click 'p' for player: ");
		char player = scan.next().charAt(0);
		obj = chooseAPlayer(player);
	
		if(player == 'g') {
			System.out.println();
			Guesser g = (Guesser)obj;
			g.numOfGuesses();
			System.out.println();
			g.chooseCategory();
			g.setWord();
			String word = g.getWord();
			g.guessLetter();
		}
		
		else if(player == 'p') {
			System.out.println();
			Player p = (Player)obj;
			p.numOfGuesses();
			System.out.println();
			p.lengthOfWord();
			int wordLength = p.getLengthOfWord();
			System.out.println();
			p.getLetters();
		}
		else {
			System.out.println("You must choose either 'g' or 'p'. ");
			System.out.println();
			System.out.print("Please click 'g' if you want to guess the word I'm thinking! Otherwise, click 'p' for player: ");		
		}
	}
	
	// Rules of the game
	public static void rules() {
		System.out.println("Welcome to the game of Hangman! Before we play, here are the rules of the game:");
		System.out.println("1. If you're a guesser, you guess the word that I am thinking of.");
		System.out.println("2. If you're a player, I guess the word that you are thinking of.");
		System.out.println("3. For each letter you get wrong, the number of guesses you have decrease");
		System.out.println("as well as that letter being removed from the list of letters you choose from");
		System.out.println("4. There is no penalty if you get the right answer");
		System.out.println("5. Some of the words you have to guess as a guesser normally have a space");
		System.out.println("but for the simplicity of the game, it will be in one word");
		System.out.println();
		System.out.println("Well, without further ado, let's play some hangman!");
		System.out.println();
	}
	
	// Chooses if you want to be a guesser or player
	public static Object chooseAPlayer(char aPlayer) {
		Scanner scan = new Scanner(System.in);
		Object o = new Object();
		
		do {
			
			if(aPlayer == 'g') {
				System.out.println("You have chosen to be the guesser! Good luck trying to guess what I'm thinking!");
				o = new Guesser();
				return o;
			}
			else if(aPlayer == 'p') {
				System.out.println("You have chosen to be the player and make me guess what's in your head!");
				o = new Player();
				return o;
			}
			else {
				System.out.println("You must choose either 'g' or 'p'. ");
				System.out.println();
				System.out.print("Please click 'g' if you want to guess the word I'm thinking! Otherwise, click 'p' for player: ");
			}
		} while(scan.hasNext());
		return o;
	}
	
}
