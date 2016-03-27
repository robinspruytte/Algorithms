package algorithms.Cardgame;

public class Deck {
	public int[] deck;
	
	public Deck() {
		deck = new int[53];
	}
	
	/**
	 * Checks for first zero in int[] = end of array = first free spot
	 * @return index of first zero
	 */
	public int checkFirstZero() {
		int a = 0;
		while(deck[a] != 0) {
			a++;
		}
		return a;
	}
	
	/**
	 * add a card on the first free spot in the array using method checkFirstZero()
	 * @param a card to be added
	 */
	public void add(int a) {
		deck[checkFirstZero()] = a;
	}
	
	/**
	 * Defines actual length of the deck. (same method as checkFirstZero())
	 * @return actual length of deck
	 */
	public int usedLength() {
		return checkFirstZero();
	}
	
	/**
	 * shuffles deck.
	 * Goes through all indexes and switches card with a card on another index
	 */
	public void shuffleDeck() {
		for (int i = 0 ; i < usedLength() ; i++) {
			int a = (int) (Math.random()*usedLength());
			int temp = deck[i];
			deck[i] = deck[a];
			deck[a] = temp;
		}
	}
	
	/**
	 * clears first index and pushes other indexes
	 */
	public void clearFirstIndex() {
		for (int a = 0 ; a < this.usedLength() ; a++) {
			deck[a] = deck[a + 1];
		}
	}
	
	/**
	 * shows top card of the deck (index 0)
	 * @return top card
	 */
	public int topCard() {
		return deck[0];
	}
	
	/**
	 * returns the card on a certain index
	 * @param a
	 * @return deck on index a
	 */
	public int getIndex( int a ) {
		return deck[a];
	}
	
	/**
	 * changes the card on a certain index to another card
	 * @param index
	 * @param waarde
	 */
	public void setIndex( int index, int value) {
		deck[index] = value;
	}
	
	/**
	 * Print out the whole deck, card by card
	 */
	public String toString(){
		System.out.println(" ");
		for (int i = 0 ; i < this.usedLength() ; i++){
			System.out.print(deck[i] + " ");
		}
		return null;
	}
	
	/**
	 * resets a deck. Makes it empty.
	 */
	public void resetDeck() {
		for (int i = 0 ; i < deck.length ; i++) {
			deck[i] = 0;
		}
	}
	

}
