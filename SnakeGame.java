package algorithms;

import java.util.Scanner;

public class SnakeGame {
	private final int bordhoogte = 10;
	private final int bordbreedte = 10;
	private int size = 1;
	private boolean slangOfNiet = false;
	
	private StukjeSlang food;
	private StukjeSlang newHead;
	private StukjeSlang oldHead;
	private StukjeSlang cursor;		
	private StukjeSlang head; 
	
	public SnakeGame(){
		head = new StukjeSlang(5, 5);
		food = new StukjeSlang((int) (Math.random()*8+1), (int) (Math.random()*8+1));
	}
	
	public StukjeSlang voorlaatste(StukjeSlang cursor){
		if (size == 1) {
			return cursor;
		}
		if (size == 2) {
			return cursor.next();
		}
		if (cursor.next().next() == null) {
			return cursor;
		}
		return voorlaatste(cursor.next());
	}
	
	public void beweeg() {
		oldHead = head;
		head = newHead;
		head.setNext(oldHead);
		if ((head.getx() != food.getx()) || (head.gety() != food.gety())) { 
			voorlaatste(head).setNext(null);
		}
		nieuwFood();
		System.out.println(this);
	}
	
	public void nieuwFood() {
		if (head.getx() == food.getx() && head.gety() == food.gety()) {
			size++;
			food = new StukjeSlang((int) ((Math.random()*8)+1), (int) ((Math.random()*8)+1));
			if (head.gety() == food.getx() || head.gety() == food.gety()) {
				food = new StukjeSlang((int) ((Math.random()*8)+1), (int) ((Math.random()*8)+1));
			}
			cursor = head.next();
			for (int a = 1 ; a <= size-1 ; a++) {
				if (cursor.getx() == food.getx() && cursor.gety() == food.gety()) {
					food = new StukjeSlang((int) ((Math.random()*8)+1), (int) ((Math.random()*8)+1));
					nieuwFood();
				}
				cursor = cursor.next();
			}
		}
	}
	
	public void rechts(){
		newHead = new StukjeSlang(head.getx()+1, head.gety());
		beweeg();
	}
	
	
	public void links(){
		newHead = new StukjeSlang(head.getx()-1, head.gety());
		beweeg();
	}
	
	public void boven(){
		newHead = new StukjeSlang(head.getx(), head.gety()-1);
		beweeg();
	}
	
	public void onder(){
		newHead = new StukjeSlang(head.getx(), head.gety()+1);
		beweeg();
	}
	
	public void gameOver(){
		if (head.getx() == 0 || head.getx() == bordbreedte-1) {
			System.out.println(" GAME OVER!!!! ");
			System.out.println(" GAME OVER!!!! ");
			System.out.println(" GAME OVER!!!! ");
		}
		else if (head.gety() == 0 || head.gety() == bordhoogte-1) {
			System.out.println(" GAME OVER!!!! ");
			System.out.println(" GAME OVER!!!! ");
			System.out.println(" GAME OVER!!!! ");
		}
		else {
			cursor = head;
			while (cursor.next() != null){
				cursor = cursor.next();
				if (head.getx() == cursor.getx() && head.gety() == cursor.gety()){
					System.out.println(" GAME OVER!!!! ");
					System.out.println(" GAME OVER!!!! ");
					System.out.println(" GAME OVER!!!! ");
				}
			}			
		}
	}
	
	public String toString() {
		StringBuilder doolhof = new StringBuilder("\n");
		for(int rij = 0; rij < bordhoogte; rij++) {
			for (int kolom = 0; kolom < bordbreedte; kolom++) {
				if(rij == 0 || rij == bordhoogte-1) {
					doolhof.append(" - ");
				}
				else if(kolom == 0 || kolom == bordbreedte-1) {
					doolhof.append(" | ");
				}
				else if(kolom == head.getx() && rij == head.gety()){ 
					doolhof.append(" * ");
				}
				else if(kolom == food.getx() && rij == food.gety()){
					doolhof.append(" o ");
				}
				else {					
					slangOfNiet = false;
					cursor = head;
					while (cursor.next() != null){
						if (kolom == cursor.next().getx() && rij == cursor.next().gety()){ slangOfNiet = true ;}
						cursor = cursor.next();
					}
					if (slangOfNiet == true){ 
						doolhof.append(" * ");
					}
					else { doolhof.append("   "); }
				}
			}
			doolhof.append("\n");
		}
		return doolhof.toString();
	}
	
	public static void main(String[] args) {
		SnakeGame spel = new SnakeGame();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Besturen via toetsen zqsd (pijltjes) + enter");
		System.out.print(spel.toString());
		
		while(true) {
			String input = scanner.next();
			switch(input) {
				case "q":
					spel.links();
					spel.gameOver();
					break;
				case "d":
					spel.rechts();
					spel.gameOver();
					break;
				case "z":
					spel.boven();
					spel.gameOver();
					break;
				case "s":
					spel.onder();
					spel.gameOver();
					break;
			}
		}
	}
}
