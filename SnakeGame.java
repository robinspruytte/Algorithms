package algorithms;

public class SnakeGame {
	private final int bordhoogte = 10;
	private final int bordbreedte = 10;
	
	private int spelbord[][] = new int[bordhoogte][bordbreedte];
	
	private StukjeSlang<Integer, Integer> food = new StukjeSlang<Integer, Integer>(3, 7);
		
	private StukjeSlang<Integer, Integer> tail = new StukjeSlang<Integer, Integer>(bordhoogte/2, bordbreedte/2);
	private List<StukjeSlang<Integer, Integer>> slang = new List<StukjeSlang<Integer, Integer>>(tail);
	
	
}
