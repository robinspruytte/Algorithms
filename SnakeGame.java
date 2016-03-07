package algorithms;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeGame {
	private final int bordhoogte = 10;
	private final int bordbreedte = 10;
	private int size = 1;
	private boolean slangOfNiet = false;
	private boolean gameOver = false;
	private String richting = "";
	
	private int spelbord[][] = new int[bordhoogte][bordbreedte];
	
	private StukjeSlang food;
	private StukjeSlang newHead;
	private StukjeSlang oldHead;
	private StukjeSlang cursor;		
	private StukjeSlang head; 
	
	private List<StukjeSlang> slang;
	
	public SnakeGame(){
		head = new StukjeSlang(5, 5);
		food = new StukjeSlang((int) ((Math.random()*8)+1), (int) ((Math.random()*8)+1));
		slangOfNiet = false;
	}
	
	public void prepend(StukjeSlang stukje){
		stukje.setNext(head);
		head = stukje;
		size++;			
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
		oldHead = head;
		head = newHead;
		head.setNext(oldHead);
		if (newHead.getx() != food.getx() || newHead.gety() != food.gety()) { 
			voorlaatste(head).setNext(null);
		}
		toString();
		gameOver();
	}
	
	public void links(){
		newHead = new StukjeSlang(head.getx()-1, head.gety());
		oldHead = head;
		head = newHead;
		head.setNext(oldHead);
		if (newHead.getx() != food.getx() || newHead.gety() != food.gety()) { 
			voorlaatste(head).setNext(null);
		}
		toString();
		gameOver();
	}
	
	public void boven(){
		newHead = new StukjeSlang(head.getx(), head.gety()+1);
		oldHead = head;
		head = newHead;
		head.setNext(oldHead);
		if (newHead.getx() != food.getx() || newHead.gety() != food.gety()) { 
			voorlaatste(head).setNext(null);
		}
		toString();
		gameOver();
	}
	
	public void onder(){
		newHead = new StukjeSlang(head.getx(), head.gety()-1);
		oldHead = head;
		head = newHead;
		head.setNext(oldHead);
		if (newHead.getx() != food.getx() || newHead.gety() != food.gety()) {
			voorlaatste(head).setNext(null);
		}
		toString();
		gameOver();
	}
	
	public boolean gameOver(){
		gameOver = false;
		if (head.getx() == 0 || head.getx() == bordbreedte-1) {
			gameOver = true;
		}
		else if (head.gety() == 0 || head.gety() == bordhoogte-1) {
			gameOver = true;
		}
		else {
			cursor = head.next();
			for (int a = 1 ; a <= size-1 ; a++){
				if (head.getx() == cursor.getx() && head.gety() == cursor.gety()){
					gameOver = true;
				}
			}
			cursor = cursor.next();
		}
			return gameOver;
	}
	
	public String toString() {
		StringBuilder doolhof = new StringBuilder("\n");
		for(int y = 0; y < bordhoogte; y++) {
			for (int x = 0; x < bordbreedte; x++) {
				if(y == 0 || y == bordhoogte-1) {
					doolhof.append(" - ");
				}
				else if(x == 0 || x == bordbreedte-1) {
					doolhof.append(" | ");
				}
				else if(x == food.getx() && y == food.gety()){
					doolhof.append(" o ");
				}
				else if(x == head.getx() && y == head.gety()){ 
					doolhof.append(" * ");
				}
				else {					
					slangOfNiet = false;
					cursor = head;
					while (cursor.next() != null){
						if (x == cursor.getx() && y == cursor.gety()){ slangOfNiet = true;}
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
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				switch(spel.richting) {
					case "left":
						spel.links();
						break;
					case "rechts":
						spel.rechts();
						break;
					case "boven":
						spel.boven();
						break;
					case "onder":
						spel.onder();
						break;
				}
				System.out.print(spel.toString());
			}
		}, 1000, 1000);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Besturen via toetsen 1,2,3,5 (pijltjes)");
		System.out.print(spel.toString());
		
		while(true) {
			String input = scanner.next();
			switch(input) {
				case "q":
					spel.richting = "links";
					break;
				case "d":
					spel.richting = "rechts";
					break;
				case "z":
					spel.richting = "boven";
					break;
				case "s":
					spel.richting = "onder";
					break;
			}
		}
	}
}
