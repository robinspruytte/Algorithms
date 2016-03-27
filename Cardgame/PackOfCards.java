package algorithms.Cardgame;

public class PackOfCards {
	private int[] packOfCards;
	
	public PackOfCards() {
		packOfCards = new int[]{ 1,1,1,1, 2,2,2,2, 3,3,3,3, 4,4,4,4, 5,5,5,5, 6,6,6,6, 7,7,7,7, 8,8,8,8,
				9,9,9,9, 10,10,10,10, 11,11,11,11, 12,12,12,12, 13,13,13,13};
	}
	
	/**
	 * shuffle the cards
	 */
	public void shuffleDeck() {
		for (int i = 0 ; i < 52 ; i++) {
			int a = (int) (Math.random()*52);
			int temp = packOfCards[i];
			packOfCards[i] = packOfCards[a];
			packOfCards[a] = temp;
		}
	}
	
	/**
	 * get card on index a
	 * @param a
	 * @return
	 */
	public int getIndex( int a ) {
		return packOfCards[a];
	}
}
