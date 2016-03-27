package algorithms.Cardgame;

import java.util.Scanner;

public class CardGame {
	private Deck deckPlayer1;
	private Deck deckPlayer2;
	private Deck temp;
	private CardGameMakeDecks divideCards;
	private static int rounds;
	
	public CardGame() {
		divideCards = new CardGameMakeDecks();
		divideCards.makeDecks();
		deckPlayer1 = divideCards.getDeck1();
		deckPlayer2 = divideCards.getDeck2();
		temp = new Deck();
		rounds = 0;
	}
	
	/** 
	 * Removes top card and pushes other cards so index 0 is not empty
	 */
	
	public void clearFirstIndex() {
		deckPlayer1.clearFirstIndex();
		deckPlayer2.clearFirstIndex();
	}
	
	/**
	 * Creates method for winning a round. 
	 * Top Cards from both deck (+ side deck if not empty) get added to winning deck
	 * Resets side deck
	 * @param winnerDeck deck of the player who won the round
	 * @param loserDeck deck of the player who lost the round
	 */
	public void winARound(Deck winnerDeck, Deck loserDeck) {
		int loserCard = loserDeck.topCard();
		int winnerCard = winnerDeck.topCard();
		clearFirstIndex();
		winnerDeck.add(winnerCard);
		winnerDeck.add(loserCard);
		if (temp.topCard() != 0) {
			for (int a = 0 ; a < temp.usedLength() ; a++) {
				winnerDeck.add(temp.getIndex(a));
				
			}
			temp.resetDeck();
		}
	}
	
	/**
	 * Creates method for when a round ends in a tie
	 * Adds top cards from both decks to side deck
	 */
	public void tieRound() {
			temp.setIndex(temp.checkFirstZero(), deckPlayer1.topCard());
			temp.setIndex(temp.checkFirstZero(), deckPlayer2.topCard());
			clearFirstIndex();
	}
	
	/**
	 * Start a new round.
	 * Check top cards to call tieRound() or winARound()
	 * Increases round counter by 1
	 */
	public void newRound() {
		if (deckPlayer1.topCard() == deckPlayer2.topCard()) {
			tieRound();
		}
		else if(deckPlayer1.topCard() > deckPlayer2.topCard()) {
			winARound(deckPlayer1, deckPlayer2);
		}
		else { winARound(deckPlayer2, deckPlayer1);}
		rounds++;
	}
	
	/**
	 * Checks if a player has won
	 * @return if a player has won, the method returns true. Otherwise it's false
	 */
	public boolean checkIfWinner(){
		if(deckPlayer2.topCard() == 0) {
			System.out.println("Player 1 wins");
			return true;
		}
		else if(deckPlayer1.topCard() ==0) {
			System.out.println("Player 2 wins");
			return true;
		}
		else {return false;}
	}
	
	/**
	 * Skip to the last round where a player wins.
	 * Keeps repeating rounds until checkIfWinner() returns true.
	 */
	public void skipToEnd() {
		while (checkIfWinner() == false) {
			newRound();
		}
	}
	
	/**
	 * Print out both decks + round counter
	 */
	public void printSituation() {
		System.out.println(" ");
		System.out.println(" Round " + rounds);
		System.out.print(" Deck Player 1 ");
		deckPlayer1.toString();
		System.out.println(" ");
		System.out.print(" Deck Player 2 ");
		deckPlayer2.toString();
		System.out.println(" ");
	}
	
	/**
	 * Makes the application for this game.
	 * @param args
	 */
	public static void main(String args[]) {
		CardGame game = new CardGame();
		game.printSituation();
		System.out.println(" ");
		System.out.println(" Advance by pressing d + enter or skip whole game with f + enter ");

		Scanner scanner = new Scanner(System.in);

		while (true) {
			String input = scanner.next();
			switch (input) {
			
			// When pressing d, advances 1 round.
			case "d": 
				game.newRound();
				game.printSituation();
				break;

			// When pressing s, skips to end. Also shows how much rounds were needed.
			case "s":
				game.skipToEnd();
				game.printSituation();
				System.out.println("This game took " + rounds + " rounds");
				break;
			}
		}
	}
}
