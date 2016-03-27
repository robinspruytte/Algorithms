package algorithms.Cardgame;

public class CardGameMakeDecks {
	private PackOfCards packOfCards;
	private Deck deck1;
	private Deck deck2;
	
	public CardGameMakeDecks(){
		packOfCards = new PackOfCards();
		deck1 = new Deck();
		deck2 = new Deck();
	}
	
	/**
	 * shuffles a new pack of cards ands divides it in two equally sized decks.
	 */
	public void makeDecks(){
		packOfCards.shuffleDeck();
		for ( int a = 0 ; a < 26 ; a++ ) {
			deck1.setIndex(a , packOfCards.getIndex(a));
			deck2.setIndex(a , packOfCards.getIndex(a + 26));
		}
	}
	
	/**
	 * return pack of cards (shuffled or not)
	 * @return
	 */
	public PackOfCards getPackOfCards(){
		return packOfCards;
	}
	
	/**
	 * returns first deck
	 * @return 
	 */
	public Deck getDeck1(){
		return deck1;
	}
	
	/**
	 * returns second deck
	 * @return
	 */
	public Deck getDeck2(){
		return deck2;
	}
	
}
