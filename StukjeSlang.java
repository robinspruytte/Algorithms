package algorithms;

public class StukjeSlang {
	
	private int x;
	private int y;
	private StukjeSlang next;
	
	public StukjeSlang(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getx(){
		return x;
	}
	
	public int gety(){
		return y;
	}
	
	public void setNext(StukjeSlang volgendStukje){
		next = volgendStukje;
	}
	
	public StukjeSlang next(){
		return next;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof StukjeSlang){
			StukjeSlang that = (StukjeSlang) obj;
			return (this.x == that.x && this.y == that.y && this.next == that.next);
					
		}
		return false;
	}
	

}
